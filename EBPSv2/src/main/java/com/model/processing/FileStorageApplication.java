package com.model.processing;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.model.application.BuildingPermitApplication;
import com.model.utility.FileStorageCategory;

@Entity
@Table(name = "file_storage_application")
public class FileStorageApplication implements java.io.Serializable {

@EmbeddedId
protected FileStorageApplicationPK pk;
@Column(name = "file_url")
private String fileUrl;
@Column(name = "enter_date")
@Temporal(TemporalType.DATE)
private java.util.Date enterDate;
@Column(name = "enter_by")
private String enterBy;
@JoinColumn(name = "APPLICATION_NO", referencedColumnName = "ID", insertable = false, updatable = false)
@ManyToOne(optional = false, fetch = FetchType.LAZY)
private BuildingPermitApplication applicationNo;
@JoinColumn(name = "file_type", referencedColumnName = "ID", insertable = false, updatable = false)
@ManyToOne(optional = false, fetch = FetchType.EAGER)
private FileStorageCategory fileType;

public void setPk(FileStorageApplicationPK pk) {
    this.pk = pk;
}

public String getId() {
    return pk.getApplicationNo() + "-" + pk.getFileType() + "-" + pk.getFileNo();
}

public String getFileUrl() {
    return fileUrl;
}

public void setFileUrl(String fileUrl) {
    this.fileUrl = fileUrl;
}

public Date getEnterDate() {
    return enterDate;
}

public void setEnterDate(Date enterDate) {
    this.enterDate = enterDate;
}

public String getEnterBy() {
    return enterBy;
}

public void setEnterBy(String enterBy) {
    this.enterBy = enterBy;
}

public Object getApplicationNo() {
    return pk.getApplicationNo();
}

public void setApplicationNo(BuildingPermitApplication applicationNo) {
    this.applicationNo = applicationNo;
}

public Integer getFileNo() {
    return pk.getFileNo();
}

public Object getFileType() {
    return fileType;
}

public void setFileType(FileStorageCategory fileType) {
    this.fileType = fileType;
}

@Override
public String toString() {
    return "\n{\"applicationNo\": \"" + pk.getApplicationNo() + "\",\"fileType\": \"" + pk.getFileType() + "\",\"fileNo\": \"" + pk.getFileNo() + "\",\"fileUrl\": \"" + fileUrl + "\",\"enterDate\": \"" + enterDate + "\",\"enterBy\": \"" + enterBy + "\"}";
}
}
