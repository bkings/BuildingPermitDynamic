package com.model.dynamic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.model.utility.FormNameMaster;

@Entity
@Table(name = "form_fields")
public class FormFields {

	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "PLACEHOLDER")
	private String placeholder;
	@Column(name = "READ_ONLY")
	private String readOnly;
	@Column(name = "REQUIRED")
	private String required;
	@Column(name = "FORM_ID")
	private Long formId;
	@Column(name = "EBPS_COLUMN_ID")
	private Long ebpsColumnId;
	@Column(name = "REFERENCED_TABLE_ID")
	private Long referencedTableId;
	@Column(name = "REFERENCED_COLUMN_ID")
	private Long referencedColumnId;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "FORM_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private FormNameMaster formNameMaster;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "EBPS_COLUMN_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private EbpsColumns ebpsColumns;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "REFERENCED_TABLE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private EbpsTables referencedEbpsTables;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "REFERENCED_COLUMN_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private EbpsColumns referencedEbpsColumns;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public Long getFormId() {
		return formId;
	}

	public void setFormId(Long formId) {
		this.formId = formId;
	}

	public Long getEbpsColumnId() {
		return ebpsColumnId;
	}

	public void setEbpsColumnId(Long ebpsColumnId) {
		this.ebpsColumnId = ebpsColumnId;
	}

	public Long getReferencedTableId() {
		return referencedTableId;
	}

	public void setReferencedTableId(Long referencedTableId) {
		this.referencedTableId = referencedTableId;
	}

	public Long getReferencedColumnId() {
		return referencedColumnId;
	}

	public void setReferencedColumnId(Long referencedColumnId) {
		this.referencedColumnId = referencedColumnId;
	}

}
