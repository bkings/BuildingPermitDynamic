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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.dynamic.EbpsTables;
import com.service.dynamic.EbpsTablesServices;

@RestController
@CrossOrigin
@RequestMapping("api/Dynamic/EbpsTables")
public class EbpsTablesRestController {

	@Autowired
	EbpsTablesServices service;

	@GetMapping
	public Object getAll(@RequestHeader String Authorization) {
		return service.getAll(Authorization);
	}

	@PostMapping
	public Object save(@RequestBody EbpsTables obj, @RequestHeader String Authorization) {
		return service.save(obj, Authorization);
	}

	@PutMapping("/{id}")
	public Object update(@RequestBody EbpsTables obj, @RequestHeader String Authorization, @PathVariable long id) {
		return service.update(obj, Authorization, id);
	}

	@DeleteMapping("/{id}")
	public Object delete(@PathVariable String id, @RequestHeader String Authorization) {
		return service.delete(id, Authorization);
	}
	
	@GetMapping("/sync")
	public Object synchronize(@RequestParam Long tableId) {
		return service.synchronizeTable(tableId);
	}

}
