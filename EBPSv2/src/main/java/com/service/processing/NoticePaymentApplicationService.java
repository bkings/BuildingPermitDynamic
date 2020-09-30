/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.processing;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.model.application.ApplicationApproved;
import com.model.processing.NoticePaymentApplication;

public interface NoticePaymentApplicationService {

public Object getAll(Long applicationNo);

public Object save(NoticePaymentApplication obj, String Authorization);

public Object doApprove(Long applicationNo, ApplicationApproved approved, String Authorization);

public Object do15DayNoticeDate(HttpServletRequest request, Long applicationNo, String date,MultipartFile[] file, MultipartFile file2,String Authorization);

}
