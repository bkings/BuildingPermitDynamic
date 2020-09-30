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
import com.model.processing.RajaswaEntry;
import com.service.processing.RajaswaEntryService;

@RestController
@CrossOrigin
@RequestMapping("api/Processing/Phase1/RajaswaEntry")
public class RajaswaEntryRestController {

@Autowired
RajaswaEntryService service;

@GetMapping("/{id}")
public Object index(@PathVariable long id) {

    return service.getAll(id);
}

@PostMapping("/{applicationNo}")
public Object doSave(@PathVariable Long applicationNo, @RequestBody String jsonData, @RequestHeader(value = "Authorization") String Authorization) {
    RajaswaEntry obj = new RajaswaEntry();
    obj.setJsonData(jsonData);
    obj.setApplicationNo(applicationNo);
    return service.save(obj, Authorization);
    }

@PutMapping("/{applicationNo}")
public Object doApprove(@PathVariable Long applicationNo, @Valid @RequestBody ApplicationApproved approved, @RequestHeader(value = "Authorization") String Authorization) {
    return service.doApprove(applicationNo, approved, Authorization);
}
}
