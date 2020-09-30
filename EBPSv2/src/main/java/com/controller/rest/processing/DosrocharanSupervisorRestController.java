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
import com.model.processing.DosrocharanSupervisor;
import com.service.processing.DosrocharanSupervisorService;

@RestController
@CrossOrigin
@RequestMapping("api/Processing/DosrocharanSupervisor")
public class DosrocharanSupervisorRestController {

@Autowired
DosrocharanSupervisorService service;

@GetMapping("/{applicationNo}")
public Object show(@PathVariable Long applicationNo) {
    return service.getAll(applicationNo);
}

@PostMapping("/{applicationNo}/{hasRevised}")
public Object doSave(@PathVariable Long applicationNo, @PathVariable String hasRevised, @RequestBody String jsonData, @RequestHeader(value = "Authorization") String Authorization) {
    
    DosrocharanSupervisor obj = new DosrocharanSupervisor();
    obj.setJsonData(jsonData);
    obj.setApplicationNo(applicationNo);
    obj.setHasRevised(hasRevised);
    return service.save(obj, Authorization);
    
}

@PutMapping("/{applicationNo}")
public Object doApprove(@PathVariable Long applicationNo, @Valid @RequestBody ApplicationApproved approved, @RequestHeader(value = "Authorization") String Authorization) {
    return service.doApprove(applicationNo, approved, Authorization);
}
}
