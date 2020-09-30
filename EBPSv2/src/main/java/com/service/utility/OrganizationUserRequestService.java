package com.service.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.model.utility.OrganizationUserRequest;

public interface OrganizationUserRequestService {

    public Object getAll(String Authorization);

    public Object save(HttpServletRequest request, OrganizationUserRequest obj, MultipartFile userImage, MultipartFile userStamp, MultipartFile userSignature, String Authorization);

    public Object update(HttpServletRequest request, Long id, OrganizationUserRequest obj, MultipartFile userImage, MultipartFile userStamp, MultipartFile userSignature, String Authorization);

    public Object approve(HttpServletRequest request, long id, String Authorization);

}
