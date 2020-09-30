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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.application.FrequentlyAskedQuestionService;


@RestController
@CrossOrigin
@RequestMapping("api/application/FrequentlyAskedQuestion")
 
public class FrequentlyAskedQuestionRestController {
 
@Autowired
FrequentlyAskedQuestionService service;

@GetMapping()
public Object show() {
  return service.getAll();  
}
        
@GetMapping("/{id}")
public Object showById(@PathVariable Integer id) {
  return service.getById(id);  
}

@PostMapping()
public Object doSave(@RequestBody String jsonData, @RequestHeader(value = "Authorization") String Authorization) {
    return service.save(jsonData,Authorization);

}
    
    
}
