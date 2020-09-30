package com.model.utility;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rajashow_master")
public class RajashowMaster implements java.io.Serializable {

    @EmbeddedId
    protected RajashowMasterPK pk;
    @Column(name = "ward_no", insertable = false, updatable = false, columnDefinition = "VARCHAR(2)")
    private String wardNo;
    @Column(name = "floor", insertable = false, updatable = false)
    private Integer floor;
    @Column(name = "floor_type", insertable = false, updatable = false, columnDefinition = "VARCHAR(3)")
    private String floorType;
    @Column(name = "area", insertable = false, updatable = false, columnDefinition = "VARCHAR(3)")
    private String area;
    @Column(name = "dharouti_rate")
    private Float dharoutiRate;
    @Column(name = "dastur_rate")
    private Float dasturRate;
    @Column(name = "construction_type", insertable = false, updatable = false, columnDefinition = "VARCHAR(3)")
    private String constructionType;

    public RajashowMaster() {
    }

    public RajashowMaster(String wardNo, Integer floor, String floorType, String area, Float dharoutiRate, Float dasturRate, String constructionType) {
        pk = new RajashowMasterPK(wardNo, floor, floorType, area, constructionType);
        this.dharoutiRate = dharoutiRate;
        this.dasturRate = dasturRate;
        this.constructionType = constructionType;
    }

    public void setPk(RajashowMasterPK pk) {
        this.pk = pk;
    }

    public String getId() {
        return wardNo + "-" + floor + "-" + floorType + "-" + area;
    }

    public String getWardNo() {
        return wardNo;
    }

    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getFloorType() {
        return floorType;
    }

    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Float getDharoutiRate() {
        return dharoutiRate;
    }

    public void setDharoutiRate(Float dharoutiRate) {
        this.dharoutiRate = dharoutiRate;
    }

    public Float getDasturRate() {
        return dasturRate;
    }

    public void setDasturRate(Float dasturRate) {
        this.dasturRate = dasturRate;
    }

    public String getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(String constructionType) {
        this.constructionType = constructionType;
    }

    @Override
    public String toString() {
        return "\n{\"wardNo\": \"" + wardNo + "\",\"floor\": \"" + floor + "\",\"floorType\": \"" + floorType + "\",\"area\": \"" + area + "\",\"dharoutiRate\": \"" + dharoutiRate + "\",\"dasturRate\": \"" + dasturRate + "\"}";
    }
}
