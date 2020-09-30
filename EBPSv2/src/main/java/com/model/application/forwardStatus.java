/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.application;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "forward_status")
public class forwardStatus implements Serializable {
    @Id 
  @Column(name = "id")
  private Integer id;
  @Column(name = "group_id")
  private Integer groupId;
  @Column(name = "applicatiion_no")
  private Long applicationNo;
  @Column(name = "forwarded_by")
  private String forwardedBy;
  @Column(name = "forwarded_to")
  private String forwardedTO;
  @Column(name = "forwarded_by_name")
  private String forwardedByName;
  @Column(name = "status")
  private String status;
    @Column(name = "forwarded_time")
    private String forwardedTime;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(Long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getForwardedBy() {
        return forwardedBy;
    }

    public void setForwardedBy(String forwardedBy) {
        this.forwardedBy = forwardedBy;
    }

    public String getForwardedTO() {
        return forwardedTO;
    }

    public void setForwardedTO(String forwardedTO) {
        this.forwardedTO = forwardedTO;
    }

    public String getForwardedByName() {
        return forwardedByName;
    }

    public void setForwardedByName(String forwardedByName) {
        this.forwardedByName = forwardedByName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getForwardedTime() {
        return forwardedTime;
    }

    public void setForwardedTime(String forwardedTime) {
        this.forwardedTime = forwardedTime;
    }

    @Override
    public String toString() {
        return "forwardStatus{" + "id=" + id + ", groupId=" + groupId + ", applicationNo=" + applicationNo + ", forwardedBy=" + forwardedBy + ", forwardedTO=" + forwardedTO + ", forwardedByName=" + forwardedByName + ", status=" + status + '}';
    }
    
    
  
  
 }
