package com.model.application;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "building_permit_floor")
public class BuildingPermitFloor implements Serializable {

    @EmbeddedId
    protected BuildingPermitFloorPK pk;
    @Column(name = "FLOOR", insertable = false, updatable = false)
    private Integer floor;
    @Basic(optional = false)
    @Column(name = "LENGTH")
    private Float length;
    @Basic(optional = false)
    @Column(name = "WIDTH")
    private Float width;
    @Column(name = "HEIGHT")
    private Float height;
    @Basic(optional = false)
    @Column(name = "AREA")
    private Float area;
    @Column(name = "FEET_AREA")
    private Float feetArea;
    @Column(name = "block", insertable = false, updatable = false)
    private String block;
    @Column(name = "floor_unit", columnDefinition = "VARCHAR(10) DEFAULT NULL")
    private String floorUnit;
    @JoinColumn(name = "APPLICATION_NO", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BuildingPermitApplication applicationNo;

    public BuildingPermitFloorPK getPk() {
        return pk;
    }

    public void setPk(BuildingPermitFloorPK pk) {
        this.pk = pk;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public Float getFeetArea() {
        return feetArea;
    }

    public void setFeetArea(Float feetArea) {
        this.feetArea = feetArea;
    }

    public String getFloorUnit() {
        return floorUnit;
    }

    public void setFloorUnit(String floorUnit) {
        this.floorUnit = floorUnit;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return "\n{\"block\":\"" + block + "\",\"floor\":\"" + floor + "\",\"length\":\"" + length + "\",\"width\":\"" + width + "\",\"height\":\"" + height + "\",\"area\":\"" + area + "\",\"feetArea\":\"" + feetArea + "\",\"floorUnit\":\"" + floorUnit + "\"}";
    }

}
