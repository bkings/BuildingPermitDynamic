package com.controller.rest.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.service.utility.ChangeDesignerService;

@RestController
@CrossOrigin
@RequestMapping("api/Utility/ChangeDesigner")
public class ChangeDesignerRestController {


@Autowired
ChangeDesignerService service;

@GetMapping("/{applicationNo}")
public Object index(@PathVariable Long applicationNo, @RequestHeader(value = "Authorization") String Authorization) {
    return service.getRecord(Authorization, applicationNo);
}

@PostMapping("/{applicationNo}")
public Object doSave(HttpServletRequest request,@PathVariable Long applicationNo, @RequestParam String designer, @RequestParam(required = false) MultipartFile file, @RequestHeader(value = "Authorization") String Authorization) {
    return service.doSave(request, Authorization, applicationNo, designer,file);
}
}
