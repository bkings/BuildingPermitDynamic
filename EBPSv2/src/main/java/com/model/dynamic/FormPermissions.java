package com.model.dynamic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.model.utility.FormNameMaster;
import com.model.utility.UserTypeMaster;

@Entity
@Table(name = "form_permissions",uniqueConstraints= {@UniqueConstraint(columnNames= {"form_id","user_type"})})
public class FormPermissions {

	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "APPROVAL_STATUS")
	private String approvalStatus;
	@Column(name = "FORM_ID")
	private Long formId;
	@Column(name = "USER_TYPE")
	private String userType;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "FORM_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private FormNameMaster formNameMaster;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_TYPE", referencedColumnName = "ID", insertable = false, updatable = false)
	private UserTypeMaster userTypeMaster;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
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
