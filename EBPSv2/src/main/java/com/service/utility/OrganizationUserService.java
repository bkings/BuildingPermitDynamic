package com.service.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.model.utility.OrganizationUser;

public interface OrganizationUserService {

	public Object getAll();

	public Object getAll(String Authorization);

	public Object save(HttpServletRequest request, OrganizationUser obj, MultipartFile userImage, MultipartFile userStamp, MultipartFile userSignature,
			String Authorization);

	public Object update(HttpServletRequest request, Long id, OrganizationUser obj, MultipartFile userImage, MultipartFile userStamp,
			MultipartFile userSignature, String Authorization);

	public Object getAllRenew();

	public Object renew(HttpServletRequest request, long id, String billNo, String tillDate, MultipartFile file, String fiscalYear, String Authorization);

	public Object resend(HttpServletRequest request, String Authorization, String id);

}
