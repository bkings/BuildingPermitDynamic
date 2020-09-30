/*    map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonData, new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {});
 */
package com.service.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.utility.DaoArchitecturalDesignBMaster;
import com.model.utility.ArchitecturalDesignBMaster;

@Service
public class ArchitecturalDesignBMasterServiceImp implements ArchitecturalDesignBMasterService {

@Autowired
DaoArchitecturalDesignBMaster da;
model.Message message = new model.Message();
String msg = "", sql;
int row;

@Override
public Object getAll() {
    return da.getAll("from ArchitecturalDesignBMaster order by id");
}

@Override
public Object save(ArchitecturalDesignBMaster obj, String Authorization) {
    JWTToken td = new JWTToken(Authorization);
    if (!td.isStatus()) {
        return message.respondWithError("invalid token");
    }
    try {
//        row = da.save(obj);
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
public Object update(ArchitecturalDesignBMaster obj, long id, String Authorization) {
    JWTToken td = new JWTToken(Authorization);
    if (!td.isStatus()) {
        return message.respondWithError("invalid token");
    }
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
    if (!td.isStatus()) {
        return message.respondWithError("invalid token");
    }

    id = "'" + id.replace(",", "','") + "'";
    sql = "DELETE FROM architectural_design_b_master WHERE ID IN (" + id + ")";
//    row = da.delete(sql);
    msg = da.getMsg();
    if (row > 0) {
        return message.respondWithMessage("Success");
    } else if (msg.contains("foreign key")) {
        msg = "this record already used in reference tables, Cannot delete of this record";
    }
    return message.respondWithError(msg);
}
}
