package com.controller.rest.processing;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.application.ApplicationApproved;
import com.model.processing.PrathamCharanKaamGarneAnumati;
import com.service.processing.PrathamCharanKaamGarneAnumatiService;

@RestController
@CrossOrigin
@RequestMapping("api/Processing/PrathamCharanKaamGarneAnumati")
public class PrathamCharanKaamGarneAnumatiRestController {

	@Autowired
	PrathamCharanKaamGarneAnumatiService service;

	@GetMapping("/{id}")
	public Object index(@PathVariable long id, @RequestHeader(value = "Authorization") String Authorization) {
		return service.getAll(id);
	}

	@PostMapping("/{applicationNo}")
	public Object doSave(@PathVariable Long applicationNo, @RequestBody String jsonData, @RequestHeader(value = "Authorization") String Authorization) {
		PrathamCharanKaamGarneAnumati obj = new PrathamCharanKaamGarneAnumati();
		obj.setJsonData(jsonData);
		obj.setApplicationNo(applicationNo);
		return service.save(obj, Authorization);
	}

	@PutMapping("/{applicationNo}")
	public Object doApprove(@PathVariable Long applicationNo, @Valid @RequestBody ApplicationApproved approved,
			@RequestHeader(value = "Authorization") String Authorization) {
		return service.doApprove(applicationNo, approved, Authorization);
	}

}
