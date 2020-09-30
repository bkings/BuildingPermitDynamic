package com.dao.application;

import com.model.application.PublicDownloads;

public interface DaoPublicDownloads {

    public int update(PublicDownloads obj);

    public String getMsg();

    public int delete(String sql);
    
}
