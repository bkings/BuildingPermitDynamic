/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.application;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "public_downloads")
public class PublicDownloads {
  @Id 
  @Column(name = "id")
  private Integer id;
  
  @Column(name = "file_url")
  private String fileUrl;
  
  @Column(name = "file_type")
  private String fileType;
  
  @Column(name = "upload_date", updatable = false)
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
//@JsonFormat(pattern="yyyy-MM-dd")
  private java.util.Date uploadDate;
  
  
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "PublicDownloads{" + "id=" + id + ", fileUrl=" + fileUrl + ", fileType=" + fileType + ", uploadDate=" + uploadDate + '}';
    }
    
    
  
    
}
