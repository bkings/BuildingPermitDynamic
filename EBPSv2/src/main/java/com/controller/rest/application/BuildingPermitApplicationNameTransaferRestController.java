package com.controller.rest.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.application.BuildingPermitApplicationNameTransaferService;

@RestController
@CrossOrigin
@RequestMapping("api/BuildingPermitApplication/NameTransafer")
public class BuildingPermitApplicationNameTransaferRestController {

    @Autowired
    BuildingPermitApplicationNameTransaferService service;

    @GetMapping
    public Object index(@RequestParam(required = false) Long applocationNo, @RequestParam(required = false) Long year, @RequestParam String constructionType, @RequestParam String nibedakName, @RequestParam String kittaNo, @RequestParam String wardNo, @RequestHeader(value = "Authorization") String Authorization) {
        return service.index(applocationNo, year, constructionType, nibedakName, kittaNo, wardNo, Authorization);
    }

    @GetMapping("/{applocationNo}")
    public Object index(@RequestParam(required = false) Long applocationNo, @RequestHeader(value = "Authorization") String Authorization) {
        return service.index(applocationNo, Authorization);
    }

    @GetMapping("/History")
    public Object history(@RequestParam(required = false) Long applicationNo, @RequestParam(required = false) Long year, @RequestParam(required = false) String constructionType, @RequestParam(required = false) String nibedakName, @RequestParam(required = false) String kittaNo, @RequestParam(required = false) String wardNo, @RequestHeader(value = "Authorization") String Authorization) {
        return service.history(applicationNo, year, constructionType, nibedakName, kittaNo, wardNo, Authorization);
    }

    @PostMapping("/{applocationNo}/{time}")
    public Object doSave(@PathVariable long applocationNo, @PathVariable int time, @RequestHeader(value = "Authorization") String Authorization) {
        return service.save(applocationNo, time, Authorization);
    }

}
