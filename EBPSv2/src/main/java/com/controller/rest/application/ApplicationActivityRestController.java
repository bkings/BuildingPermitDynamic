package com.controller.rest.application;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.DB;
import model.DateConvert;
import model.Message;

@RestController
@CrossOrigin
@RequestMapping("api/Application")
public class ApplicationActivityRestController {

    @GetMapping("/API")
    public Object get() {
        return new DB().getRecord("SELECT api FROM application_activity GROUP BY api");

    }

    @GetMapping("/Activity")
    public Object get(@RequestParam String dateFrom, @RequestParam String dateTo, @RequestParam String loginBy, @RequestParam String method, @RequestParam String api) {
        try {
            String sql;
            DB db = new DB();
            dateFrom = DateConvert.bsToAd(dateFrom);
            dateTo = DateConvert.bsToAd(dateTo);
            if (dateFrom.length() != 10) {
                return new Message().respondWithError("Invalid Date From!!");
            }
            if (dateTo.length() != 10) {
                return new Message().respondWithError("Invalid Date To!!");
            }
            if (loginBy.length() > 1) {
                loginBy = " AND login_by='" + loginBy + "'";
            }
            if (method.length() > 1) {
                method = " AND method='" + method + "'";
            }
            if (api.length() > 1) {
                api = " AND api='" + api + "'";
            }
            sql = "SELECT access_time \"accessTime\",api,login_by \"loginBy\",method,activity FROM application_activity WHERE access_time BETWEEN '" + dateFrom + "' AND '" + dateTo + "' " + loginBy + method + api + " ORDER BY access_time DESC";
            return db.getRecord(sql);
//            if (list.isEmpty()) {
//                return new Message().respondWithError("Record not found");
//            }
//            return list;
        } catch (Exception e) {
            return new Message().respondWithError(e.getMessage());
        }
    }
}
