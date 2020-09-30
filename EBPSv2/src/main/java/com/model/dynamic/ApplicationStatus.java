package com.model.dynamic;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.model.application.BuildingPermitApplication;
import com.model.utility.UserTypeMaster;

import model.ApplicationForm;

@Entity
@Table(name = "application_status")
public class ApplicationStatus implements Serializable {

	@EmbeddedId
	private ApplicationStatusPK pk;
	@Column(name = "USER_ACTION", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT '1'")
	private String userAction;
	@Column(name = "USER_STATUS")
	private String userStatus; // er_status
	@Column(name = "USER_DATE")
	@Temporal(TemporalType.DATE)
	private Date userDate;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "USER_FULL_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String userFullStatus; // engineer_status
	@Column(name = "FORWARD_TO_USER")
	private String forwardToUser; // I or O
	@Column(name = "FORWARD_TO_USER_DATE")
	private String forwardToUserDate;
	@Column(name = "FORWARD_TO_USER_NAME")
	private String forwardToUserName;
	@Column(name = "FORWARD_TO_USER_REMARK")
	private String forwardToUserRemark;
	@Column(name = "REJECTED_BY_USER")
	private String rejectedByUser;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_TYPE", referencedColumnName = "ID", insertable = false, updatable = false)
	private UserTypeMaster userTypeMaster;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "APPLICATION_NO", referencedColumnName = "ID", insertable = false, updatable = false)
	private BuildingPermitApplication buildingPermitApplication;

	public ApplicationStatusPK getPk() {
		return pk;
	}

	public void setPk(ApplicationStatusPK pk) {
		this.pk = pk;
	}

	public Object getUserAction() {
		try {
			return ApplicationForm.getById(userAction);
		} catch (Exception e) {
		}
		return null;
	}

	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Date getUserDate() {
		return userDate;
	}

	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserFullStatus() {
		return userFullStatus;
	}

	public void setUserFullStatus(String userFullStatus) {
		this.userFullStatus = userFullStatus;
	}

	public String getForwardToUser() {
		return forwardToUser;
	}

	public void setForwardToUser(String forwardToUser) {
		this.forwardToUser = forwardToUser;
	}

	public String getForwardToUserDate() {
		return forwardToUserDate;
	}

	public void setForwardToUserDate(String forwardToUserDate) {
		this.forwardToUserDate = forwardToUserDate;
	}

	public String getForwardToUserName() {
		return forwardToUserName;
	}

	public void setForwardToUserName(String forwardToUserName) {
		this.forwardToUserName = forwardToUserName;
	}

	public String getForwardToUserRemark() {
		return forwardToUserRemark;
	}

	public void setForwardToUserRemark(String forwardToUserRemark) {
		this.forwardToUserRemark = forwardToUserRemark;
	}

	public String getRejectedByUser() {
		return rejectedByUser;
	}

	public void setRejectedByUser(String rejectedByUser) {
		this.rejectedByUser = rejectedByUser;
	}

}
