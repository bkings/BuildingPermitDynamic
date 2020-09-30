/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.application;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author MS
 */
@Embeddable
public class BuildingPermitSurroundingPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "APPLICATION_NO")
    private long applicationNo;
    @Basic(optional = false)
    @Column(name = "SIDE")
    private int side;

    public BuildingPermitSurroundingPK() {
    }

    public BuildingPermitSurroundingPK(long applicationNo, int side) {
        this.applicationNo = applicationNo;
        this.side = side;

    }

    public long getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) applicationNo;
        hash += (int) side;

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BuildingPermitSurroundingPK)) {
            return false;
        }
        BuildingPermitSurroundingPK other = (BuildingPermitSurroundingPK) object;
        if (this.applicationNo != other.applicationNo) {
            return false;
        }
        if (this.side != other.side) {
            return false;
        }

        return true;
    }

}
