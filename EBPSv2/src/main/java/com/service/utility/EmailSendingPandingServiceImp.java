package com.service.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.utility.DaoEmailSendingPanding;
import com.model.utility.EmailSendingPanding;

@Service
public class EmailSendingPandingServiceImp implements EmailSendingPandingService {

    @Autowired
    DaoEmailSendingPanding da;

    @Override
    public Object getAll() {
        return da.getRecord("SELECT date_time \"dateTime\",email,body,subject,application_no \"applicationNo\",CONCAT(application_no,email,date_time) AS id FROM email_sending_panding WHERE status='Y'");
    }

    @Override
    public Object save(EmailSendingPanding obj, String Authorization) {
        model.Message message = new model.Message();
        String msg = "";
        int row;
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        try {

            row = da.save(obj);
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
    public Object update(String id, String Authorization) {
        model.Message message = new model.Message();
        try {

            String sql = "UPDATE email_sending_panding SET status='S' WHERE CONCAT(application_no,email,date_time)='" + id + "'";
            int row = da.delete(sql);
            String msg = da.getMsg();
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

}
