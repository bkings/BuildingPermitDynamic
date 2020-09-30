package com.controller.rest.utility;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.utility.RajashowMaster;
import com.service.utility.RajashowMasterService;

@RestController
@CrossOrigin
@RequestMapping("api/Utility/RajashowMaster")
public class RajashowMasterRestController {

    @Autowired
    RajashowMasterService service;

    @GetMapping
    public Object index(@RequestParam String wardNo,@RequestHeader(value = "Authorization") String Authorization) {

        return service.getAll(wardNo);
    }

    @PostMapping
    public Object doSave(@RequestBody List<RajashowMaster> obj, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
        return service.save(obj, Authorization);
    }

    

    @DeleteMapping("/{id}")
    public Object doDelete(@PathVariable String id,@RequestHeader(value = "Authorization") String Authorization) {
        return service.delete(id,Authorization);
    }
}
