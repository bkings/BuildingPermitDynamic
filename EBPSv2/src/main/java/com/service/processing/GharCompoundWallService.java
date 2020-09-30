package com.service.processing;

import com.model.processing.GharCompoundWall;

public interface GharCompoundWallService {

public Object getAll(long id);

public Object save(GharCompoundWall obj, String Authorization);

public Object update(GharCompoundWall obj, long id, String Authorization);



}
