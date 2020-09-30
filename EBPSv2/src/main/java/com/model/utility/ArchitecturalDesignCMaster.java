package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "architectural_design_c_master")
public class ArchitecturalDesignCMaster implements java.io.Serializable {

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "BUILDING_ELEMENTS")
    private String buildingElements;
    @Column(name = "UNIT")
    private String unit;
    @Column(name = "GROUP_ID")
    private String groupId;
    @Column(name = "GROUP_NAME")
    private String groupName;

    public ArchitecturalDesignCMaster() {
    }

    public ArchitecturalDesignCMaster(String id) {
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "\n{\"id\": \"" + id + "\",\"buildingElements\": \"" + buildingElements + "\",\"unit\": \"" + unit + "\",\"groupName\": \"" + groupName + "\",\"groupId\": \"" + groupId + "\"}";
    }
}
