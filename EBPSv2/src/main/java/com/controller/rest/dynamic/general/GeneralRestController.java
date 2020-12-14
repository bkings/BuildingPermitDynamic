package com.controller.rest.dynamic.general;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.application.ApplicationApproved;
import com.service.dynamic.general.GeneralServices;

import model.Message;

@RestController
@CrossOrigin
@RequestMapping("api/Dynamic/General")
public class GeneralRestController {

	@Autowired
	GeneralServices service;

	Message message = new Message();

	String msg = "", sql;
	int row;

	@GetMapping("/{applicationNo}")
	public ResponseEntity<Object> getAll(@PathVariable Long applicationNo, @RequestHeader String Authorization, @RequestParam String formId) {
		return ResponseEntity.ok(service.getAll(applicationNo, Authorization, formId));
	}

	@PostMapping("/{applicationNo}")
	public ResponseEntity<Object> save(@RequestBody Object obj, @PathVariable Long applicationNo, @RequestParam String formId,
			@RequestParam(defaultValue="") String hasRevised, @RequestHeader String Authorization) {
		return ResponseEntity.ok(service.save(obj, applicationNo, formId, hasRevised, Authorization));
	}

	@PutMapping("/{applicationNo}")
	public Object approve(@PathVariable Long applicationNo, @Valid @RequestBody ApplicationApproved obj, @RequestHeader String Authorization,
			@RequestParam String formId) {
		return service.approve(applicationNo, obj, Authorization, formId);
	}

}
