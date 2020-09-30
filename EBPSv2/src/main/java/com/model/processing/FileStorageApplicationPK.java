package com.model.processing;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FileStorageApplicationPK implements Serializable {

@Column(name = "application_no")
private Long applicationNo;
@Column(name = "file_type")
private Long fileType;
@Column(name = "file_no")
private Integer fileNo;

public FileStorageApplicationPK() {
}

public FileStorageApplicationPK(Long applicationNo, Long fileType, Integer fileNo) {
    this.applicationNo = applicationNo;
    this.fileType = fileType;
    this.fileNo = fileNo;
}

public Long getApplicationNo() {
    return applicationNo;
}

public void setApplicationNo(Long applicationNo) {
    this.applicationNo = applicationNo;
}

public Long getFileType() {
    return fileType;
}

public void setFileType(Long fileType) {
    this.fileType = fileType;
}

public Integer getFileNo() {
    return fileNo;
}

public void setFileNo(Integer fileNo) {
    this.fileNo = fileNo;
}

}
