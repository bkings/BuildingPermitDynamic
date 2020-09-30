package com.dao.dynamic;

import com.model.dynamic.ApplicationStatus;

public interface DaoApplicationStatus {

	public int save(ApplicationStatus obj);

	public String getMsg();

}
