package com.controller.rest.utility;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dao.utility.DaoOrganizationUser;
import com.model.utility.OrganizationUser;
import com.service.utility.OrganizationUserService;

@RestController
@CrossOrigin
@RequestMapping("api/Utility/OrganizationUser")
public class OrganizationUserRestController {

    @Autowired
    OrganizationUserService service;

    @Autowired
    DaoOrganizationUser da;

    @GetMapping
    public Object index() {

        return service.getAll();
    }

    @GetMapping("/DesignerRenewStatus")
    public Object renewIndex() {
        return service.getAllRenew();
    }

    @GetMapping("/Info")
    public Object index(@RequestHeader(value = "Authorization") String Authorization) {
        return service.getAll(Authorization);
    }

    @PostMapping
    public Object doSave(HttpServletRequest request, @ModelAttribute OrganizationUser obj, @RequestParam(required = false) MultipartFile userImage, @RequestParam(required = false) MultipartFile userStamp, @RequestParam(required = false) MultipartFile userSignature, @RequestHeader(value = "Authorization") String Authorization) {
        return service.save(request, obj, userImage, userStamp, userSignature, Authorization);
    }

    @PostMapping("/{id}")
    public Object doUpdate(HttpServletRequest request, @PathVariable long id, @ModelAttribute OrganizationUser obj, @RequestParam(required = false) MultipartFile userImage, @RequestParam(required = false) MultipartFile userStamp, @RequestParam(required = false) MultipartFile userSignature, @RequestHeader(value = "Authorization") String Authorization) {
        return service.update(request, id, obj, userImage, userStamp, userSignature, Authorization);
    }

    @PostMapping("/Renew")
    public Object doDesignerRenew(HttpServletRequest request, @RequestParam String id, @RequestParam String billNo, @RequestParam String tillDate, @RequestParam String fiscalYear, @RequestParam(required = false) MultipartFile file, @RequestHeader(value = "Authorization") String Authorization) throws IOException {

        return service.renew(request, Long.parseLong(id), billNo, tillDate, file, fiscalYear, Authorization);
    }

}
