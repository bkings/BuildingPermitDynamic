/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.model.utility.MasonList;

public interface MasonListService {

    public Object getAll();

    public Object save(MasonList obj, MultipartFile photo,  HttpServletRequest request,String Authorization);

    public Object update(MasonList obj, MultipartFile photo, HttpServletRequest request, Integer id, String Authorization);

    public Object delete(String id, String Authorization);

}
