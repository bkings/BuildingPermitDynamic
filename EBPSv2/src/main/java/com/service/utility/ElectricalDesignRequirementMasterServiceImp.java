/*    map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonData, new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {});
 */
package com.service.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.utility.DaoElectricalDesignRequirementMaster;
import com.model.utility.ElectricalDesignRequirementMaster;

@Service
public class ElectricalDesignRequirementMasterServiceImp implements ElectricalDesignRequirementMasterService {

@Autowired
DaoElectricalDesignRequirementMaster da;
model.Message message = new model.Message();
String msg = "", sql;
int row;

@Override
public Object getAll() {
    return da.getAll("from ElectricalDesignRequirementMaster order by id");
}

@Override
public Object save(ElectricalDesignRequirementMaster obj, String Authorization) {
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
public Object update(ElectricalDesignRequirementMaster obj, String Authorization) {
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
    sql = "DELETE FROM electrical_design_requirement_master WHERE ID IN (" + id + ")";
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
