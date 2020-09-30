package com.service.processing;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.DaoArchitectural;
import com.dao.processing.DaoImpArchitectura;
import com.model.application.ApplicationApproved;
import com.model.processing.ArchitecturalDesignC;
import com.model.processing.ArchitecturalDesignCDetails;
import com.model.processing.ArchitecturalDesignDetailsPK;

import model.Message;

@Service
public class ArchitecturalDesignCServiceImp implements ArchitecturalDesignCService {

    DaoArchitectural da = new DaoImpArchitectura();
    Message message = new Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getindex(String applicationNo) {
        model.DB db = new model.DB();
        try {
            if (applicationNo.length() == 0) {
                applicationNo = "";
            }
        } catch (Exception e) {
        }
        sql = "SELECT BUILDING_TYPE buildingType,coalesce(B.ENTER_BY,'') \"enterBy\",B.ENTER_DATE \"enterDate\",B.ER_DATE \"erDate\",B.ER_NAME \"erName\",coalesce(B.ER_STATUS,'P') \"erStatus\",B.SER_DATE \"serDate\",B.SER_NAME \"serName\",coalesce(B.SER_STATUS,'P') \"serStatus\" FROM building_permit_application A LEFT JOIN architectural_design_c B ON A.ID=B.APPLICATION_NO WHERE A.ID='" + applicationNo + "'";
        message.list = db.getRecord(sql);
        if (message.list.isEmpty()) {
            return message.respondWithError("invalid application no");
        }
        message.map = (Map) message.list.get(0);
//    sql = "SELECT group_id \"groupId\" ,M.BUILDING_ELEMENTS \"buildingElements\",M.GROUP_NAME \"groupName\",M.UNIT \"unit\",QTY \"qty\",REMARK remarks,M.ID \"classId\" FROM architectural_design_c_master M LEFT JOIN architectural_design_c_details D  ON M.ID=D.CLASS_ID WHERE coalesce(D.APPLICATION_NO,'" + applicationNo + "')='" + applicationNo + "' ORDER BY M.ID";
        sql = "SELECT group_id \"groupId\" ,M.BUILDING_ELEMENTS \"buildingElements\",M.GROUP_NAME \"groupName\",M.UNIT \"unit\",CONCAT(QTY,'') \"qty\",REMARK remarks,M.ID \"classId\",D.unit \"usedUnit\" FROM architectural_design_c_master M ,architectural_design_c_details D   WHERE M.ID=D.CLASS_ID AND D.APPLICATION_NO='" + applicationNo + "' "
                + " UNION "
                + "SELECT group_id \"groupId\" ,M.BUILDING_ELEMENTS \"buildingElements\",M.GROUP_NAME \"groupName\",M.UNIT \"unit\",' ' \"qty\",'' AS  remarks,M.ID \"classId\",'' \"usedUnit\" FROM architectural_design_c_master M WHERE M.id NOT IN(SELECT D.class_id FROM architectural_design_c_details D WHERE D.application_no='" + applicationNo + "') ORDER BY \"classId\"";
        message.list = db.getRecord(sql);
        message.map.put("listData", message.list);
        message.map.put("comment", message.getComment(applicationNo, "5"));
        message.map.put("history", message.getHistory(applicationNo, "5"));
        return message.map;
    }

    @Override
    public Object save(long applicationNo, String jsonData, String Authorization) {
        try {
            JWTToken td = new JWTToken(Authorization);
            if (!td.isValid()) {
                return message.respondWithError("Authorization Error");
            }

            String userType = td.getUserType();

            if (!message.checkSaveStatus(userType, "5", applicationNo)) {
                return message.respondWithError(message.getMsg());
            }
            ArchitecturalDesignC obj = new ArchitecturalDesignC();
            ArchitecturalDesignCDetails details = new ArchitecturalDesignCDetails();
            ArchitecturalDesignDetailsPK pk = new ArchitecturalDesignDetailsPK();
            String jsonDataArray[] = message.jsonDataToStringArray(jsonData);
            try {
                message.map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonDataArray[0], new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {
                });
                obj.setBuildingType(message.map.get("buildingType").toString());
            } catch (Exception e) {
                //return message.respondWithError("Please provide buildingType");
            }
            String classId;
            obj.setEnterBy(td.getUserId());
            obj.setEnterDate(new Date());
            obj.setSerDate(null);
            obj.setErStatus("P");
            obj.setErDate(null);
            obj.setSerStatus("P");
            obj.setApplicationNo(applicationNo);
            row = da.save(obj);
            message.setHistory(applicationNo, td.getUserType(), "5", "S", td.getUserName());
            message.list = message.objectMapper.readValue(jsonDataArray[1], new com.fasterxml.jackson.core.type.TypeReference<List>() {
            });

            if (row == 1) {
                for (int i = 0; i < message.list.size(); i++) {
                    message.map = (Map) message.list.get(i);
                    classId = message.map.get("classId").toString();
                    details.setQty(message.map.get("qty").toString());
                    try {
                        details.setUnit(message.map.get("usedUnit").toString());
                    } catch (Exception e) {

                    }
                    try {
                        details.setRemark(message.map.get("remarks").toString());
                    } catch (Exception e) {
                    }
                    pk.setApplicationNo(applicationNo);
                    pk.setClassId(classId);
                    details.setPk(pk);
                    da.save(details);

                }
            }
            msg = da.getMsg();
            if (row > 0) {
                message.db.save(message.getEnterByStatus(userType, 5, applicationNo));
                message.map = new HashMap();//("6");
                message.map.put("message", "Success");
                return message.map;
            } else {
                if (msg.contains("Duplicate entry")) {
                    msg = "This record already exist";
                }
                return message.respondWithError(msg);
            }

        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
    }

    @Override
    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization) {
     return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "5");
    }
}