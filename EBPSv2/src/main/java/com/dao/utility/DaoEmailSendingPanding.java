
package com.dao.utility;

import java.util.List;

import com.model.utility.EmailSendingPanding;

public interface DaoEmailSendingPanding {

    public List<EmailSendingPanding> getAll(String hql);

    public int save(EmailSendingPanding obj);

    public int update(EmailSendingPanding obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}
