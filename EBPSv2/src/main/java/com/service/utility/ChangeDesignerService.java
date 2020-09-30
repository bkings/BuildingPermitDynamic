/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface ChangeDesignerService {

public Object getRecord(String Authorization, Long applicationNo);

public Object doSave(HttpServletRequest request,String Authorization, Long applicationNo, String designer, MultipartFile file);
}
