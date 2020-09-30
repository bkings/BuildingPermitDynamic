/*    map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonData, new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {});
 */
package com.service.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.utility.DaoRajashowMaster;
import com.model.utility.RajashowMaster;

@Service
public class RajashowMasterServiceImp implements RajashowMasterService {

    @Autowired
    DaoRajashowMaster da;
    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getAll(String wardNo) {
        if (wardNo.length() == 2) {
            wardNo = " where wardNo='" + wardNo + "'";
        } else {
            wardNo = "";
        }
        return da.getAll("from RajashowMaster " + wardNo + " order by wardNo,area,floor");
    }

    @Override
    public Object save(List<RajashowMaster> list, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        try {
            row = 0;
            RajashowMaster obj;
            for (int i = 0; i < list.size(); i++) {
                obj = new RajashowMaster(list.get(i).getWardNo(), list.get(i).getFloor(), list.get(i).getFloorType(), list.get(i).getArea(), list.get(i).getDharoutiRate(), list.get(i).getDasturRate(), list.get(i).getConstructionType());
                row += da.save(obj);
            }
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
    public Object delete(String id, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }

        id = "'" + id.replace(",", "','") + "'";
        sql = "DELETE FROM rajashow_master WHERE CONCAT(ward_No,'-',floor,'-',floor_Type,'-',area) IN (" + id + ")";
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
