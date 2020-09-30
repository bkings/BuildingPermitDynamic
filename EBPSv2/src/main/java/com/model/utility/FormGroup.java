package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "form_group", uniqueConstraints = @UniqueConstraint(columnNames = {"form_id","group_type", "group_position", "user_type"}))
public class FormGroup implements java.io.Serializable {

private static final long serialVersionUID = 1L;
@Id
@Column(name = "ID")
private Integer id;
@JoinColumn(name = "group_id", referencedColumnName = "id")
@ManyToOne(optional = false)
private FormGroupMaster groupId;
@JoinColumn(name = "form_id", referencedColumnName = "id")
@ManyToOne(optional = false)
private FormNameMaster formId;
@JoinColumn(name = "user_type", referencedColumnName = "id")
@ManyToOne(optional = false)
private UserTypeMaster userType;
@Column(name = "group_position")
private Float groupPosition;
@Column(name = "previous_form")
private Integer previousForm;
@Column(name = "group_type")
private String groupType;

public Float getGroupPosition() {
    return groupPosition;
}

public void setGroupPosition(Float groupPosition) {
    this.groupPosition = groupPosition;
}

public Integer getId() {
    return id;
}

public void setId(Integer id) {
    this.id = id;
}

public FormNameMaster getFormId() {
    return formId;
}

public void setFormId(FormNameMaster formId) {
    this.formId = formId;
}

public FormGroupMaster getGroupId() {
    return groupId;
}

public void setGroupId(FormGroupMaster groupId) {
    this.groupId = groupId;
}

public UserTypeMaster getUserType() {
    return userType;
}

public void setUserType(UserTypeMaster userType) {
    this.userType = userType;
}

public Integer getPreviousForm() {
    return previousForm;
}

public void setPreviousForm(Integer previousForm) {
    this.previousForm = previousForm;
}

public String getGroupType() {
    return groupType;
}

public void setGroupType(String groupType) {
    this.groupType = groupType;
}

    @Override
    public String toString() {
        return "FormGroup{" + "id=" + id + ", groupId=" + groupId + ", formId=" + formId + ", userType=" + userType + ", groupPosition=" + groupPosition + ", previousForm=" + previousForm + ", groupType=" + groupType + '}';
    }

    public String getGroupType(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




}
