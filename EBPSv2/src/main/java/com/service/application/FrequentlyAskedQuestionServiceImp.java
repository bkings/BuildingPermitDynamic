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
import com.dao.application.DaoFrequentlyAskedQuestion;
import com.model.application.FrequentlyAskedQuestion;

import model.DateConvert;
import model.Message;

@Service
public class FrequentlyAskedQuestionServiceImp implements FrequentlyAskedQuestionService{
@Autowired
DaoFrequentlyAskedQuestion da;
Message message = new Message();
String msg = "", sql;
int row;
    @Override
    public Object getAll() { 
         return da.getAll("from FrequentlyAskedQuestion");
//        if (message.list.isEmpty()) {
//            return message.respondWithError("Record not found");
//        }
//        message.map = new HashMap();
//        List l = new ArrayList();
//        for (int i = 0; i < message.list.size(); i++) {
//            message.map = (Map) message.list.get(i);
//            l.add(message.map);
//        }
//        return l;
    //message.map.put("data", message.list.get(0));
    
    }

    @Override
    public Object getById(Integer id) {
        message.list = da.getById("from FrequentlyAskedQuestion where id='" + id + "'");
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
        FrequentlyAskedQuestion obj = new FrequentlyAskedQuestion();
        
        String sql;
        Integer id = 1;
        obj.setId(id);
        obj.setEnterDate(DateConvert.today());
        row = da.save(obj);
        sql="UPDATE frequently_asked_question SET json_data = CONCAT(json_data, '"+jsonData+"') where id=1";
        message.db.save(sql);
        
        if (row == 1) {
        message.map = new HashMap();
        message.map.put("message", "Success");
        return message.map;
        } else {
        return message.respondWithError("Cannot save the question");
        }
        //return "";
    }

    
}
