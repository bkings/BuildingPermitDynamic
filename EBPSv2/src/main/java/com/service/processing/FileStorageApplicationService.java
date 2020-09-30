package com.service.processing;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageApplicationService {

public Object getAll(Long applicationNo);

public Object save(HttpServletRequest request, Long applicationNo,Long fileType, MultipartFile[] file, String Authorization);

public Object delete(String url, String Authorization);

}
