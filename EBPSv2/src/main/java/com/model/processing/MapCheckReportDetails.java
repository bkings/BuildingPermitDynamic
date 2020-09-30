
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
@Table(name = "map_check_report_details")
public class MapCheckReportDetails implements Serializable {

@EmbeddedId
protected MapCheckReportDetailsPK pk;
@Basic(optional = false)
@Column(name = "DESIGN_DATA", columnDefinition = "VARCHAR")
private String designData;
@Column(name = "REMARK", columnDefinition = "VARCHAR")
private String remark;

@JoinColumn(name = "APPLICATION_NO", referencedColumnName = "APPLICATION_NO", insertable = false, updatable = false)
@ManyToOne(optional = false, fetch = FetchType.LAZY)
private MapCheckReport applicationNo;
@JoinColumn(name = "CLASS_ID", referencedColumnName = "ID", insertable = false, updatable = false)
@ManyToOne(optional = false, fetch = FetchType.LAZY)
private MapCheckReportMaster classId;

public MapCheckReportDetails() {
}

public MapCheckReportDetailsPK getPk() {
    return pk;
}

public void setPk(MapCheckReportDetailsPK pk) {
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
    return "MapCheckReportDetails{" + "pk=" + pk + ", designData=" + designData + ", remark=" + remark + '}';
}

}
