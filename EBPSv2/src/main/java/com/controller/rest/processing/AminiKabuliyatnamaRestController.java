package com.controller.rest.processing;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.processing.AminiKabuliyatnama;
import com.service.processing.AminiKabuliyatnamaService;

@RestController
@CrossOrigin
@RequestMapping("api/Processing/AminiKabuliyatnama")

public class AminiKabuliyatnamaRestController {

@Autowired
AminiKabuliyatnamaService service;

@GetMapping("/{id}")
public Object index(@PathVariable long id, @RequestHeader(value = "Authorization") String Authorization) {

    return service.getAll(id);
}

@PostMapping("/{id}")
public Object doSave(@PathVariable long id, @RequestBody String jsonData, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
    AminiKabuliyatnama obj = new AminiKabuliyatnama();
    obj.setApplicationNo(id);
    obj.setJsonData(jsonData);
    return service.save(obj, Authorization);
}

@PutMapping("/{id}")
public Object doUpdate(@RequestBody String jsonData, @PathVariable long id, @RequestHeader(value = "Authorization") String Authorization) throws IOException {
    AminiKabuliyatnama obj = new AminiKabuliyatnama();
    obj.setApplicationNo(id);
    obj.setJsonData(jsonData);
    return service.update(obj, id, Authorization);
}

}
