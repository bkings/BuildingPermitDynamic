
package com.dao.utility;

import java.util.List;

import com.model.utility.FormGroup;

public interface DaoFormGroup {

    public List<FormGroup> getAll(String hql);

    public int save(FormGroup obj);

    public int update(FormGroup obj);

    public int delete(String sql);

    public List getRecord(String sql);

    public String getMsg();
}
