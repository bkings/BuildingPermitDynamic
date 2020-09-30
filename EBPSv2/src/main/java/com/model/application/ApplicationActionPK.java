/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.application;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ApplicationActionPK implements Serializable {

@Column(name = "APPLICATION_NO")
private Long applicationNo;
@Column(name = "USER_TYPE", columnDefinition = "VARCHAR(2)")
private String userType;
@Column(name = "FORM_ID")
private Integer formId;
@Column(name = "STATUS")
private String status;

public ApplicationActionPK() {
}

public ApplicationActionPK(Long applicationNo, String userType, Integer formId, String status) {
    this.applicationNo = applicationNo;
    this.userType = userType;
    this.formId = formId;
    this.status = status;
}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

public Integer getFormId() {
    return formId;
}

public void setFormId(Integer formId) {
    this.formId = formId;
}

@Override
public int hashCode() {
    int hash = 5;
    hash = 13 * hash + Objects.hashCode(this.applicationNo);
    hash = 13 * hash + Objects.hashCode(this.userType);
    hash = 13 * hash + Objects.hashCode(this.formId);
    return hash;
}

@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    final ApplicationActionPK other = (ApplicationActionPK) obj;
    if (!Objects.equals(this.userType, other.userType)) {
        return false;
    }
    if (!Objects.equals(this.applicationNo, other.applicationNo)) {
        return false;
    }
    if (!Objects.equals(this.formId, other.formId)) {
        return false;
    }
    return true;
}

@Override
public String toString() {
    return "ApplicationActionPK{" + "applicationNo=" + applicationNo + ", userType=" + userType + ", formId=" + formId + '}';
}

}
