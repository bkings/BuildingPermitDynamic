package com.model.application;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.UserTypeName;

@Entity
@Table(name = "applications_comments")
public class ApplicationsComments implements Serializable {

@EmbeddedId
protected ApplicationsCommentsPK pk;
@Column(name = "COMMENT", columnDefinition = "VARCHAR")
private String comment;
@Column(name = "REMARK", columnDefinition = "VARCHAR")
private String remark;
@Column(name = "PAGE", columnDefinition = "VARCHAR(10)")
private Integer page;
@Column(name = "USER_TYPE", columnDefinition = "VARCHAR(10)")
private String userType;
@JoinColumn(name = "APPLICATION_NO", referencedColumnName = "ID", insertable = false, updatable = false)
@ManyToOne(optional = false, fetch = FetchType.LAZY)
private BuildingPermitApplication applicationNo;

public void setPk(ApplicationsCommentsPK pk) {
    this.pk = pk;
}

public String getComment() {
    return comment;
}

public long getApplicationNo() {
    return pk.getApplicationNo();
}

public String getCommentBy() {
    return pk.getCommentBy();
}

public String getCommentDate() {
    return pk.getCommentDate();
}

public void setComment(String comment) {
    this.comment = comment;
}

public Integer getPage() {
    return page;
}


public void setPage(Integer page) {
    this.page = page;
}

public String getRemark() {
    return remark;
}

public void setRemark(String remark) {
    this.remark = remark;
}

public String getUserType() {
    return userType;
}

public String getUserTypeName() {
    return new UserTypeName().userTypeName(userType);
}

public void setUserType(String userType) {
    this.userType = userType;
}

@Override
public String toString() {
    return "{\"applicationNo\":\"" + pk.getApplicationNo() + "\",\"commentBy\":\"" + pk.getCommentBy() + "\",\"commentDate\":\"" + pk.getCommentDate() + "\",\"comment\":\"" + comment + "\"}";
}

}
