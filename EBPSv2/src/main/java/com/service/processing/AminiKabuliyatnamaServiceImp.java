/*    map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonData, new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {});
 */
package com.service.processing;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.processing.DaoAmini;
import com.model.processing.AminiKabuliyatnama;

@Service
public class AminiKabuliyatnamaServiceImp implements AminiKabuliyatnamaService {

    @Autowired
    DaoAmini da;
    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getAll(long id) {
        message.list = da.getAll("from AminiKabuliyatnama where applicationNo=" + id);
        if (message.list.isEmpty()) {
            return message.respondWithError("Ghar Compound Wall not proced.!!");
        }
        return message.list.get(0);
    }

    @Override
    public Object save(AminiKabuliyatnama obj, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        String userType = td.getUserType();
        try {
            obj.setEnterDate(new Date());
            obj.setEnterBy(td.getUserId());
            if (userType.equalsIgnoreCase("B")) {
                obj.setSerDate(new Date());
                obj.setSerName(td.getUserId());
            } else if (userType.equalsIgnoreCase("A")) {
                obj.setErDate(new Date());
                obj.setErName(td.getUserId());
            } else if (userType.equalsIgnoreCase("AD")) {
                obj.setAminiDate(new Date());
                obj.setAminiName(td.getUserId());
            } else {
                return message.respondWithError("You have not access this feature!!");
            }
            row = da.save(obj);
            msg = da.getMsg();
            if (row > 0) {
                message.db.save(message.getEnterByStatus(userType, 45, obj.getApplicationNo()));
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
    public Object update(AminiKabuliyatnama obj, long id, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }

//    row = da.update(obj);
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

}
