/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.rest.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.utility.FormReportService;

@RestController
@CrossOrigin
@RequestMapping("api/Utility/FormReport")

public class FormReportRestController {

    @Autowired
    FormReportService service;

    @GetMapping("/{id}")
    public Object index(@PathVariable String id, @RequestParam(required = false) String constructionType, @RequestParam(required = false) String designer, @RequestParam String wardNo, @RequestHeader(value = "Authorization") String Authorization) {
        return service.getAll(id, designer, wardNo, constructionType, Authorization);
    }

}
