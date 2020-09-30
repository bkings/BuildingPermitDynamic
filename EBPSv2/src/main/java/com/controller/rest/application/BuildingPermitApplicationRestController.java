package com.controller.rest.application;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.model.application.ApplicationApproved;
import com.model.application.BuildingPermitApplication;
import com.service.application.BuildingPermitApplicationService;

@RestController
@CrossOrigin
@RequestMapping("api/Application/BuildingPermit")
public class BuildingPermitApplicationRestController {

    @Autowired
    BuildingPermitApplicationService service;

    @GetMapping
    public Object index(@RequestHeader(value = "Authorization") String Authorization) {
        return service.getIndex(Authorization);
    }

    @GetMapping("/Task")
    public Object task(@RequestParam(required = false) Long applicationNo, @RequestParam(required = false) String constructionType, @RequestParam(required = false) String year, @RequestParam(required = false) String nibedakName, @RequestParam(required = false) String kittaNo, @RequestParam(required = false) String wardNo, @RequestParam(required = false) String applicationStatus, @RequestHeader(value = "Authorization") String Authorization) {
        try {
            if (constructionType == null) {
                constructionType = "";
            }
        } catch (Exception e) {
            constructionType = "";
        }
        try {
            if (year == null) {
                year = "";
            }
        } catch (Exception e) {
            year = "";
        }
        try {
            if (nibedakName == null) {
                nibedakName = "";
            }
        } catch (Exception e) {
            nibedakName = "";
        }
        try {
            if (kittaNo == null) {
                kittaNo = "";
            }
        } catch (Exception e) {
            kittaNo = "";
        }
        try {
            if (wardNo == null) {
                wardNo = "";
            }
        } catch (Exception e) {
            wardNo = "";
        }
        try {
            if (applicationStatus == null) {
                applicationStatus = "";
            }
        } catch (Exception e) {
            applicationStatus = "";
        }

        return service.getTask(applicationNo, constructionType, year, nibedakName, kittaNo, wardNo, applicationStatus, Authorization);
    }

    @GetMapping("/Filter")
    public Object filter(@RequestParam(required = false) Long applicationNo, @RequestParam String constructionType, @RequestParam String year, @RequestParam String nibedakName, @RequestParam String kittaNo, @RequestParam String wardNo, @RequestParam(required = false) String applicationStatus, @RequestHeader(value = "Authorization") String Authorization) {
        return service.getFilter(applicationNo, constructionType, year, nibedakName, kittaNo, wardNo, Authorization, applicationStatus);
    }

    @GetMapping("/Expired")
    public Object expiredApplications(@RequestHeader(value = "Authorization") String Authorization) {
        return service.expiredApplications(Authorization);
    }

    @PostMapping("/Expired/{id}")
    public Object expiredApplications(@PathVariable Long id, @RequestHeader(value = "Authorization") String Authorization) {
        return service.expiredApplications(id, Authorization);
    }

    @GetMapping("/ExpiredRequest")
    public Object expiredApplicationsRequest(@RequestHeader(value = "Authorization") String Authorization) {
        return service.expiredApplicationsRequest(Authorization);
    }

    @PostMapping("/ExpiredRequest/{id}")
    public Object expiredApplicationsRequest(@PathVariable Long id, @RequestHeader(value = "Authorization") String Authorization) {
        return service.expiredApplicationsRequest(id, Authorization);
    }

    @GetMapping("/Talathap")
    public Object talathap(@RequestHeader(value = "Authorization") String Authorization, @RequestParam String status, @RequestParam(required = false) Long year) {
        return service.getTalathap(Authorization, status, year);
    }

    @GetMapping("/Completed")
    public Object completedList(@RequestParam(required = false) String constructionType, @RequestParam(required = false) Long applicationNo, @RequestParam(required = false) String nibedakName, @RequestParam(required = false) Long year, @RequestHeader(value = "Authorization") String Authorization) {
        return service.completedList(constructionType, year, applicationNo, nibedakName, Authorization);
    }

    @PostMapping("/Completed")
    public Object completedList(HttpServletRequest request, @RequestParam Long applicationNo, @RequestParam MultipartFile file, @RequestParam String designer, @RequestHeader(value = "Authorization") String Authorization) {
        return service.completedList(request, applicationNo, designer, file, Authorization);
    }

    @GetMapping("/{id}")
    public Object index(@RequestHeader(value = "Authorization") String Authorization, @PathVariable Long id) {
        return service.getIndex(Authorization, id);
    }

    @PostMapping
    public Object doSave(HttpServletRequest request, @Valid @RequestBody BuildingPermitApplication obj, @RequestHeader(value = "Authorization") String Authorization) {
        return service.doSave(request, obj, Authorization);
    }

    @PostMapping("/{id}")
    public Object doPhotoUpload(HttpServletRequest request, @PathVariable Long id, @RequestParam MultipartFile photo, @RequestHeader(value = "Authorization") String Authorization) {
        return service.doPhotoUpload(request, id, photo, Authorization);
    }

    @PutMapping("/{id}")
    public Object doApprove(HttpServletRequest request, @PathVariable Long id, @Valid @RequestBody ApplicationApproved approved, @RequestHeader(value = "Authorization") String Authorization) {
        return service.doApprove(request, id, id.toString(), approved, Authorization);

    }

    @PutMapping("/{id}/{regNo}")
    public Object doApprove(HttpServletRequest request, @PathVariable Long id, @PathVariable String regNo, @Valid @RequestBody ApplicationApproved approved, @RequestHeader(value = "Authorization") String Authorization) {
        return service.doApprove(request, id, regNo, approved, Authorization);
    }

    @PatchMapping("/{id}")
    public Object doDiscard(HttpServletRequest request, @RequestParam(required = false) MultipartFile file, @RequestParam String date, @RequestParam String reason, @PathVariable Long id, @RequestHeader(value = "Authorization") String Authorization) {
        return service.doDiscard(request, id, date, reason, file, Authorization);
    }

    @GetMapping("/Discard")
    public Object doDiscard(HttpServletRequest request, @RequestParam Long year, @RequestHeader(value = "Authorization") String Authorization) {
        return service.doDiscard(request, year, Authorization);
    }
}
