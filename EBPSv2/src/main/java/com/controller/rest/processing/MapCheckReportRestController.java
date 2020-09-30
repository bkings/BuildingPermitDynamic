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

import com.model.application.ApplicationApproved;
import com.service.processing.MapCheckReportService;

@RestController
@CrossOrigin
@RequestMapping("api/Processing/Phase1/MapCheckReport")
public class MapCheckReportRestController {

model.Message message = new model.Message();
String msg = "", sql;
int row;
@Autowired
MapCheckReportService service;

@GetMapping("/{applicationNo}")
public Object index(@PathVariable long applicationNo) {
    return service.getindex(applicationNo);
}

@PostMapping("/{applicationNo}")
public Object doSave(@PathVariable Long applicationNo, @RequestBody String jsonData, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
    return service.save(applicationNo, jsonData, Authorization);
}
@PutMapping("/{applicationNo}")
public Object doApprove(@PathVariable Long applicationNo, @Valid @RequestBody ApplicationApproved approved, @RequestHeader(value = "Authorization") String Authorization) {
    return service.doApprove(applicationNo, approved, Authorization);
}
}
