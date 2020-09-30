package com.controller.rest.utility;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.utility.OrganizationMaster;
import com.service.utility.OrganizationMasterService;

@RestController
@CrossOrigin
@RequestMapping("api/Utility/OrganizationMaster")
public class OrganizationMasterRestController {

@Autowired
OrganizationMasterService service;

@GetMapping
public Object index() {
    return service.getRecord();
}

@GetMapping("/Configuration")
public Object configuration() {
    return service.applicationConfiguration();
}

@PostMapping
public Object doSave(@RequestBody OrganizationMaster obj, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
    return service.Save(obj, Authorization);
}

@PutMapping
public Object update(@RequestBody OrganizationMaster obj, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
    return service.update(obj, Authorization);
}
}
