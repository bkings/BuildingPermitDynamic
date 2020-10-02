package com.service.dynamic;

import com.model.dynamic.EbpsTables;

public interface EbpsTablesServices {

	public Object getAll(String Authorization);

	public Object save(EbpsTables obj, String Authorization);

	public Object update(EbpsTables obj, String Authorization,long id);
	
	public Object delete(String id,String Authorization);
	
	public Object synchronizeTable(Long tableId);

}
