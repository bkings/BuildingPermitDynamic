/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.application;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import model.DateConvert;

@Entity
@Table(name = "frequently_asked_question")
public class FrequentlyAskedQuestion {
  @Id 
  @Column(name = "id")
  private Integer id;
  
  @Column(name = "enter_date", updatable = false)
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
//@JsonFormat(pattern="yyyy-MM-dd")
  private java.util.Date enterDate;
  
  @Column(name = "json_data",columnDefinition = "varchar")
  private String jsonData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
//    public String getJoinDate() {
//    return DateConvert.toStringDateTime(joinDate);
//}

public String getEnterDateBS() {
    return DateConvert.adtobsDate(enterDate);
}
    public Date getEnterDate() {
        
        return enterDate;
    }

    public void setEnterDate(String enterDate) {
        this.enterDate = DateConvert.toDate(enterDate);
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    @Override
    public String toString() {
        return "FrequentlyAskedQuestion{" + "id=" + id + ", enterDate=" + enterDate + ", jsonData=" + jsonData + '}';
    }
  
  
    
}
