package com.service.processing;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.DaoAnusuchiGa;
import com.log.ApplicationLog;
import com.model.application.ApplicationApproved;
import com.model.processing.AnusuchiGa;
import com.model.utility.OrganizationUser;

import model.Message;

@Service
public class AnusuchiGaServiceImp implements AnusuchiGaService {

    @Autowired
    DaoAnusuchiGa da;
    Message message = new Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getIndex(String Authorization, String applicationNo) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }

        Map map = new HashMap();
        try {
            try {
                message.list = da.getAll("from AnusuchiGa where applicationNo='" + applicationNo + "'");
                if (message.list.isEmpty()) {
                    return message.respondWithError("Record not found");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println(message.list);
            try {
                map.put("data", message.list.get(0));
            } catch (Exception e) {
                System.out.println(e);
                return message.list;
            }
            try {
                map.put("comment", message.getComment(applicationNo, "9"));
            } catch (Exception e) {
            }
            try {
                map.put("history", message.getHistory(applicationNo, "9"));
            } catch (Exception e) {
            }
        } catch (Exception e) {
            System.out.println("Out "+e);
        }
        return map;
    }

    @Override
    public Object save(long applicationNo, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }
        String userType = td.getUserType();
        if (!message.checkSaveStatus(td.getUserType(), "9", applicationNo)) {
            return message.respondWithError(message.getMsg());
        }
        AnusuchiGa obj = new AnusuchiGa();
        obj.setApplicationNo(applicationNo);
        obj.setEnterBy(new OrganizationUser(td.getUserId()));
        obj.setEnterDate(new Date());
        obj.setErName("P");
        obj.setErDate(null);
        obj.setSerDate(null);
        obj.setSerStatus("P");
        row = da.save(obj);
        message.setHistory(applicationNo, td.getUserType(), "9", "S", td.getUserName());
        msg = da.getMsg();
        if (row == 1) {
            ApplicationLog.save("api/Processing/Phase1/AnusuchiGa", "POST", td.getUserName(), "save or update form");
            message.db.save(message.getEnterByStatus(userType, 9, applicationNo));
            message.map = new HashMap();
            message.map.put("message", "Success");
            return message.map;
        } else {
            if (msg.contains("Duplicate entry")) {
                return message.respondWithMessage("designer already approved this process.");
            }
        }
        return message.respondWithError(msg);
    }

    @Override
    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization) {
          return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "9");
    }
}