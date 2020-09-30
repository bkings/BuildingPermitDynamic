/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.utility;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.utility.DaoRoadSetBackMaster;
import com.model.utility.RoadSetBackMaster;

import model.Message;

@Service
public class RoadSetBackMasterServiceImp implements RoadSetBackMasterService{
    @Autowired
    DaoRoadSetBackMaster da;
    model.Message message = new model.Message();
    String msg = "", sql;
    int row;


    @Override
    public Object getAll() {
        return da.getAll("from RoadSetBackMaster order by id");
    }

    @Override
    public Object save(RoadSetBackMaster obj, String Authorization) {
        
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return new Message().respondWithError("Authorization Error");
        }
        try {
            try {
                sql = "SELECT coalesce(MAX(ID),0)+1 AS id FROM road_set_back_master";
                message.map = (Map) da.getRecord(sql).get(0);
                obj.setId(Long.parseLong(String.valueOf(message.map.get("id"))));
            } catch (Exception e) {
                return message.respondWithError("connection error or invalid table name");
            }
            row = da.save(obj);
            msg = da.getMsg();
            if (row > 0) {
                return message.respondWithMessage("Success");
            } else if (msg.contains("Duplicate entry")) {
                msg = "This record already exist";
            }
            return message.respondWithError(msg);

        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
    }

    @Override
    public Object update(RoadSetBackMaster obj, long id, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return new Message().respondWithError("Authorization Error");
        }
        obj.setId(id);
        row = da.update(obj);
        msg = da.getMsg();
        if (row > 0) {
            return message.respondWithMessage("Success");
        } else if (msg.contains("Duplicate entry")) {
            msg = "This record already exist";
        } else if (msg.contains("foreign key")) {
            msg = "this record already used in reference tables, Cannot delete of this record";
        }
        return message.respondWithError(msg);
    }

    @Override
    public Object delete(String id, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return new Message().respondWithError("Authorization Error");
        }

        id = "'" + id.replace(",", "','") + "'";
        sql = "DELETE FROM road_set_back_master WHERE ID IN (" + id + ")";
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
