package com.service.application;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.model.application.ApplicationApproved;
import com.model.application.BuildingPermitApplication;

public interface BuildingPermitApplicationService {

    public Object getIndex(String Authorization);

    public Object getTask(Long applicationNo, String constructionType, String year, String nibedakName, String kittaNo, String wardNo, String applicationStatus, String Authorization);

    public Object getTalathap(String Authorization, String status, Long year);

    public Object completedList(String constructionType, Long year, Long applicationNo, String nibedakName, String Authorization);

    public Object completedList(HttpServletRequest request, Long applicationNo, String designer, MultipartFile file, String Authorization);

    public Object getFilter(Long applicationNo, String constructionType, String year, String nibedakName, String kittaNo, String wardNo, String Authorization, String applicationStatus);

    public Object getIndex(String Authorization, Long id);

    public Object doSave(HttpServletRequest request, BuildingPermitApplication obj, String Authorization);

    public Object doPhotoUpload(HttpServletRequest request, Long id, MultipartFile photo, String Authorization);

    public Object doApprove(HttpServletRequest request, Long applicationNo,String regNo, ApplicationApproved approved, String Authorization);

    public Object doDiscard(HttpServletRequest request, Long id, String date, String reason,MultipartFile file, String Authorization);

    public Object doDiscard(HttpServletRequest request, Long year, String Authorization);

    public Object expiredApplications(String Authorization);

    public Object expiredApplications(Long id, String Authorization);

    public Object expiredApplicationsRequest(String Authorization);

    public Object expiredApplicationsRequest(Long id, String Authorization);

}
