package com.model.dynamic;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "ebps_tables")
public class EbpsTables {

	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME", columnDefinition = "VARCHAR")
	private String name;
	@Column(name = "TABLE_NAME")
	private String tableName;
	@Column(name = "ACTIVE")
	private String active;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ebpsTables", fetch = FetchType.EAGER)
	private List<EbpsColumns> ebpsColumns;

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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public List<EbpsColumns> getEbpsColumns() {
		return ebpsColumns;
	}

	public void setEbpsColumns(List<EbpsColumns> ebpsColumns) {
		this.ebpsColumns = ebpsColumns;
	}

}
