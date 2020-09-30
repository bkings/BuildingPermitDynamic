package com.controller.rest.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.model.utility.OrganizationUserRequest;
import com.service.utility.OrganizationUserRequestService;

@RestController
@CrossOrigin
@RequestMapping
public class OrganizationUserRequestRestController {

    @Autowired
    OrganizationUserRequestService service;

    @GetMapping("api/Utility/UserRequest")
    public Object index(@RequestHeader(value = "Authorization") String Authorization) {
        return service.getAll(Authorization);
    }

    @PostMapping("/Utility/UserRequest")
    public Object doSave(HttpServletRequest request, @ModelAttribute OrganizationUserRequest obj, @RequestParam(required = false) MultipartFile userImage, @RequestParam(required = false) MultipartFile userStamp, @RequestParam(required = false) MultipartFile userSignature) {
        return service.save(request, obj, userImage, userStamp, userSignature, "");
    }

    @PostMapping("api/Utility/UserRequest/{id}")
    public Object doUpdate(HttpServletRequest request, @PathVariable long id, @ModelAttribute OrganizationUserRequest obj, @RequestParam(required = false) MultipartFile userImage, @RequestParam(required = false) MultipartFile userStamp, @RequestParam(required = false) MultipartFile userSignature, @RequestHeader(value = "Authorization") String Authorization) {
        return service.update(request, id, obj, userImage, userStamp, userSignature, Authorization);
    }

    @PutMapping("api/Utility/UserRequest/{id}")
    public Object doApprove(HttpServletRequest request, @PathVariable long id, @RequestHeader(value = "Authorization") String Authorization) {
        return service.approve(request, id, Authorization);
    }

}
