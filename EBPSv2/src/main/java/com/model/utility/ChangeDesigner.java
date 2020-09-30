/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.utility;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "change_designer")
public class ChangeDesigner implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;
    
    @Column(name = "application_no")
    private Long applicationNo;
    
    @Column(name = "file_url")
    private String fileUrl;
    
    @Column(name = "change_by")
    private String changeBy;
    
    @Column(name = "change_to")
    private String changeTo;
    
    @Column(name="change_date",updatable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date changeDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(Long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(String changeBy) {
        this.changeBy = changeBy;
    }

    public String getChangeTo() {
        return changeTo;
    }

    public void setChangeTo(String changeTo) {
        this.changeTo = changeTo;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    @Override
    public String toString() {
        return "ChangeDesigner{" + "id=" + id + ", applicationNo=" + applicationNo + ", fileUrl=" + fileUrl + ", changeBy=" + changeBy + ", changeTo=" + changeTo + ", changeDate=" + changeDate + '}';
    }
    
    
            
    
    
}
