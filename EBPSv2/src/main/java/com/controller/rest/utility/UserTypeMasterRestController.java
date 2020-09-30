package com.controller.rest.utility;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.JWTToken;
import com.model.utility.UserTypeMaster;
import com.service.utility.UserTypeMasterRestService;

@RestController
@CrossOrigin
@RequestMapping("api/Utility/UserTypeMaster")
public class UserTypeMasterRestController {

    @Autowired
    UserTypeMasterRestService service;

    @GetMapping
    public Object index(@RequestHeader(value = "Authorization") String Authorization) {
        model.Message message = new model.Message();
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
//        System.out.println(td.getUserTypeName(td.getUserType()));
//        System.out.println(td.getUserTypeColumn());
        return service.index();
    }

    @PostMapping
    public Object doSave(@RequestHeader(value = "Authorization") String Authorization, @Valid @RequestBody UserTypeMaster obj) {
        return service.doUpdate(Authorization, obj);
    }

    @PutMapping
    public Object doUpdate(@RequestHeader(value = "Authorization") String Authorization, @Valid @RequestBody UserTypeMaster obj) {
        return service.doUpdate(Authorization, obj);
    }
}
