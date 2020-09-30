package com.controller.rest.processing;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.model.application.ApplicationApproved;
import com.model.processing.NoticePaymentApplication;
import com.service.processing.NoticePaymentApplicationService;

@RestController
@CrossOrigin
@RequestMapping("api/Processing/NoticePaymentApplication")
public class NoticePaymentApplicationRestController {

    @Autowired
    NoticePaymentApplicationService service;

    @GetMapping("/{applicationNo}")
    public Object show(@PathVariable Long applicationNo) {
        return service.getAll(applicationNo);

    }

    @PostMapping("/{applicationNo}")
    public Object doSave(@PathVariable Long applicationNo, @RequestBody String jsonData, @RequestHeader(value = "Authorization") String Authorization) {
        NoticePaymentApplication obj = new NoticePaymentApplication();
        obj.setJsonData(jsonData);
        obj.setApplicationNo(applicationNo);
        return service.save(obj, Authorization);

    }

    @PutMapping("/{applicationNo}")
    public Object doApprove(@PathVariable Long applicationNo, @Valid @RequestBody ApplicationApproved approved, @RequestHeader(value = "Authorization") String Authorization) {
        return service.doApprove(applicationNo, approved, Authorization);
    }

    @PutMapping("/{applicationNo}/{date}")
    public Object doApprove(HttpServletRequest request, @PathVariable Long applicationNo, @PathVariable String date, @RequestParam(required = false) MultipartFile[] file, @RequestParam(required = false) MultipartFile file2, @RequestHeader(value = "Authorization") String Authorization) {
        return service.do15DayNoticeDate(request, applicationNo, date, file, file2, Authorization);
    }
}
