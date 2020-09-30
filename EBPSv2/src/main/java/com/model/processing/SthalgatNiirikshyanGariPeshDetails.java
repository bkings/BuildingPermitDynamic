package com.model.processing;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.model.utility.MapCheckReportMaster;

@Entity
@Table(name = "sthalgat_nirikshyan_gari_pesh_details")
public class SthalgatNiirikshyanGariPeshDetails implements Serializable {

	@EmbeddedId
	protected SthalgatNiirikshyanGariPeshDetailsPK pk;
	@Basic(optional = false)
	@Column(name = "DESIGN_DATA", columnDefinition = "VARCHAR")
	private String designData;
	@Column(name = "REMARK", columnDefinition = "VARCHAR")
	private String remark;

	@JoinColumn(name = "APPLICATION_NO", referencedColumnName = "APPLICATION_NO", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private SthalgatNiirikshyanGariPesh applicationNo;
	@JoinColumn(name = "CLASS_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MapCheckReportMaster classId;

	public SthalgatNiirikshyanGariPeshDetails() {
	}

	public SthalgatNiirikshyanGariPeshDetailsPK getPk() {
	    return pk;
	}

	public void setPk(SthalgatNiirikshyanGariPeshDetailsPK pk) {
	    this.pk = pk;
	}

	public String getDesignData() {
	    return designData;
	}

	public void setDesignData(String designData) {
	    this.designData = designData;
	}

	public String getRemark() {
	    return remark;
	}

	public void setRemark(String remark) {
	    this.remark = remark;
	}

	@Override
	public String toString() {
	    return "SthalgatNiirikshyanGariPeshDetails{" + "pk=" + pk + ", designData=" + designData + ", remark=" + remark + '}';
	}
	
}
