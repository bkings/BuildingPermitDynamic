package com.model.dynamic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StatusPK implements Serializable {

	@Column(name = "APPLICATION_NO")
	private Long applicationNo;
	@Column(name = "FORM_ID")
	private Long formId;
	@Column(name = "USER_TYPE")
	private String userType;

	public StatusPK() {

	}

	public StatusPK(Long applicationNo, Long formId, String userType) {
		this.applicationNo = applicationNo;
		this.formId = formId;
		this.userType = userType;
	}

	public Long getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(Long applicationNo) {
		this.applicationNo = applicationNo;
	}

	public Long getFormId() {
		return formId;
	}

	public void setFormId(Long formId) {
		this.formId = formId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
