package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "map_check_report_master")
public class MapCheckReportMaster implements java.io.Serializable {

@Id
@Column(name = "ID")
private int id;
@Column(name = "SN", columnDefinition = "VARCHAR(20)")
private String sn;

@Column(name = "BUILDING_DESCRIPTION", columnDefinition = "VARCHAR")
private String buildingDescription;
@Column(name = "TYPE")
private String type;

public MapCheckReportMaster() {
}

public MapCheckReportMaster(int id) {
    this.id = id;
}

public String getSn() {
    return sn;
}

public void setSn(String sn) {
    this.sn = sn;
}

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getBuildingDescription() {
    return buildingDescription;
}

public void setBuildingDescription(String buildingDescription) {
    this.buildingDescription = buildingDescription;
}

public String getType() {
    return type;
}

public void setType(String type) {
    this.type = type;
}

@Override
public String toString() {
    return "MapCheckReportMaster{" + "id=" + id + ", sn=" + sn + ", buildingDescription=" + buildingDescription + ", type=" + type + '}';
}

}
