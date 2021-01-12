package com.controller.rest.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dao.utility.DaoOrganizationUser;
import com.model.utility.OrganizationUser;
import com.service.utility.OrganizationUserService;

@RestController
@CrossOrigin
@RequestMapping("api/Utility/OrganizationUser")
public class OrganizationUserRestController {

	@Autowired
	OrganizationUserService service;

	@Autowired
	DaoOrganizationUser da;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping
	public Object index() {

		return service.getAll();
	}

	@GetMapping("/DesignerRenewStatus")
	public Object renewIndex() {
		return service.getAllRenew();
	}

	@GetMapping("/Info")
	public Object index(@RequestHeader(value = "Authorization") String Authorization) {
		return service.getAll(Authorization);
	}

	@PostMapping
	public Object doSave(HttpServletRequest request, @ModelAttribute OrganizationUser obj, @RequestParam(required = false) MultipartFile userImage,
			@RequestParam(required = false) MultipartFile userStamp, @RequestParam(required = false) MultipartFile userSignature,
			@RequestHeader(value = "Authorization") String Authorization) {
		return service.save(request, obj, userImage, userStamp, userSignature, Authorization);
	}

	@PostMapping("/{id}")
	public Object doUpdate(HttpServletRequest request, @PathVariable long id, @ModelAttribute OrganizationUser obj,
			@RequestParam(required = false) MultipartFile userImage, @RequestParam(required = false) MultipartFile userStamp,
			@RequestParam(required = false) MultipartFile userSignature, @RequestHeader(value = "Authorization") String Authorization) {
		return service.update(request, id, obj, userImage, userStamp, userSignature, Authorization);
	}

	@PostMapping("/Renew")
	public Object doDesignerRenew(HttpServletRequest request, @RequestParam String id, @RequestParam String billNo, @RequestParam String tillDate,
			@RequestParam String fiscalYear, @RequestParam(required = false) MultipartFile file, @RequestHeader(value = "Authorization") String Authorization)
			throws IOException {

		return service.renew(request, Long.parseLong(id), billNo, tillDate, file, fiscalYear, Authorization);
	}

	@GetMapping("/Resend")
	public Object resendEmail(HttpServletRequest request, @RequestHeader String Authorization, @RequestParam String loginId) {
		return service.resend(request, Authorization, loginId);
	}

	@GetMapping("/GetallEnc")
	public Object getEncpwds() {
		String sql = "SELECT db_password pwd,login_id \"loginId\" FROM organization_user";
		List list = new ArrayList<>();
		Map map = new HashMap<>(), retMap = new HashMap<>();
		list = da.getRecord(sql);
		for (int i = 0; i < list.size(); i++) {
			map = (Map) list.get(i);
			retMap.put(map.get("loginId").toString() + " " + map.get("pwd").toString(), encoder.encode(map.get("pwd").toString()));
			sql = "UPDATE organization_user SET db_password='"+encoder.encode(map.get("pwd").toString())+"' WHERE login_id='"+map.get("loginId").toString()+"'";
			da.delete(sql);
		}
		return retMap;
	}
}
