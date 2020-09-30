package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "electrical_design_requirement_master")
public class ElectricalDesignRequirementMaster implements java.io.Serializable {
@Id
@Column(name = "id",columnDefinition = "VARCHAR(5)")
private String id;
@Column(name = "group_name",columnDefinition = "VARCHAR(50)")
private String groupName;
@Column(name = "data",columnDefinition = "VARCHAR(500)")
private String data;
@Column(name = "unit",columnDefinition = "VARCHAR(50)")
private String unit;

public String getId() {
    return id;
}

public void setId(String id) {
    this.id = id;
}

public String getGroupName() {
    return groupName;
}

public void setGroupName(String groupName) {
    this.groupName = groupName;
}

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


public String getUnit() {
    return unit;
}

public void setUnit(String unit) {
    this.unit = unit;
}

@Override
public String toString() {
    return "\n{\"id\": \"" + id + "\",\"groupName\": \"" + groupName + "\",\"elements\": \"" + data + "\",\"unit\": \"" + unit + "\"}";
}
}
