package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_type_master")
public class UserTypeMaster implements java.io.Serializable {

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "DESIGNATION")
    private String designation;
    @Column(name = "DESIGNATION_NEPALI")
    private String designationNepali;
    @Column(name = "HIERARCHY", updatable = false)
    private int hierarchy;
    @Column(name = "APPROVE_COLUMN", updatable = false)
    private String approveColumn;
    @Column(name = "ACTION_STATUS", updatable = false)
    private String actionStatus;

    public UserTypeMaster() {
    }

    public UserTypeMaster(String id) {
        this.id = id;
    }

    public UserTypeMaster(String id, String designation, int hierarchy, String approveColumn, String actionStatus) {
        this.id = id;
        this.designation = designation;
        this.hierarchy = hierarchy;
        this.approveColumn = approveColumn;
        this.actionStatus = actionStatus;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(int hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getApproveColumn() {
        return approveColumn;
    }

    public void setApproveColumn(String approveColumn) {
        this.approveColumn = approveColumn;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getDesignationNepali() {
        return designationNepali;
    }

    public void setDesignationNepali(String designationNepali) {
        this.designationNepali = designationNepali;
    }

    @Override
    public String toString() {
        return "\n{\"id\": \"" + id + "\"}";
    }
}
