/*    map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonData, new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {});
        */

package com.service.utility;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.utility.DaoMenuMaster;
import com.model.utility.MenuMaster;

@Service
public class MenuMasterServiceImp implements MenuMasterService {

    @Autowired
    DaoMenuMaster da;
    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getAll() {
        return da.getAll("from MenuMaster");
    }

    @Override
    public Object save(MenuMaster obj, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        try {
           try{
            sql = "SELECT coalesce(MAX(ID),0)+1 AS id FROM menu_master";
            message.map = (Map) da.getRecord(sql).get(0);
            obj.setId(Long.parseLong(message.map.get("id").toString()));
          } catch(Exception e){
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

        } catch(Exception e) {
            return message.respondWithError(e.getMessage());
        }
    }

    @Override
    public Object update(MenuMaster obj,long id,String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
            obj.setId(id);
            row = da.update(obj);
    msg = da.getMsg();
        if (row > 0) {
            return message.respondWithMessage("Success");
        }else if (msg.contains("Duplicate entry")) {
                    msg = "This record already exist";
                } else if (msg.contains("foreign key")) {
            msg = "this record already used in reference tables, Cannot delete of this record";
        }
        return message.respondWithError(msg);    }

    @Override
    public Object delete(String id,String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }

        id = "'" + id.replace(",", "','") + "'";
        sql = "DELETE FROM menu_master WHERE ID IN (" + id + ")";
        row = da.delete(sql);
        msg = da.getMsg();
        if (row > 0) {
            return message.respondWithMessage("Success");
        } else if (msg.contains("foreign key")) {
            msg = "this record already used in reference tables, Cannot delete of this record";
        }
        return message.respondWithError(msg);    }
}
