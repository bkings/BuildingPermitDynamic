package com.service.dynamic;

import com.model.utility.FormNameMaster;

public interface FormMasterServices {

	public Object getAll(String Authorization);
	
	public Object getById(String Authorization, String formId);

	public Object save(FormNameMaster obj, String Authorization);

	public Object update(FormNameMaster obj, String Authorization, long id);

	public Object delete(String id, String Authorization);

	public Object deleteFields(String id, String Authorization);
	
	public Object deleteFormPermissions(String id, String Authorization);

}
