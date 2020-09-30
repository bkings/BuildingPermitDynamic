package com.model.application;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BuildingPermitFloorPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "APPLICATION_NO")
    private Long applicationNo;
    @Basic(optional = false)
    @Column(name = "FLOOR")
    private Integer floor;
    @Column(name = "block")
    private String block;

    public BuildingPermitFloorPK() {
    }

    public BuildingPermitFloorPK(Long applicationNo, Integer floor, String block) {
        this.applicationNo = applicationNo;
        this.floor = floor;
        this.block = block;
    }

    public Long getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(Long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.applicationNo);
        hash = 79 * hash + Objects.hashCode(this.floor);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BuildingPermitFloorPK)) {
            return false;
        }
        BuildingPermitFloorPK other = (BuildingPermitFloorPK) object;
        if (this.applicationNo != other.applicationNo) {
            return false;
        }
        if (this.floor != other.floor) {
            return false;
        }
        return true;
    }

}
