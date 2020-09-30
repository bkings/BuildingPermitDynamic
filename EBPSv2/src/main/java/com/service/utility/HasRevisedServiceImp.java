/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.utility.DaoHasRevised;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.utility.HasRevised;

@Service
public class HasRevisedServiceImp implements HasRevisedService {

    @Autowired
    DaoHasRevised da;
    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getAll(String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        return da.getAll("from HasRevised");
    }

    @Override
    public Object save(String obj1, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        List formList = new ArrayList();
        List userTypeList = new ArrayList();
        ObjectMapper mapper = new ObjectMapper();
        Map map = new HashMap();
        try {
            map = mapper.readValue(obj1, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HasRevised obj = new HasRevised();
        formList = (ArrayList) map.get("ignoredForm");
        userTypeList = (ArrayList) map.get("userType");

        for (int i = 0; i < userTypeList.size(); i++) {
            for (int j = 0; j < formList.size(); j++) {
                try {
                    try {
                        sql = "SELECT coalesce(MAX(ID),0)+1 AS id FROM has_revised";
                        message.map = (Map) da.getRecord(sql).get(0);
                        obj.setId(Integer.parseInt(message.map.get("id").toString()));
                    } catch (Exception e) {
                        return message.respondWithError("connection error or invalid table name");
                    }
                    obj.setStatus(map.get("status").toString());
                    obj.setHasRevised(Integer.parseInt(map.get("hasRevised").toString()));
                    obj.setUserType(userTypeList.get(i).toString());
                    obj.setIgnoredForm(Integer.parseInt(formList.get(j).toString()));
                    row = da.save(obj);
                    msg = da.getMsg();

                } catch (Exception e) {
                    return message.respondWithError(e.getMessage());
                }

            }
        }
        if (row > 0) {
            return message.respondWithMessage("Success");
        } else if (msg.contains("duplicate entry")) {
            msg = "This record already exist";
        }
        return message.respondWithError(msg);

    }

    @Override
    public Object update(HasRevised obj, Integer id, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        obj.setId(id);
        row = da.update(obj);
        msg = da.getMsg();
        if (row > 0) {
            return message.respondWithMessage("Success");
        } else if (msg.contains("duplicate key")) {
            msg = "This record already exist";
        } else if (msg.contains("foreign key")) {
            msg = "this record already used in reference tables, Cannot delete of this record";
        }
        return message.respondWithError(msg);
    }

    @Override
    public Object delete(String id, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }

        id = "'" + id.replace(",", "','") + "'";
        sql = "DELETE FROM has_revised WHERE ID IN (" + id + ")";
        row = da.delete(sql);
        msg = da.getMsg();
        if (row > 0) {
            return message.respondWithMessage("Success");
        } else if (msg.contains("foreign key")) {
            msg = "this record already used in reference tables, Cannot delete of this record";
        }
        return message.respondWithError(msg);
    }

}
