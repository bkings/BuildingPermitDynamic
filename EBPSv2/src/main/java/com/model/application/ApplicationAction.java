/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.application;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.model.utility.FormNameMaster;
import com.model.utility.UserTypeMaster;

@Entity
@Table(name = "application_action")
public class ApplicationAction implements Serializable {

@EmbeddedId
protected ApplicationActionPK pk;

@JoinColumn(name = "APPLICATION_NO", referencedColumnName = "ID", insertable = false, updatable = false)
@ManyToOne(optional = false, fetch = FetchType.LAZY)
private BuildingPermitApplication applicationNo;

@JoinColumn(name = "FORM_ID", referencedColumnName = "ID", insertable = false, updatable = false)
@ManyToOne(optional = false, fetch = FetchType.LAZY)
private FormNameMaster formName;

@JoinColumn(name = "USER_TYPE", referencedColumnName = "ID", insertable = false, updatable = false)
@ManyToOne(optional = false, fetch = FetchType.LAZY)
private UserTypeMaster userType;



public ApplicationActionPK getPk() {
    return pk;
}

public void setPk(ApplicationActionPK pk) {
    this.pk = pk;
}


}
