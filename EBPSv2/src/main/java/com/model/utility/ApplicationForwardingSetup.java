package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "application_forwarding_setup", uniqueConstraints = { @UniqueConstraint(name = "unique_forward_to", columnNames = { "forward_by", "forward_to",
		"form_group" }) })
public class ApplicationForwardingSetup implements java.io.Serializable {

	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "forward_by")
	private String forwardBy;
	@Column(name = "forward_to")
	private String forwardTo;

	@JoinColumn(name = "form_group", referencedColumnName = "ID")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private FormGroupMaster formGroup;

	@Column(name = "skip_parameter")
	private String skipParameter;

	public String getSkipParameter() {
		return skipParameter;
	}

	public void setSkipParameter(String skipParameter) {
		this.skipParameter = skipParameter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getForwardBy() {
		return forwardBy;
	}

	public void setForwardBy(String forwardBy) {
		this.forwardBy = forwardBy;
	}

	public String getForwardTo() {
		return forwardTo;
	}

	public void setForwardTo(String forwardTo) {
		this.forwardTo = forwardTo;
	}

	public FormGroupMaster getFormGroup() {
		return formGroup;
	}

	public void setFormGroup(FormGroupMaster formGroup) {
		this.formGroup = formGroup;
	}

	@Override
	public String toString() {
		return "\n{\"id\": \"" + id + "\",\"forwardBy\": \"" + forwardBy + "\",\"forwardTo\": \"" + forwardTo + "\"}";
	}
}
