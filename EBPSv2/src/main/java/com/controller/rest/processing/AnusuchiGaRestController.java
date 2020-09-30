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
import com.service.processing.AnusuchiGaService;

@RestController
@CrossOrigin
@RequestMapping("api/Processing/Phase1/AnusuchiGa")
public class AnusuchiGaRestController {

@Autowired
AnusuchiGaService service;

@GetMapping("/{applicationNo}")
public Object index(@PathVariable String applicationNo, @RequestHeader(value = "Authorization") String Authorization) {
    return service.getIndex(Authorization,applicationNo);
}

@PostMapping("/{applicationNo}")
public Object doSave(@PathVariable Long applicationNo, @RequestHeader(value = "Authorization") String Authorization) {
    return service.save(applicationNo, Authorization);
}

@PutMapping("/{applicationNo}")
public Object doApprove(@PathVariable Long applicationNo, @Valid @RequestBody ApplicationApproved approved, @RequestHeader(value = "Authorization") String Authorization) {
    return service.doApprove(applicationNo, approved, Authorization);
}
}
