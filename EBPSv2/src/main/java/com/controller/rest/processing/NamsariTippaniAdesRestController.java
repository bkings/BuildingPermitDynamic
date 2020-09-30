/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.rest.processing;

import java.util.Map;

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
import com.model.processing.NamsariTippaniAdes;
import com.service.processing.NamsariTippaniAdesService;

import model.DB;

@RestController
@CrossOrigin
@RequestMapping("api/Processing/NamsariTippaniAdes")

public class NamsariTippaniAdesRestController {

    @Autowired
    NamsariTippaniAdesService service;

    @GetMapping("/{applicationNo}")
    public Object show(@PathVariable Long applicationNo) {
        return service.getAll(applicationNo);
    }

    @PostMapping("/{id}")
    public Object doSave(@PathVariable Long id, @RequestBody String jsonData, @RequestHeader(value = "Authorization") String Authorization) {
        NamsariTippaniAdes obj = new NamsariTippaniAdes();
        obj.setJsonData(jsonData);
        String sql = "SELECT application_no AS id FROM building_permit_application_name_transafer WHERE id='" + id + "'";
        DB db = new DB();
        Map map = (Map) db.getRecord(sql).get(0);
        obj.setApplicationNo(Long.parseLong(map.get("id").toString()));
        obj.setId(id);
        return service.save(obj, Authorization);

    }

    @PutMapping("/{applicationNo}")
    public Object doApprove(@PathVariable Long applicationNo, @Valid @RequestBody ApplicationApproved approved, @RequestHeader(value = "Authorization") String Authorization) {
        return service.doApprove(applicationNo, approved, Authorization);
    }

}
