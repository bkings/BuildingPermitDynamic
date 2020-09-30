/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "add_consultancy")
public class AddConsultancy {
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "consultancy_name")
    private String consultancyName;
    
    @Column(name = "user")
    private String user;
    
    @Column(name="designer_id")
    private Integer designerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConsultancyName() {
        return consultancyName;
    }

    public void setConsultancyName(String consultancyName) {
        this.consultancyName = consultancyName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Integer designerId) {
        this.designerId = designerId;
    }

    @Override
    public String toString() {
        return "AddConsultancy{" + "id=" + id + ", consultancyName=" + consultancyName + ", user=" + user + ", designerId=" + designerId + '}';
    }
    
    
    
    
    
    
}
