package com.controller.rest.dynamic;

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
import com.service.dynamic.FormMasterServices;

@RestController
@CrossOrigin
@RequestMapping("api/Dynamic/FormNameMaster")
public class FormMasterRestController {

	@Autowired
	FormMasterServices service;

	@GetMapping
	public Object getAll(@RequestHeader String Authorization) {
		return service.getAll(Authorization);
	}

	@PostMapping
	public Object save(@RequestBody FormNameMaster obj, @RequestHeader String Authorization) {
		return service.save(obj, Authorization);
	}

	@PutMapping("/{id}")
	public Object update(@RequestBody FormNameMaster obj, @RequestHeader String Authorization, @PathVariable long id) {
		return service.update(obj, Authorization, id);
	}

	@DeleteMapping("/{id}")
	public Object delete(@PathVariable String id, @RequestHeader String Authorization) {
		return service.delete(id, Authorization);
	}

}
