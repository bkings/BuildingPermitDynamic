package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "architectural_design_b_master")
public class ArchitecturalDesignBMaster implements java.io.Serializable {

@Id
@Column(name = "ID")
private String id;
@Column(name = "BUILDING_ELEMENTS")
private String buildingElements;
@Column(name = "GROUP_NAME")
private String groupName;
@Column(name = "GROUP_ID")
private String groupId;
@Column(name = "UNIT")
private String unit;
public String getGroupId() {
    return groupId;
}

public void setGroupId(String groupId) {
    this.groupId = groupId;
}

public ArchitecturalDesignBMaster() {
}

public ArchitecturalDesignBMaster(String id) {
    this.id = id;
}

public String getId() {
    return id;
}

public void setId(String id) {
    this.id = id;
}

public String getBuildingElements() {
    return buildingElements;
}

public void setBuildingElements(String buildingElements) {
    this.buildingElements = buildingElements;
}

public String getGroupName() {
    return groupName;
}

public void setGroupName(String groupName) {
    this.groupName = groupName;
}

public String getUnit() {
    return unit;
}

public void setUnit(String unit) {
    this.unit = unit;
}

@Override
public String toString() {
    return "\n{\"id\": \"" + id + "\",\"buildingElements\": \"" + buildingElements + "\",\"groupName\": \"" + groupName + "\",\"unit\": \"" + unit + "\"}";
}
}
