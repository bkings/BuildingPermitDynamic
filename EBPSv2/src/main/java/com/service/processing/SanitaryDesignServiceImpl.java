package com.service.processing;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.DaoImpStructureDesign;
import com.dao.processing.DaoStructureDesign;
import com.model.application.ApplicationApproved;
import com.model.processing.SanitaryDesign;

@Service
public class SanitaryDesignServiceImpl implements SanitaryDesignService {

    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    public Object index(Long applicationNo) {
        DaoStructureDesign da = new DaoImpStructureDesign();
        message.list = da.getAll("from SanitaryDesign WHERE applicationNo=" + applicationNo);
        if (message.list.isEmpty()) {
            return message.respondWithError("Record not found");
        }
        message.map = new HashMap();
        message.map.put("data", message.list.get(0));
        message.map.put("comment", message.getComment("" + applicationNo, "7"));
        message.map.put("history", message.getHistory(applicationNo, "7"));
        return message.map;
    }

    public Object save(Long applicationNo, String jsonData, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }
        String userType = td.getUserType();

        if (!message.checkSaveStatus(userType, "7", applicationNo)) {
            return message.respondWithError(message.getMsg());
        }
        DaoStructureDesign da = new DaoImpStructureDesign();
        String userId = td.getUserId();
        SanitaryDesign obj = new SanitaryDesign();
        obj.setApplicationNo(applicationNo);
        obj.setEnterBy(userId);
        obj.setEnterDate(new Date());
        obj.setErStatus("P");
        obj.setSerStatus("P");
        obj.setSerDate(null);
        obj.setSerDate(null);
        obj.setJsonData(jsonData);
        row = da.save(obj);
        message.setHistory(applicationNo, td.getUserType(), "7", "S", td.getUserName());
        msg = message.db.getMsg();

        if (row == 1) {
            message.db.save(message.getEnterByStatus(userType, 7, applicationNo));
            return message.respondWithMessage("Success");
        } else {
            if (msg.contains("already exists")) {
                return message.respondWithMessage("designer already approved this process.");
            }
        }
        return message.respondWithError(msg);
    }

    @Override
    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization) {
        return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "7");
    }
}
