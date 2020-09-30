
package com.model.application;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BuildingMemberDetailsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "APPLICATION_NO")
    private long applicationNo;
    @Basic(optional = false)
    @Column(name = "MEMBER_NAME")
    private String memberName;

    public BuildingMemberDetailsPK() {
    }

    public BuildingMemberDetailsPK(long applicationNo, String memberName) {
        this.applicationNo = applicationNo;
        this.memberName = memberName;
    }

    public long getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) applicationNo;
        hash += (memberName != null ? memberName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BuildingMemberDetailsPK)) {
            return false;
        }
        BuildingMemberDetailsPK other = (BuildingMemberDetailsPK) object;
        if (this.applicationNo != other.applicationNo) {
            return false;
        }
        if ((this.memberName == null && other.memberName != null) || (this.memberName != null && !this.memberName.equals(other.memberName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.BuildingMenberDetailsPK[ applicationNo=" + applicationNo + ", memberName=" + memberName + " ]";
    }
    
}
