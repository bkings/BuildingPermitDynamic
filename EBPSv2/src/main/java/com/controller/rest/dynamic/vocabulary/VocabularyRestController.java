package com.controller.rest.dynamic.vocabulary;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.model.dynamic.EbpsTables;
import com.model.dynamic.vocabulary.Vocabulary;
import com.service.dynamic.vocabulary.VocabularyService;

@RestController
@CrossOrigin
@RequestMapping("api/Dynamic/Vocabulary")
public class VocabularyRestController {

	@Autowired
	VocabularyService service;

	@GetMapping
	public Object getAll(@RequestHeader String Authorization) {
		return service.getAll(Authorization);
	}

	@PostMapping
	public Object save(@RequestBody Vocabulary obj, @RequestHeader String Authorization) {
		return service.save(obj, Authorization);
	}

	@DeleteMapping("/{id}")
	public Object delete(@PathVariable String id, @RequestHeader String Authorization) {
		return service.delete(id, Authorization);
	}

	@DeleteMapping("/Details/{id}")
	public Object deleteDetails(@PathVariable String id, @RequestHeader String Authorization) {
		return service.deleteDetails(id, Authorization);
	}

}
