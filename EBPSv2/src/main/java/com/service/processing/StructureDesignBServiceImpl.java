package com.service.processing;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.DaoImpStructureDesign;
import com.dao.processing.DaoStructureDesign;
import com.model.application.ApplicationApproved;
import com.model.processing.StructureDesignB;

@Service
public class StructureDesignBServiceImpl implements StructureDesignBService {

    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    public Object index(Long applicationNo) {
        DaoStructureDesign da = new DaoImpStructureDesign();
        message.list = da.getAll("from StructureDesignB WHERE applicationNo=" + applicationNo);
        if (message.list.isEmpty()) {
            return message.respondWithError("Record not found");
        }
        message.map = new HashMap();
        message.map.put("data", message.list.get(0));
        message.map.put("comment", message.getComment("" + applicationNo, "4"));
        message.map.put("history", message.getHistory(applicationNo, "4"));
        return message.map;

    }

    public Object save(Long applicationNo, String jsonData, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return message.respondWithError("Authorization Error");
        }
        String userType = td.getUserType();

        if (!message.checkSaveStatus(userType, "4", applicationNo)) {
            return message.respondWithError(message.getMsg());
        }
        DaoStructureDesign da = new DaoImpStructureDesign();
        String userId = td.getUserId();
        StructureDesignB obj = new StructureDesignB();
        obj.setApplicationNo(applicationNo);
        obj.setEnterBy(userId);
        obj.setEnterDate(new Date());
        obj.setErStatus("P");
        obj.setSerStatus("P");
        obj.setSerDate(null);
        obj.setSerDate(null);
        obj.setJsonData(jsonData);
        row = da.save(obj);
        message.setHistory(applicationNo, td.getUserType(), "4", "S", td.getUserName());
        msg = message.db.getMsg();

        if (row == 1 || message.db.getMsg().contains("Success")) {
            message.db.save(message.getEnterByStatus(userType, 4, applicationNo));
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
        return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "4");
    }
}