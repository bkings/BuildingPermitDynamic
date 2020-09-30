
package com.service.application;


import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.model.application.ApplicationsComments;

@Service
public class CommentHistoryServiceImp implements CommentHistoryService {

model.Message message = new model.Message();

java.util.List<ApplicationsComments> list = new ArrayList();
String sql;

public Object getComment(long applicationNo) {
    sql = "SELECT cast(C.comment_date as time) AS \"actionTime\",get_bsdate(C.comment_date) AS \"actionDate\",F.\"name\",user_type \"userType\",C.remark,comment,comment_by FROM applications_comments C,form_name_master  F WHERE CONCAT(C.page,'')=CONCAT(F.id,'') AND application_no=" + applicationNo;
    return message.db.getRecord(sql);

}

@Override
public Object getHistory(long applicationNo) {
    sql = "SELECT cast(action_date as time) AS \"actionTime\",get_bsdate(H.action_date) AS \"actionDate\",F.\"name\",user_type \"userType\",H.enter_by \"enterBy\",status FROM application_history H,form_name_master  F WHERE CONCAT(H.page_no,'')=CONCAT(F.id,'') AND application_no=" + applicationNo;
    return message.db.getRecord(sql);
}
}
