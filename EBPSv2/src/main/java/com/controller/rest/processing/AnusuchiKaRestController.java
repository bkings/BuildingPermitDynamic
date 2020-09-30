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
import com.model.processing.AnusuchiKa;
import com.service.processing.AnusuchiKaService;

@RestController
@CrossOrigin
@RequestMapping("api/Processing/Phase1/AnusuchiKa")
public class AnusuchiKaRestController {

    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    @Autowired
    AnusuchiKaService service;

    @GetMapping("/{applicationNo}")
    public Object index(@RequestHeader(value = "Authorization") String Authorization, @PathVariable Long applicationNo) {
        return service.getData(Authorization, applicationNo + "");
    }

    @PostMapping("/{applicationNo}")
    public Object doSave(@PathVariable Long applicationNo, @Valid @RequestBody AnusuchiKa obj, @RequestHeader(value = "Authorization") String Authorization) {
        obj.setApplicationNo(applicationNo);
        return service.Save(obj, Authorization);
    }

    @PutMapping("/{applicationNo}")
    public Object doApprove(@PathVariable Long applicationNo, @Valid @RequestBody ApplicationApproved approved, @RequestHeader(value = "Authorization") String Authorization) {
        return service.doApprove(applicationNo, approved, Authorization);
    }

}
