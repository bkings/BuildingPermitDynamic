/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.rest.utility;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.model.utility.MasonList;
import com.service.utility.MasonListService;

@RestController
@CrossOrigin
@RequestMapping("api/Utility/MasonList")
public class MasonListRestController {

    @Autowired
    MasonListService service;

    @GetMapping
    public Object index() {
        return service.getAll();
    }

    @PostMapping
     public Object doSave(@ModelAttribute MasonList obj, HttpServletRequest request, @RequestParam(required = false) MultipartFile photoMason,  @RequestHeader(value = "Authorization") String Authorization) throws IOException {
        return service.save(obj, photoMason, request, Authorization);
    }

    @PutMapping("/{id}")
    public Object doUpdate(@ModelAttribute MasonList obj, HttpServletRequest request, @RequestParam(required = false) MultipartFile photoMason, @PathVariable Integer id, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
        return service.update(obj, photoMason, request, id, Authorization);
    }

    @DeleteMapping("/{id}")
    public Object doDelete(@PathVariable String id, @RequestHeader(value = "Authorization") String Authorization) {
        return service.delete(id, Authorization);
    }

}
