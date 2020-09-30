package com.dao.processing;

import java.util.List;

import com.model.processing.FileStorageApplication;
import com.model.processing.NoticePaymentApplication;

public interface DaoFileStorageApplication {

    public List<FileStorageApplication> getAll(String hql);

    public List<NoticePaymentApplication> getNoticePaymentApplication(String hql);

    public int save(FileStorageApplication obj);

    public int save(NoticePaymentApplication obj);

    public int delete(String sql);

    public String getMsg();
}
