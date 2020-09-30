/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.application;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.application.DaoContacts;
import com.model.application.Contacts;

import model.Message;

@Service
public class ContactsServiceImp implements ContactsService{
    
    @Autowired
    DaoContacts da;
    Message message = new Message();
    String msg = "", sql;
    int row;


    @Override
    public Object getAll() {
        return da.getAll("from Contacts");
    }

    @Override
    public Object getById(Integer id) {
        message.list = da.getById("from Contacts where id='" + id + "'");
        if (message.list.isEmpty()) {
            return message.respondWithError("Record not found");
        }
        message.map = new HashMap();
        message.map.put("data", message.list.get(0));

        return message.map;
    }

    @Override
    public Object save(String jsonData, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return new Message().respondWithError("Authorization Error");
        }
        Contacts obj = new Contacts();
        
        String sql;
        Integer id = 1;
        obj.setId(id);
        row = da.save(obj);
        
        sql="UPDATE contacts SET json_data = CONCAT(json_data, '"+jsonData+"') where id=1";
        message.db.save(sql);
       
        if (row == 1) {
        message.map = new HashMap();
        message.map.put("message", "Success");
        return message.map;
        } else {
        return message.respondWithError("Cannot save the question");
        }
                }
    }
    
    

