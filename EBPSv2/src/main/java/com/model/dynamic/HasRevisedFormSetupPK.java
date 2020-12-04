package com.model.dynamic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class HasRevisedFormSetupPK implements Serializable {

	@Column(name = "APPLICATION_NO")
	private Long applicationNo;
	@Column(name = "FORM_ID")
	private Long formId;

	public HasRevisedFormSetupPK() {
		
	}
	
	public HasRevisedFormSetupPK(Long applicationNo, Long formId) {
		this.applicationNo = applicationNo;
		this.formId = formId;
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

}
