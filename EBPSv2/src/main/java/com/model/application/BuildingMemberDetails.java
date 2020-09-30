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
@Table(name = "building_member_details")
public class BuildingMemberDetails implements Serializable {

    @EmbeddedId
    private BuildingMemberDetailsPK pk;
    @Column(name = "MEMBER_NAME", insertable = false, updatable = false)
    private String memberName;
    @Basic(optional = false)
    @Column(name = "RELATION")
    private String relation;

    @JoinColumn(name = "application_no", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BuildingPermitApplication applicationNo;

    public BuildingMemberDetails() {
    }

    public BuildingMemberDetails(BuildingMemberDetailsPK pk) {
        this.pk = pk;
    }

    public void setPk(BuildingMemberDetailsPK pk) {
        this.pk = pk;
    }

    public BuildingMemberDetailsPK getPk() {
        return pk;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Override
    public String toString() {
        return "\n{\"memberName\":\"" + memberName + "\",\"relation\":\"" + relation + "\"}";
    }

}
