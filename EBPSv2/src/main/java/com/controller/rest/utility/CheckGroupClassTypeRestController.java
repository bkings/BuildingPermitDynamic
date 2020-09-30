/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import com.model.utility.CheckClassGroup;
import com.service.utility.CheckGroupClassService;

@RestController
@CrossOrigin
@RequestMapping("api/Utility/ClassGroup")
public class CheckGroupClassTypeRestController {

    @Autowired
    CheckGroupClassService service;

    @GetMapping
    public Object index(@RequestHeader(value = "Authorization") String Authorization) {
        return service.getAll(Authorization);
    }

    @PostMapping
    public Object doSave(@RequestBody String obj, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
        return service.save(obj, Authorization);
    }

    @PutMapping("/{id}")
    public Object doUpdate(@RequestBody CheckClassGroup obj, @PathVariable long id, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
        return service.update(obj, id, Authorization);
    }

    @DeleteMapping("/{id}")
    public Object doDelete(@PathVariable String id, @RequestHeader(value = "Authorization") String Authorization) {
        return service.delete(id, Authorization);
    }

}
