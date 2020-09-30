package com.controller.rest.utility;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.utility.MenuUserAccess;
import com.service.utility.MenuUserAccessService;

@RestController
@CrossOrigin
@RequestMapping("api/Utility/MenuUserAccess")
public class MenuUserAccessRestController {

    @Autowired
    MenuUserAccessService service;

    @GetMapping("/{userType}")
    public Object index(@PathVariable String userType, @RequestHeader(value = "Authorization") String Authorization) {

        return service.getAll(userType);
    }

    @PostMapping
    public Object doSave(@Valid @RequestBody List<MenuUserAccess> list, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
        return service.save(list, Authorization);
    }

   
}
