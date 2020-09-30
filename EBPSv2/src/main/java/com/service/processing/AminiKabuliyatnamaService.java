package com.service.processing;

import com.model.processing.AminiKabuliyatnama;

public interface AminiKabuliyatnamaService {

public Object getAll(long id);

public Object save(AminiKabuliyatnama obj, String Authorization);

public Object update(AminiKabuliyatnama obj, long id, String Authorization);



}
