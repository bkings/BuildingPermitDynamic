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

import com.model.utility.FormGroup;
import com.service.utility.FormGroupService;

@RestController
@CrossOrigin
@RequestMapping("api/Utility/FormGroup")
public class FormGroupRestController {

    @Autowired
    FormGroupService service;

    @GetMapping("/{id}")
    
    public Object index( @PathVariable String id,@RequestHeader(value = "Authorization") String Authorization) {
        return service.getAll(id);
    }

    @PostMapping
    public Object doSave(@RequestBody FormGroup obj, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
        return service.save(obj, Authorization);
    }

    @PutMapping("/{id}")
    public Object doUpdate(@RequestBody FormGroup obj, @PathVariable String id, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
        return service.update(obj,id,Authorization);
    }

    @DeleteMapping("/{id}")
    public Object doDelete(@PathVariable String id,@RequestHeader(value = "Authorization") String Authorization) {
        return service.delete(id,Authorization);
    }
}
