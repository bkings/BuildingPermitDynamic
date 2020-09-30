package com.controller.rest.utility;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.utility.ElectricalDesignRequirementMaster;
import com.service.utility.ElectricalDesignRequirementMasterService;

@RestController
@RequestMapping("api/Utility/ElectricalDesignRequirementMaster")
@CrossOrigin
public class ElectricalDesignRequirementMasterRestController {

@Autowired
ElectricalDesignRequirementMasterService service;

@GetMapping
public Object index(@RequestHeader(value = "Authorization") String Authorization) {

    return service.getAll();
}

@PostMapping
public Object doSave(@RequestBody ElectricalDesignRequirementMaster obj, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
    return service.save(obj, Authorization);
}

@PutMapping
public Object doUpdate(@RequestBody ElectricalDesignRequirementMaster obj, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
    return service.update(obj, Authorization);
}

@DeleteMapping("/{id}")
public Object doDelete(@PathVariable String id, @RequestHeader(value = "Authorization") String Authorization) {
    return service.delete(id, Authorization);
}
}
