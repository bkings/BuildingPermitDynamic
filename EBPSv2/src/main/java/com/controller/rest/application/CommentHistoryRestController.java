/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.rest.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.application.CommentHistoryService;

@RestController
@CrossOrigin
@RequestMapping("api/Processing")
public class CommentHistoryRestController {

@Autowired
CommentHistoryService service;

@GetMapping("/Comment/{applicationNo}")
public Object comment(@PathVariable Long applicationNo) {
    return service.getComment(applicationNo);
}

@GetMapping("/History/{applicationNo}")
public Object history(@PathVariable Long applicationNo) {
    return service.getHistory(applicationNo);
}
}
