/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import model.DateConvert;

@Entity
@Table(name = "designer_renew_status")
public class DesignerRenewStatus implements java.io.Serializable {

@Id
@Column(name = "id")
private Long id;
@Column(name = "designer_id")
private Long designerId;
@Column(name = "till_date")
@Temporal(TemporalType.DATE)
private java.util.Date tilldate;
@Column(name = "renew_date")
@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "yyyy-MM-dd")
private java.util.Date renewDate;
@Column(name = "renew_by")
private String renewBy;
@Column(name = "fiscal_year")
private String fiscalYear;
@Column(name = "file_url")
private String fileUrl;
@Column(name = "bill_no")
private String billNo;

public DesignerRenewStatus() {
}

public DesignerRenewStatus(Long id) {
    this.id = id;
}

public DesignerRenewStatus(String id) {
    this.id = Long.parseLong(id);
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public Long getDesignerId() {
    return designerId;
}

public void setDesignerId(Long designerId) {
    this.designerId = designerId;
}

public String getTilldate() {
    if(tilldate==null){
        return "";
    }
    
    return DateConvert.toStringDateTime(tilldate);
}

public String getTillDateBS() {
    if(tilldate==null){
        return "";
    }
    
    return DateConvert.adtobsDate(tilldate);
}

public void setTilldate(String tilldate) {
    
    this.tilldate = DateConvert.toDate(tilldate);
}

public String getRenewDate() {
    if(renewDate==null){
        return "";
    }
    return DateConvert.toStringDateTime(renewDate);
}

public String getRenewDateBS() {
    if(renewDate==null){
        return "";
    }
    return DateConvert.adtobsDate(renewDate);
}

public void setRenewDate(String renewDate) {
    this.renewDate = DateConvert.toDate(renewDate);
}

public String getRenewBy() {
    return renewBy;
}

public void setRenewBy(String renewBy) {
    this.renewBy = renewBy;
}

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    @Override
    public String toString() {
        return "DesignerRenewStatus{" + "id=" + id + ", designerId=" + designerId + ", tilldate=" + tilldate + ", renewDate=" + renewDate + ", renewBy=" + renewBy + ", fiscalYear=" + fiscalYear + ", fileUrl=" + fileUrl + ", billNo=" + billNo + '}';
    }
    
    

    
   

    


}
