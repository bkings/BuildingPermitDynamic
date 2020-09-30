/*    map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonData, new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {});
 */
package com.service.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.utility.DaoFiscalYear;
import com.model.utility.FiscalYear;

import model.Message;

@Service
public class FiscalYearServiceImp implements FiscalYearService {

    @Autowired
    DaoFiscalYear da;
    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    @Override
    public Object getAll() {
        return da.getAll("from FiscalYear");
    }

    @Override
    public Object save(FiscalYear obj, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return new Message().respondWithError("Authorization Error");
        }
        try {
            String enteredStatus = obj.getStatus();
            if (enteredStatus.equalsIgnoreCase("Y")) {
                sql = "FROM FiscalYear WHERE status='Y'";
                List l = da.getAll(sql);

                if (!l.isEmpty()) {
                    return message.respondWithError("Already have an active fiscal year");
                }
            }
            row = da.save(obj);
            msg = da.getMsg();
            if (row > 0) {
                return message.respondWithMessage("Success");
            } else if (msg.contains("Duplicate entry")) {
                msg = "This record already exist";
            } else if (msg.contains("start_date")) {
                msg = "invalid start date";
            } else if (msg.contains("end_date")) {
                msg = "invalid end date";
            }
            return message.respondWithError(msg);

        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
    }

    @Override
    public Object update(FiscalYear obj, long id, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return new Message().respondWithError("Authorization Error");
        }
        String enteredStatus = obj.getStatus();
        if (enteredStatus.equalsIgnoreCase("Y")) {
            sql = "FROM FiscalYear WHERE status='Y'";
            List l = da.getAll(sql);
            if (!l.isEmpty()) {
                return message.respondWithError("Already have an active fiscal year");
            }
        }
        //row = da.save(obj);
        row = da.update(obj);
        //sql="UPDATE fiscal_year SET year_code='"+obj.getYearCode()+"',year_name='"+obj.getYearName()+"',start_date='"+obj.getStartDate()+"',end_date='"+obj.getEndDate()+"',status='"+obj.getStatus()+"' WHERE year_code='"+id+"'";
        //row = da.delete(sql);
        msg = da.getMsg();
        if (row > 0) {
            return message.respondWithMessage("Success");
        } else if (msg.contains("Duplicate entry")) {
            msg = "This record already exist";
        } else if (msg.contains("start_date")) {
            msg = "invalid start date";
        } else if (msg.contains("end_date")) {
            msg = "invalid end date";
        }
        return message.respondWithError(msg);
    }

    @Override
    public Object delete(String id, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return new Message().respondWithError("Authorization Error");
        }
        System.out.println("id=" + id);
        id = "'" + id.replace(",", "','") + "'";
        System.out.println("id1=" + id);
        sql = "DELETE FROM fiscal_year WHERE year_code IN (" + id + ")";
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
