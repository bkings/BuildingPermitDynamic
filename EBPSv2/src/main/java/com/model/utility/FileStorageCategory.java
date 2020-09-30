package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "file_storage_category")
public class FileStorageCategory implements java.io.Serializable {

	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "name", columnDefinition = "VARCHAR(150)")
	private String name;
	@Column(name = "is_multiple", columnDefinition = "VARCHAR(1)")
	private String isMultiple;
	@Column(name = "is_required")
	private String isRequired;
	@Column(name = "static_category_identifier", updatable = false,unique=true)
	private String staticCategoryIdentifier;

	@JoinColumn(name = "form_name", referencedColumnName = "ID")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private FormNameMaster formName;

	public FileStorageCategory() {
	}

	public FileStorageCategory(long id) {
		this.id = id;
	}

	public String getStaticCategoryIdentifier() {
		return staticCategoryIdentifier;
	}

	public void setStaticCategoryIdentifier(String staticCategoryIdentifier) {
		this.staticCategoryIdentifier = staticCategoryIdentifier;
	}

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

	public String getIsMultiple() {
		return isMultiple;
	}

	public void setIsMultiple(String isMultiple) {
		this.isMultiple = isMultiple;
	}

	public FormNameMaster getFormName() {
		return formName;
	}

	public void setFormName(FormNameMaster formName) {
		this.formName = formName;
	}

	public String getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}

	@Override
	public String toString() {
		return "\n{\"id\": \"" + id + "\",\"name\": \"" + name + "\",\"isMultiple\": \"" + isMultiple + "\",\"isRequired\": \"" + isRequired + "\"}";
	}

}
