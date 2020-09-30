/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.model.processing.SuperStructureConstruction;
import com.service.processing.SuperStructureConstructionService;

@RestController
@CrossOrigin
@RequestMapping("api/Processing/SuperStructureConstruction")
public class SuperStructureConstructionRestController {

    @Autowired
    SuperStructureConstructionService service;

    @GetMapping("/{applicationNo}")
    public Object show(@PathVariable Long applicationNo) {
        return service.getAll(applicationNo);
    }

    @PostMapping("/{applicationNo}")
    public Object doSave(@PathVariable Long applicationNo, @RequestBody String jsonData, @RequestHeader(value = "Authorization") String Authorization) {

        SuperStructureConstruction obj = new SuperStructureConstruction();
        obj.setJsonData(jsonData);
        obj.setApplicationNo(applicationNo);

        return service.save(obj, Authorization);

    }

    @PutMapping("/{applicationNo}")
    public Object doApprove(@PathVariable Long applicationNo, @Valid @RequestBody ApplicationApproved approved, @RequestHeader(value = "Authorization") String Authorization) {
        return service.doApprove(applicationNo, approved, Authorization);
    }

}
