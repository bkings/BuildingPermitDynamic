package com.model.application;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "application_approved")
public class ApplicationApproved implements Serializable {

@Id
private String comment;
private String status;
private String remark;

public String getComment() {
    return comment;
}

public void setComment(String comment) {
    this.comment = comment;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

public String getRemark() {
    return remark;
}

public void setRemark(String remark) {
    this.remark = remark;
}

    @Override
    public String toString() {
        return "{\"comment\":\"" + comment + ",\",\"status\":\"" + status + "\",\"remark\":\"" + remark + "\"}";
    }

}
