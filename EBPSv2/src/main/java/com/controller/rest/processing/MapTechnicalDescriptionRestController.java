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
import com.model.processing.MapTechnicalDescription;
import com.service.processing.MapTechnicalDescriptionService;

@RestController
@CrossOrigin
@RequestMapping("api/processing/phase1/MapTechnicalDescription")
public class MapTechnicalDescriptionRestController {

@Autowired
MapTechnicalDescriptionService service;

@GetMapping("/{id}")
public Object index(@RequestHeader(value = "Authorization") String Authorization, @PathVariable String id) {
    return service.getindex(id);
}

@GetMapping("/{unit}/{id}")
public Object getBuildingDetails(@RequestHeader(value = "Authorization") String Authorization, @PathVariable String unit, @PathVariable String id) {
    return service.buildingDetails(unit, id);
}

@PostMapping("/{applicationNo}")
public Object doSave(@PathVariable Long applicationNo, @RequestBody String jsonData, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
    MapTechnicalDescription obj = new MapTechnicalDescription();
    obj.setApplicationNo(applicationNo);
    obj.setJsonData(jsonData);
    
    return service.save(obj, Authorization);
}

@PutMapping("/{applicationNo}")
public Object doApprove(@PathVariable Long applicationNo, @Valid @RequestBody ApplicationApproved approved, @RequestHeader(value = "Authorization") String Authorization) {
    return service.doApprove(applicationNo, approved, Authorization);
}
}
