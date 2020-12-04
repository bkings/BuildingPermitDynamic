package com.model.dynamic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "has_revised_form_setup")
public class HasRevisedFormSetup implements Serializable {

	@EmbeddedId
	private HasRevisedFormSetupPK pk;
	@Column(name = "TYPE")
	private String type;

	public HasRevisedFormSetupPK getPk() {
		return pk;
	}

	public void setPk(HasRevisedFormSetupPK pk) {
		this.pk = pk;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
