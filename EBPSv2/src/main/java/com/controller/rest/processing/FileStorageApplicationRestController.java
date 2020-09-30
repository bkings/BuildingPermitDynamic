package com.controller.rest.processing;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.service.processing.FileStorageApplicationService;

@RestController
@CrossOrigin
@RequestMapping("api/Processing/FileStorageApplication")
public class FileStorageApplicationRestController {

    @Autowired
    FileStorageApplicationService service;

    @GetMapping("/{applicationNo}")
    public Object index(@PathVariable Long applicationNo, @RequestHeader(value = "Authorization") String Authorization) {

        return service.getAll(applicationNo);
    }

    @PostMapping("/{applicationNo}")
    public Object doSave(HttpServletRequest request, @PathVariable Long applicationNo, @RequestParam Long fileType, @RequestParam MultipartFile[] file, @RequestHeader(value = "Authorization") String Authorization) {
        return service.save(request, applicationNo, fileType, file, Authorization);
    }

    @DeleteMapping("/{url}")
    public Object doDelete(@PathVariable String url, @RequestHeader(value = "Authorization") String Authorization) {
        return service.delete(url, Authorization);
    }
}
