/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.rest.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.application.DaoImpBuildingPermitApplication;

@RestController
@RequestMapping("api/Report")
@CrossOrigin
public class ReportRestController {

    @Autowired
    DaoImpBuildingPermitApplication da;

    @GetMapping("/Application")
    public ResponseEntity designer(@RequestParam(required = false) Long applicantYear, @RequestParam String designer, @RequestParam String wardNo, @RequestParam String applicationStatus, @RequestHeader(value = "Authorization") String Authorization) {
        if (designer.length() > 0) {
            designer = " and enterBy='" + designer + "'";
        }
        if (wardNo.length() > 0) {
            wardNo = " and newWardNo='" + wardNo + "'";
        }
        if (applicationStatus.length() > 0) {
            applicationStatus = " and applicationStatus='" + applicationStatus + "'";
        }
//        BuildingPermitApplication obj;
        String sql = "from BuildingPermitApplication where applicantYear=coalesce(" + applicantYear + ",applicantYear)  " + designer + wardNo + applicationStatus + " order by enterDate";
        return ResponseEntity.ok(da.getAll(sql));
    }

}
