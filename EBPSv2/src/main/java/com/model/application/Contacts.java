/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.application;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contacts {
  @Id 
  @Column(name = "id")
  private Integer id;
  
  @Column(name = "json_data",columnDefinition = "varchar")
  private String jsonData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    @Override
    public String toString() {
        return "Contacts{" + "id=" + id + ", jsonData=" + jsonData + '}';
    }

  
  
    
    
}
