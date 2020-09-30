/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RajashowMasterPK implements java.io.Serializable {

    @Column(name = "ward_no", columnDefinition = "VARCHAR(2)")
    private String wardNo;
    @Column(name = "floor")
    private Integer floor;
    @Column(name = "floor_type", columnDefinition = "VARCHAR(3)")
    private String floorType;
    @Column(name = "area", columnDefinition = "VARCHAR(3)")
    private String area;
    @Column(name = "construction_type", columnDefinition = "VARCHAR(3)")
    private String constructionType;

    public RajashowMasterPK() {
    }

    public RajashowMasterPK(String wardNo, Integer floor, String floorType, String area, String constructionType) {
        this.wardNo = wardNo;
        this.floor = floor;
        this.floorType = floorType;
        this.area = area;
        this.constructionType = constructionType;
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

    public String getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(String constructionType) {
        this.constructionType = constructionType;
    }

}
