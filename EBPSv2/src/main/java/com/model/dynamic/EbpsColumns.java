package com.model.dynamic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ebps_columns")
public class EbpsColumns {

	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "COLUMN_NAME")
	private String columnName;
	@Column(name = "DEFAULT_VALUE")
	private String defaultValue;
	@Column(name = "LENGTH")
	private String length;
	@Column(name = "REFERENCE")
	private String reference;
	@Column(name = "IS_PK",columnDefinition = "VARCHAR(1) DEFAULT 'N'")
	private String isPk;
	@Column(name = "TABLE_ID")
	private Long tableId;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "TABLE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private EbpsTables ebpsTables;

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

	public String getIsPk() {
		return isPk;
	}

	public void setIsPk(String isPk) {
		this.isPk = isPk;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

}
