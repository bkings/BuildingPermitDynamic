/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.DB;
import model.HibernateUtil;

/**
 *
 * @author bhubaneshwor
 */
@RestController
@CrossOrigin
@RequestMapping("api/HibernateUtil")
public class HibernateUtilRestController {
 DB db = new DB();
    @GetMapping
    public Object get() {
        Map map = new HashMap();
        String sql = "SELECT  NOW()";
        try {
            List l = db.getRecord(sql);
            if (l.isEmpty()) {
                map.put("db", "Database connection error");
            } else {
                map.put("msg", "Success");
            }
        } catch (Exception e) {
            map.put("db", "Database connection error");
        }
        return map;
    }

    @PostMapping
    public Object post() {
        Map map = new HashMap();
            HibernateUtil.init();
            String sql = "SELECT  NOW()";
        try {
            List l = db.getRecord(sql);
            if (l.isEmpty()) {
                map.put("db", "Database connection error");
            } else {
                map.put("msg", "Success");
            }
        } catch (Exception e) {
            map.put("db", "Database connection error");
        }
        return map;
             
//            map.put("msg", "Success");
//        } catch (Exception e) {
//            map.put("db", e.getMessage());
//        }
//        return map;
    }

}
