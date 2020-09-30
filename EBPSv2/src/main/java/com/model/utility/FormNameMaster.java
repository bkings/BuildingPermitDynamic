package com.model.utility;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.model.dynamic.EbpsTables;
import com.model.dynamic.FormFields;
import com.model.dynamic.FormPermissions;

@Entity
@Table(name = "form_name_master")
public class FormNameMaster implements java.io.Serializable {

	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "view_url")
	private String viewUrl;
	@Column(name = "page_code", columnDefinition = "varchar(5)", updatable = false)
	private String pageCode;
	@Column(name = "enter_by", columnDefinition = "varchar(2)")
	private String enterBy;
	@Column(name = "TABLE_ID")
	private Long tableId;
	@Column(name = "FORM_LAYOUT", columnDefinition = "VARCHAR")
	private String formLayout;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "TABLE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private EbpsTables ebpsTables;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "formNameMaster", fetch = FetchType.EAGER)
	private List<FormFields> formFieldsList;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "formNameMaster", fetch = FetchType.EAGER)
	private List<FormPermissions> formPermissions;

	public FormNameMaster() {
	}

	public FormNameMaster(Integer id) {
		this.id = id;
	}

	public FormNameMaster(String id) {
		this.id = Integer.parseInt(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		try {
			if (viewUrl.length() == 0 || viewUrl.contains("null")) {
				viewUrl = null;
			}
		} catch (Exception e) {
			viewUrl = null;
		}
		this.viewUrl = viewUrl;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		try {
			if (pageCode.length() == 0 || pageCode.contains("null")) {
				pageCode = null;
			}
		} catch (Exception e) {
			pageCode = null;
		}
		this.pageCode = pageCode;
	}

	public String getEnterBy() {
		return enterBy;
	}

	public void setEnterBy(String enterBy) {
		this.enterBy = enterBy;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public String getFormLayout() {
		return formLayout;
	}

	public void setFormLayout(String formLayout) {
		this.formLayout = formLayout;
	}

	public List<FormFields> getFormFieldsList() {
		return formFieldsList;
	}

	public void setFormFieldsList(List<FormFields> formFieldsList) {
		this.formFieldsList = formFieldsList;
	}

	public List<FormPermissions> getFormPermissions() {
		return formPermissions;
	}

	public void setFormPermissions(List<FormPermissions> formPermissions) {
		this.formPermissions = formPermissions;
	}

	@Override
	public String toString() {
		return "\n{\"id\": \"" + id + "\",\"name\": \"" + name + "\"}";
	}

}
