/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.rest.processing;

import java.io.IOException;

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

//NaksaFilePathayekoBare

import com.model.application.ApplicationApproved;
import com.model.processing.NaksaFilePathayekoBare;
import com.service.processing.NaksaFilePathayekoBareService;

@RestController
@CrossOrigin
@RequestMapping("api/Processing/NaksaFilePathayekoBare")
public class NaksaFilePathayekoBareRestController {
   @Autowired
   NaksaFilePathayekoBareService service;
   @GetMapping("/{id}")
    public Object index(@PathVariable long id, @RequestHeader(value = "Authorization") String Authorization) {

        return service.getAll(Authorization, id);
    }

    @PostMapping("/{id}")
    public Object doSave(@PathVariable long id, @RequestBody String jsonData, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
        NaksaFilePathayekoBare obj = new NaksaFilePathayekoBare();
        obj.setApplicationNo(id);
        obj.setId(id);
        obj.setJsonData(jsonData);
        return service.save(obj, Authorization);
    }

    @PutMapping("/{applicationNo}")
    public Object doApprove(@PathVariable Long applicationNo, @Valid @RequestBody ApplicationApproved approved, @RequestHeader(value = "Authorization") String Authorization) {
        return service.doApprove(applicationNo, approved, Authorization);
    }
    
}
