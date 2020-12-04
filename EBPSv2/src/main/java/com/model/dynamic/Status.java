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

import com.model.utility.FormNameMaster;
import com.model.utility.UserTypeMaster;

@Entity
@Table(name = "status")
public class Status implements Serializable {

	@EmbeddedId
	private StatusPK pk;
	@Column(name = "TABLE_ID")
	private Long tableId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DATE")
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "HAS_REVISED")
	private String hasRevised;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "TABLE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private EbpsTables ebpsTables;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_TYPE", referencedColumnName = "ID", insertable = false, updatable = false)
	private UserTypeMaster userTypeMaster;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "FORM_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private FormNameMaster formNameMaster;

	public String getHasRevised() {
		return hasRevised;
	}

	public void setHasRevised(String hasRevised) {
		this.hasRevised = hasRevised;
	}

	public StatusPK getPk() {
		return pk;
	}

	public void setPk(StatusPK pk) {
		this.pk = pk;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
