/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.processing;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.EBPSDao;
import com.model.application.ApplicationApproved;
import com.model.processing.SurjaminMuchulka;

@Service
public class SurjaminMuchulkaServiceImp implements SurjaminMuchulkaService {

    @Autowired
    EBPSDao da;

    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getAll(long applicationNo) {
        message.list = da.getAll("from SurjaminMuchulka where applicationNo=" + applicationNo);
        if (message.list.isEmpty()) {
            return message.respondWithError("Record not found");
        }
        message.map = new HashMap();
        message.map.put("data", message.list.get(0));
        message.map.put("comment", message.getComment("" + applicationNo, "15"));
        message.map.put("history", message.getHistory(applicationNo, "15"));
        return message.map;
    }

    @Override
    public Object save(SurjaminMuchulka obj, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        String userType = td.getUserType();
        long applicationNo = obj.getApplicationNo();
        if (!message.checkSaveStatus(userType, "15", applicationNo)) {
            return message.respondWithError(message.getMsg());
        }
        int row = 0;
        obj.setEnterBy(td.getUserId());
        obj.setEnterDate(new Date());
        try {
            row = da.save(obj);
            if (row == 1) {
                message.db.save(message.getEnterByStatus(userType, 15, applicationNo));
                message.setHistory(applicationNo, td.getUserType(), "15", "S", td.getUserName());
                return message.respondWithMessage("Success");
            }
            return message.respondWithError(da.getMsg());
        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
    }

    @Override
    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization) {
        return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "15");
    }
}
