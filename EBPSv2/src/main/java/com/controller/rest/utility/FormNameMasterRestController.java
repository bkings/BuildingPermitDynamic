package com.controller.rest.utility;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.utility.FormNameMaster;
import com.service.utility.FormNameMasterService;

@RestController
@RequestMapping("api/Utility/FormNameMaster")
@CrossOrigin
public class FormNameMasterRestController {

	@Autowired
	FormNameMasterService service;

	@GetMapping
	public Object index(@RequestHeader(value = "Authorization") String Authorization) {
		return service.getAll();
	}

	@PostMapping
	public Object doSave(@RequestBody FormNameMaster obj, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
		return service.save(obj, Authorization);
	}

	@PutMapping("/{id}")
	public Object doUpdate(@RequestBody FormNameMaster obj, @PathVariable long id, @RequestHeader(value = "Authorization") String Authorization)
			throws IOException {
		return service.update(obj, id, Authorization);
	}

	@DeleteMapping("/Fields/{id}")
	public Object deleteFields(@PathVariable String id, @RequestHeader String Authorization) {
		return service.deleteFields(id, Authorization);
	}

}
