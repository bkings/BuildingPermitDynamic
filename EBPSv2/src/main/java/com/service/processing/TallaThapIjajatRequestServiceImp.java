/*    map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonData, new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {});
 */
package com.service.processing;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.DaoTallaThapIjajatRequest;
import com.model.application.ApplicationApproved;
import com.model.processing.TallaThapIjajatRequest;

@Service
public class TallaThapIjajatRequestServiceImp implements TallaThapIjajatRequestService {

    @Autowired
    DaoTallaThapIjajatRequest da;
    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getAll(String Authorization, long id) {

        message.list = da.getAll("from TallaThapIjajatRequest where applicationNo=" + id);
        if (message.list.isEmpty()) {
            return message.respondWithError("Record not found");
        }
        message.map = new HashMap();
        message.map.put("data", message.list.get(0));
        message.map.put("comment", message.getComment("" + id, "59"));
        message.map.put("history", message.getHistory(id, "59"));
        return message.map;
    }

    @Override
    public Object save(TallaThapIjajatRequest obj, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }

        String userType = td.getUserType();
        long applicationNo = obj.getApplicationNo();
        if (!message.checkSaveStatus(userType, "59", applicationNo)) {
            return message.respondWithError(message.getMsg());
        }
        int row = 0;
        obj.setEnterBy(td.getUserId());
        obj.setEnterDate(new Date());
        try {
            System.out.println(obj);
            row = da.save(obj);
            if (row == 1) {
                message.db.save(message.getEnterByStatus(userType, 59, applicationNo));
                message.setHistory(applicationNo, td.getUserType(), "59", "S", td.getUserName());
                return message.respondWithMessage("Success");
            }
            return message.respondWithError(da.getMsg());
        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }

    }

    @Override
    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization) {
        return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "59");
    }
}
