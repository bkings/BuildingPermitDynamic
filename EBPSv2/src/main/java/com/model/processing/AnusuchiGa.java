package com.model.processing;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.model.utility.OrganizationUser;

import model.DateConvert;

@Entity
@Table(name = "anusuchi_ga")
public class AnusuchiGa implements java.io.Serializable {

    @Id
    @Column(name = "APPLICATION_NO")
    private Long applicationNo;
    @Column(name = "ENTER_DATE")
    @Temporal(TemporalType.DATE)
    private java.util.Date enterDate;
    @JoinColumn(name = "enter_by", referencedColumnName = "login_id")
    @ManyToOne(optional = false)
    private OrganizationUser enterBy;

    @Column(name = "SER_NAME")
    private String serName;
    @Column(name = "SER_DATE")
    @Temporal(TemporalType.DATE)
    private java.util.Date serDate;
    @Column(name = "SER_STATUS")
    private String serStatus;

    @Column(name = "ER_NAME")
    private String erName;
    @Column(name = "ER_DATE")
    @Temporal(TemporalType.DATE)
    private java.util.Date erDate;
    @Column(name = "ER_STATUS")
    private String erStatus;

    @Column(name = "chief_NAME")
    private String chiefName;
    @Column(name = "chief_DATE")
    @Temporal(TemporalType.DATE)
    private java.util.Date chiefDate;
    @Column(name = "chief_STATUS")
    private String chiefStatus;

    @Column(name = "amini_name")
    private String aminiName;
    @Column(name = "amini_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date aminiDate;
    @Column(name = "AMINI_STATUS", updatable = false, columnDefinition = "VARCHAR(1) DEFAULT 'P'")
    private String aminiStatus;

    @Column(name = "RW_NAME", updatable = false)
    private String rwName;
    @Column(name = "RW_DATE", updatable = false)
    @Temporal(TemporalType.DATE)
    private java.util.Date rwDate;
    @Column(name = "RW_STATUS", updatable = false, columnDefinition = "VARCHAR(1) DEFAULT 'P'")
    private String rwStatus;

    public String getchiefName() {
        return chiefName;
    }

    public void setchiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    public Date getchiefDate() {
        return chiefDate;
    }

    public void setchiefDate(Date chiefDate) {
        this.chiefDate = chiefDate;
    }

    public String getchiefStatus() {
        return chiefStatus;
    }

    public void setchiefStatus(String chiefStatus) {
        this.chiefStatus = chiefStatus;
    }

    public Long getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(Long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public String getEnterDateBs() {
        try {
            return DateConvert.adtobsDate(enterDate);
        } catch (Exception e) {
        }
        return null;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public OrganizationUser getEnterBy() {
        return enterBy;
    }

    public void setEnterBy(OrganizationUser enterBy) {
        this.enterBy = enterBy;
    }

    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }

    public Date getSerDate() {
        return serDate;
    }

    public void setSerDate(Date serDate) {
        this.serDate = serDate;
    }

    public String getSerStatus() {
        return serStatus;
    }

    public void setSerStatus(String serStatus) {
        this.serStatus = serStatus;
    }

    public String getErName() {
        return erName;
    }

    public void setErName(String erName) {
        this.erName = erName;
    }

    public Date getErDate() {
        return erDate;
    }

    public void setErDate(Date erDate) {
        this.erDate = erDate;
    }

    public String getErStatus() {
        return erStatus;
    }

    public void setErStatus(String erStatus) {
        this.erStatus = erStatus;
    }

    public String getAminiName() {
        return aminiName;
    }

    public void setAminiName(String aminiName) {
        this.aminiName = aminiName;
    }

    public Date getAminiDate() {
        return aminiDate;
    }

    public void setAminiDate(Date aminiDate) {
        this.aminiDate = aminiDate;
    }

    public String getAminiStatus() {
        return aminiStatus;
    }

    public void setAminiStatus(String aminiStatus) {
        this.aminiStatus = aminiStatus;
    }

    public String getRwName() {
        return rwName;
    }

    public void setRwName(String rwName) {
        this.rwName = rwName;
    }

    public Date getRwDate() {
        return rwDate;
    }

    public void setRwDate(Date rwDate) {
        this.rwDate = rwDate;
    }

    public String getRwStatus() {
        return rwStatus;
    }

    public void setRwStatus(String rwStatus) {
        this.rwStatus = rwStatus;
    }

    @Override
    public String toString() {
        return "{\"applicationNo\":\"" + applicationNo + "\",\"enterDate\":\"" + enterDate + "\",\"enterBy\":\"" + enterBy + "\",\"serName\":\"" + serName + "\",\"serDate\":\"" + serDate + "\",\"serStatus\":\"" + serStatus + "\",\"erName\":\"" + erName + "\",\"erDate\":\"" + erDate + "\",\"erStatus\":\"" + erStatus + "\",\"chiefName\":\"" + chiefName + "\",\"chiefDate\":\"" + chiefDate + "\",\"chiefStatus\":\"" + chiefStatus + "\",\"aminiName\":\"" + aminiName + "\",\"aminiDate\":\"" + aminiDate + "\",\"aminiStatus\":\"" + aminiStatus + "\",\"rwName\":\"" + rwName + "\",\"rwDate\":\"" + rwDate + "\",\"rwStatus\":\"" + rwStatus + "\"}";
    }


    @Column(name = "E_NAME", updatable = false)
    private String eName;
    @Column(name = "E_DATE", updatable = false)
    @Temporal(TemporalType.DATE)
    private java.util.Date eDate;
    @Column(name = "E_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
    private String eStatus;

    @Column(name = "F_NAME", updatable = false)
    private String fName;
    @Column(name = "F_DATE", updatable = false)
    @Temporal(TemporalType.DATE)
    private java.util.Date fDate;
    @Column(name = "F_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
    private String fStatus;

    @Column(name = "G_NAME", updatable = false)
    private String gName;
    @Column(name = "G_DATE", updatable = false)
    @Temporal(TemporalType.DATE)
    private java.util.Date gDate;
    @Column(name = "G_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
    private String gStatus;

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public String geteStatus() {
        return eStatus;
    }

    public void seteStatus(String eStatus) {
        this.eStatus = eStatus;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public Date getfDate() {
        return fDate;
    }

    public void setfDate(Date fDate) {
        this.fDate = fDate;
    }

    public String getfStatus() {
        return fStatus;
    }

    public void setfStatus(String fStatus) {
        this.fStatus = fStatus;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public Date getgDate() {
        return gDate;
    }

    public void setgDate(Date gDate) {
        this.gDate = gDate;
    }

    public String getgStatus() {
        return gStatus;
    }

    public void setgStatus(String gStatus) {
        this.gStatus = gStatus;
    }


    
}
