/*    map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonData, new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {});
 */
package com.service.processing;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.DaoRajaswaVoucher;
import com.model.application.ApplicationApproved;
import com.model.processing.RajaswaVoucher;

@Service
public class RajaswaVoucherServiceImp implements RajaswaVoucherService {

    @Autowired
    DaoRajaswaVoucher da;
    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getAll(long applicationNo) {
        message.list = da.getAll("from RajaswaVoucher where applicationNo='" + applicationNo + "'");
        if (message.list.isEmpty()) {
            return message.respondWithError("Record not found");
        }
        message.map = new HashMap();
        message.map.put("data", message.list.get(0));
        message.map.put("comment", message.getComment("" + applicationNo, "49"));
        message.map.put("history", message.getHistory(applicationNo, "49"));
        return message.map;
    }

    @Override
    public Object save(RajaswaVoucher obj, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        String userType = td.getUserType();
        long applicationNo = obj.getApplicationNo();
        if (!message.checkSaveStatus(userType, "49", applicationNo)) {
            return message.respondWithError(message.getMsg());
        }
        try {
            obj.setEnterBy(td.getUserName());
            obj.setEnterDate(new Date());
            row = da.save(obj);
            msg = da.getMsg();
            if (row > 0) {
                row = da.save(obj);
                if (row == 1) {
                    message.db.save(message.getEnterByStatus(userType, 49, applicationNo));
                    message.setHistory(applicationNo, td.getUserType(), "49", "S", td.getUserName());
                    return message.respondWithMessage("Success");
                }
                return message.respondWithError(da.getMsg());
            } else if (msg.contains("Duplicate entry")) {
                msg = "This record already exist";
            }
            return message.respondWithError(msg);

        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
    }

    @Override
    public Object update(Long applicationNo, ApplicationApproved obj, String Authorization) {
        return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "49");
    }
}
