package com.controller.rest.utility;

import java.io.IOException;

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

import com.model.utility.EmailSendingPanding;
import com.service.utility.EmailSendingPandingService;

@RestController
@CrossOrigin
@RequestMapping("Utility/EmailSendingPanding")
public class EmailSendingPandingRestController {

    @Autowired
    EmailSendingPandingService service;

    @GetMapping
    public Object index(@RequestHeader(value = "Authorization") String Authorization) {

        return service.getAll();
    }

    @PostMapping
    public Object doSave(@RequestBody EmailSendingPanding obj, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
        return service.save(obj, Authorization);
    }

    @PutMapping("/{ids}")
    public Object doSave(@PathVariable String ids, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
        return service.update(ids, Authorization);
    }
}
