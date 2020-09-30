/*    map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonData, new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {});
 */
package com.service.utility;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.utility.DaoFormGroup;
import com.model.utility.FormGroup;

import model.Message;

@Service
public class FormGroupServiceImp implements FormGroupService {

@Autowired
DaoFormGroup da;
model.Message message = new model.Message();
String msg = "", sql;
int row;

@Override
public Object getAll(String id) {
    String ids[] = id.split("-");
//    for (String url1 : ids) {
//        System.out.println("part:  " + url1);
//    }
    String groupType=ids[0];
    String userType=ids[1];
    return da.getAll("from FormGroup where userType='" + userType + "' AND groupType='"+ groupType + "'  order by userType,groupPosition");
}

@Override
public Object save(FormGroup obj, String Authorization) {
    JWTToken td = new JWTToken(Authorization);
    if (!td.isValid()) {
        return new Message().respondWithError("Authorization Error");
    }
    try {

        try {
            sql = "SELECT coalesce(MAX(ID),0)+1 AS id FROM form_group";
            message.map = (Map) da.getRecord(sql).get(0);
            obj.setId(Integer.parseInt(message.map.get("id").toString()));
        } catch (Exception e) {
            return message.respondWithError("connection error or invalid table name");
        }
        row = da.save(obj);
        msg = da.getMsg().toLowerCase();
        if (row > 0) {
            return message.respondWithMessage("Success");
        } else if (msg.contains("duplicate key")) {
            msg = "This record already exist";
        }
        return message.respondWithError(msg);

    } catch (Exception e) {
        return message.respondWithError(e.getMessage());
    }
}

@Override
public Object update(FormGroup obj, String id, String Authorization) {
    JWTToken td = new JWTToken(Authorization);
    if (!td.isValid()) {
        return new Message().respondWithError("Authorization Error");
    }
    int formId = obj.getFormId().getId();
    int groupId = obj.getGroupId().getId();
    String userType = obj.getUserType().getId();
    String groupType = obj.getGroupType();
    sql = "UPDATE form_group SET form_id='" + formId + "',group_id='" + groupId + "',user_type='" + userType + "',group_type='" + groupType + "' WHERE CONCAT(form_id,'-',group_id,'-',user_type)='" + id + "'";
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
    sql = "DELETE FROM form_group WHERE ID IN (" + id + ")";
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
