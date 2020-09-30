package com.model.dynamic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ApplicationStatusPK implements Serializable {

	@Column(name = "APPLICATION_NO")
	private Long applicationNo;
	@Column(name = "USER_TYPE")
	private String userType;

	public ApplicationStatusPK() {

	}

	public ApplicationStatusPK(Long applicationNo, String userType) {
		this.applicationNo = applicationNo;
		this.userType = userType;
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

}
