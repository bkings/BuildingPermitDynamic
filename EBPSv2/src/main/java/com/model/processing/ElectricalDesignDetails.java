/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.processing;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.model.application.BuildingPermitApplication;

@Entity
@Table(name = "electrical_design_details")
public class ElectricalDesignDetails implements Serializable {

@EmbeddedId
protected ArchitecturalDesignDetailsPK pk;
@Basic(optional = false)
@Column(name = "QTY")
private String qty;
@Column(name = "REMARK", columnDefinition = "VARCHAR")
private String remark;
@Column(name = "unit")
private String unit;
@JoinColumn(name = "APPLICATION_NO", referencedColumnName = "ID", insertable = false, updatable = false)
@ManyToOne(optional = false)
private BuildingPermitApplication applicationNo;

public ElectricalDesignDetails() {
}

public ArchitecturalDesignDetailsPK getPk() {
    return pk;
}

public void setPk(ArchitecturalDesignDetailsPK pk) {
    this.pk = pk;
}

public String getQty() {
    return qty;
}

public void setQty(String qty) {
    this.qty = qty;
}

public String getRemark() {
    return remark;
}

public void setRemark(String remark) {
    this.remark = remark;
}

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BuildingPermitApplication getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(BuildingPermitApplication applicationNo) {
        this.applicationNo = applicationNo;
    }

    @Override
    public String toString() {
        return "ElectricalDesignDetails{" + "pk=" + pk + ", qty=" + qty + ", remark=" + remark + ", unit=" + unit + ", applicationNo=" + applicationNo + '}';
    }




}
