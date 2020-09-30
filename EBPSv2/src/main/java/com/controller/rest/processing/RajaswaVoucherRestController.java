package com.controller.rest.processing;

import java.io.IOException;

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
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.application.ApplicationApproved;
import com.model.processing.RajaswaVoucher;
import com.service.processing.RajaswaVoucherService;

@RestController
@CrossOrigin
@RequestMapping("api/Processing/RajaswaVoucher")
public class RajaswaVoucherRestController {

@Autowired
RajaswaVoucherService service;

@GetMapping("/{id}")
public Object index(@PathVariable long id, @RequestHeader(value = "Authorization") String Authorization) {

    return service.getAll(id);
}

@PostMapping("/{applicationNo}")
public Object doSave(@PathVariable Long applicationNo,
          
          @RequestParam Float amtOfDharauti,
          @RequestParam String receiveSign,
          @RequestParam String rashidNumber,
          @RequestParam String amountInWords,
          @RequestParam String amtReceivedDate,         
          @RequestHeader(value = "Authorization") String Authorization) throws IOException {
    
    RajaswaVoucher obj = new RajaswaVoucher(applicationNo, amtOfDharauti, amountInWords, amtReceivedDate, rashidNumber, receiveSign);
    
    return service.save(obj, Authorization);
}
//@RequestParam MultipartFile file,
@PutMapping("/{applicationNo}")
public Object doUpdate(@PathVariable Long applicationNo, @Valid @RequestBody ApplicationApproved approved , @RequestHeader(value = "Authorization") String Authorization) throws IOException {
     return service.update(applicationNo, approved, Authorization);
}

}
