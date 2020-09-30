/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.rest.utility;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Message;

@RestController
@CrossOrigin
@RequestMapping("/deleteRecords")
public class deleteSetup {
    @GetMapping
    public Object deleteRecord(){   
    Message message = new Message();
    String sql="Select table_name from form_name_master";
    List l = message.db.getRecord(sql);
    for(int i=0;i<l.size();i++){
    try{
    message.map = (Map) l.get(i);
    String table =message.map.get("table_name").toString();
                try{
                    if (table.equalsIgnoreCase("building_permit_application")){
                      sql="Delete from building_permit_floor";
                      int row=message.db.delete(sql);
                      sql="Delete from building_permit_surrounding";
                      row=message.db.delete(sql);
                      sql="Delete from building_member_details";
                      row=message.db.delete(sql);
                      sql="Delete from building_member_details";
                      row=message.db.delete(sql);
                      sql="Delete from file_storage_application";
                      row=message.db.delete(sql);
                      sql="Delete from architectural_design_c_details";
                      row=message.db.delete(sql);
                      sql="Delete from architectural_design_b_details";
                      row=message.db.delete(sql);
                      sql="Delete from applications_comments";
                      row=message.db.delete(sql);
                      sql="Delete from application_history";
                      row=message.db.delete(sql);
                      sql="Delete from electrical_design_details";
                      row=message.db.delete(sql);
                      
                    }
                    if (table.equalsIgnoreCase("map_check_report")){
                       sql="Delete from map_check_report_details";
                      int row=message.db.delete(sql); 
                    }
                    sql="Delete from "+table;
                    int row=message.db.delete(sql);
//                    if(row>0){
//                     return message.respondWithMessage("successfully deleted recrd!!!");
//                    }else{
//                        return message.respondWithError("cannot delete record");
//                    }
                        
                }catch(Exception e){
                    System.out.println("Exceptiom:: "+e);
                }
    }catch(Exception e){
        System.out.println("excepton:: "+e);
    }
                
}
    return "process is completed";
    }   
    
}
