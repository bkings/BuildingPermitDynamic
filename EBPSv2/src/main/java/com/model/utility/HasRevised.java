/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.utility;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="has_revised")
public class HasRevised implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;    
    @Column(name = "user_type")
    private String userType;    
    @Column(name = "has_revised")
    private Integer hasRevised;    
    @Column(name = "status")
    private String status;    
    @Column(name = "ignored_form")
    private Integer ignoredForm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getHasRevised() {
        return hasRevised;
    }

    public void setHasRevised(Integer hasRevised) {
        this.hasRevised = hasRevised;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIgnoredForm() {
        return ignoredForm;
    }

    public void setIgnoredForm(Integer ignoredForm) {
        this.ignoredForm = ignoredForm;
    }

    @Override
    public String toString() {
        return "HasRevised{" + "id=" + id + ", userType=" + userType + ", hasRevised=" + hasRevised + ", status=" + status + ", ignoredForm=" + ignoredForm + '}';
    }

    
}
