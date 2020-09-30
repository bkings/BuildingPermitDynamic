package com.model.application;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.UserTypeName;

import model.Message;

@Entity
@Table(name = "application_history")
public class ApplicationHistory implements Serializable {

@EmbeddedId
protected ApplicationHistoryPK pk;
@Column(name = "STATUS", columnDefinition = "VARCHAR(1)")
private String status;
@Column(name = "ENTER_BY", columnDefinition = "VARCHAR(100)")
private String enterBy;
//@Column(name = "UserType", columnDefinition = "VARCHAR(100)")

public void setPk(ApplicationHistoryPK pk) {
    this.pk = pk;
}

public String getStatus() {
    return Message.status(status);
}

public void setStatus(String status) {
    this.status = status;
}

public Long getApplicationNo() {
    return pk.getApplicationNo();
}

public String getUserType() {
    return new UserTypeName().userTypeName(pk.getUserType());
}

public String getPageNo() {
    return pk.getPageNo();
}

public String getActionDate() {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    return df.format(pk.getActionDate());
}

public String getEnterBy() {
    return enterBy;
}

public void setEnterBy(String enterBy) {
    this.enterBy = enterBy;
}

}
