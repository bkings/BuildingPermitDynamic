/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.application;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Embeddable
public class ApplicationHistoryPK implements Serializable {

@Column(name = "APPLICATION_NO")
private Long applicationNo;
@Column(name = "USER_TYPE", columnDefinition = "VARCHAR(2)")
private String userType;
@Column(name = "PAGE_NO", columnDefinition = "VARCHAR(3)")
private String pageNo;
@Column(name = "ACTION_DATE", columnDefinition = "timestamp")
@Temporal(TemporalType.TIMESTAMP)
private Date actionDate;

public ApplicationHistoryPK() {
}

public ApplicationHistoryPK(Long applicationNo, String userType, String pageNo, Date actionDate) {
    this.applicationNo = applicationNo;
    this.userType = userType;
    this.pageNo = pageNo;
    this.actionDate = actionDate;
}

public Long getApplicationNo() {
    return applicationNo;
}

public void setApplicationNo(Long applicationNo) {
    this.applicationNo = applicationNo;
}

public String getUserType() {
    return userType;
}

public void setUserType(String userType) {
    this.userType = userType;
}

public String getPageNo() {
    return pageNo;
}

public void setPageNo(String pageNo) {
    this.pageNo = pageNo;
}

public Date getActionDate() {
    return actionDate;
}

public void setActionDate(Date actionDate) {
    this.actionDate = actionDate;
}

}
