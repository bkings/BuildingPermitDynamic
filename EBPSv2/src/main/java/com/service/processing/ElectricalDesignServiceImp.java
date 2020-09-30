package com.service.processing;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.DaoElectricalDesign;
import com.dao.processing.DaoImpElectricalDesign;
import com.model.application.ApplicationApproved;
import com.model.processing.ArchitecturalDesignDetailsPK;
import com.model.processing.ElectricalDesign;
import com.model.processing.ElectricalDesignDetails;

import model.DB;

@Service
public class ElectricalDesignServiceImp implements ElectricalDesignService {

    DaoElectricalDesign da = new DaoImpElectricalDesign();
    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getindex(long applicationNo) {
        DB db = new DB();
        Map map;
        List list;
        sql = "SELECT R.ENTER_BY \"enterBy\",R.ENTER_DATE \"enterDate\",R.ER_DATE \"erDate\",R.ER_NAME \"erName\",R.ER_STATUS \"erStatus\",R.SER_DATE \"serDate\",R.SER_NAME \"serName\",R.SER_STATUS \"serStatus\" FROM building_permit_application A LEFT JOIN electrical_design R ON A.id=R.application_no WHERE A.id='" + applicationNo + "'";
        list = db.getRecord(sql);
        if (list.isEmpty()) {
            return message.respondWithError("invalid application no");
        }
        map = (Map) list.get(0);
        sql = "SELECT M.group_name \"groupName\" ,M.id AS \"elementId\",M.\"data\" element,M.unit,coalesce(D.qty,'') AS \"qty\",coalesce(D.remark,'') AS remark,coalesce(D.unit,'') AS \"usedUnit\" FROM electrical_design_requirement_master M ,electrical_design_details D WHERE M.id=D.class_id AND D.application_no='" + applicationNo + "' "
                + "UNION "
                + "SELECT M.group_name \"groupName\",M.id AS \"elementId\",M.\"data\" element,M.unit,'0' AS \"qty\",'' AS remark,'' AS \"usedUnit\" FROM electrical_design_requirement_master M WHERE M.id NOT IN(SELECT D.class_id FROM electrical_design_details D WHERE D.application_no='" + applicationNo + "') order by  \"elementId\"";
        list = db.getRecord(sql);
        map.put("details", list);
        map.put("comment", message.getComment("" + applicationNo, "8"));
        map.put("history", message.getHistory(applicationNo, "8"));
        return map;
    }

    @Override
    public Object save(Long applicationNo, String jsonData, String Authorization) {
        try {
            JWTToken td = new JWTToken(Authorization);
            if (!td.isValid()) {
                return message.respondWithError("Authorization Error");
            }
            String userType = td.getUserType();
            if (!message.checkSaveStatus(userType, "8", applicationNo)) {
                return message.respondWithError(message.getMsg());
            }
            ElectricalDesign obj = new ElectricalDesign();
            ElectricalDesignDetails details = new ElectricalDesignDetails();
            ArchitecturalDesignDetailsPK pk = new ArchitecturalDesignDetailsPK();
            String jsonDataArray[] = message.jsonDataToStringArray(jsonData);
            Map map;
            obj.setEnterBy(td.getUserId());
            obj.setEnterDate(new Date());
            obj.setSerDate(null);
            obj.setErStatus("P");
            obj.setErDate(null);
            obj.setSerStatus("P");
            obj.setApplicationNo(applicationNo);
            row = da.save(obj);

            message.setHistory(applicationNo, td.getUserType(), "8", "S", td.getUserName());

            if (row == 0) {
                return message.respondWithError(da.getMsg());
            } else {
                message.list = message.objectMapper.readValue(jsonDataArray[1], new com.fasterxml.jackson.core.type.TypeReference<List>() {
                });
                String elementId;
                for (int i = 0; i < message.list.size(); i++) {
                    map = (Map) message.list.get(i);
                    elementId = map.get("elementId").toString();
                    details.setQty(map.get("qty").toString());
                    try {
                        details.setRemark(map.get("remark").toString());
                    } catch (Exception e) {

                    }
                    try {
                        details.setUnit(map.get("usedUnit").toString());
                    } catch (Exception e) {

                    }
                    pk.setApplicationNo(applicationNo);
                    pk.setClassId(elementId);
                    details.setPk(pk);
                    da.save(details);
                }
            }

            if (row > 0) {
                message.db.save(message.getEnterByStatus(userType, 8, applicationNo));
                message.map = new HashMap();//("12");
                message.map.put("message", "Success");
                return message.map;
            } else {
                if (msg.contains("Duplicate entry")) {
                    msg = "This record already exist";
                }
                return message.respondWithError(msg);
            }

        } catch (Exception e) {
            return message.respondWithError("String " + e.getMessage());
        }
    }

    @Override
    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization) {
    return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "8");
    }
}