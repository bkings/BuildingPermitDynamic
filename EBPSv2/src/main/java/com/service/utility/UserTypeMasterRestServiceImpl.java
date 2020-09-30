/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.utility.DaoUserTypeMaster;
import com.model.utility.UserTypeMaster;

import model.Message;

@Service
public class UserTypeMasterRestServiceImpl implements UserTypeMasterRestService {

    @Autowired
    DaoUserTypeMaster da;
    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    @Override
    public Object index() {

//        List<UserTypeMaster> list = new ArrayList();
//        list.add(new UserTypeMaster("C", "Chief", 4, "chief_", "chief_"));
//        list.add(new UserTypeMaster("A", "Engineer", 3, "er_", "engineer_"));
//        list.add(new UserTypeMaster("B", "Sub Engineer", 2, "ser_", "sub_engineer_"));
//        list.add(new UserTypeMaster("D", "Designer", 1, "", "designer_"));
//        list.add(new UserTypeMaster("AD", "Amin", 1, "amini_", "amin_"));
//        list.add(new UserTypeMaster("R", "Rajasow", 1, "rw_", "rajasow_"));
//        list.add(new UserTypeMaster("E", "Rajasow", 1, "poste_", "poste_"));
//        list.add(new UserTypeMaster("F", "Rajasow", 1, "postf_", "postf_"));
//        list.add(new UserTypeMaster("G", "Rajasow", 1, "postg_", "postg_"));
//        list.add(new UserTypeMaster("ADM", "ADMIN", 5, "", ""));
//        list.add(new UserTypeMaster("TADM", "T-ADMIN", 5, "", ""));
////        for (int i = 0; i < list.size(); i++) {
////            da.save(list.get(i));
////        }
//        return list;
        message.list = da.getAll("from UserTypeMaster order by hierarchy ");
        return message.list;
    }

    @Override
    public Object doUpdate(String Authorization, UserTypeMaster obj) {

        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return new Message().respondWithError("Authorization Error");
        }

        row = da.save(obj);
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

}
