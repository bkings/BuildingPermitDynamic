/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.processing;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "dosrocharan_prabidhik")
public class DosrocharanPrabidhik implements Serializable {

    @Id
    @Column(name = "application_no")
    private Long applicationNo;
    @Column(name = "json_data", columnDefinition = "varchar")
    private String jsonData;
    @Column(name = "enter_by")
    private String enterBy;
    @Column(name = "enter_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date enterDate;
    @Column(name = "amini_name")
    private String aminiName;
    @Column(name = "amini_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date aminiDate;
    @Column(name = "AMINI_STATUS", updatable = false, columnDefinition = "VARCHAR(1) DEFAULT 'P'")
    private String aminiStatus;
    @Column(name = "ser_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date serDate;
    @Column(name = "ser_name")
    private String serName;
    @Column(name = "er_name")
    private String erName;
    @Column(name = "er_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date erDate;
    @Column(name = "er_status")
    private String erStatus;
    @Column(name = "ser_status")
    private String serStatus;
    @Column(name = "chief_name")
    private String chiefName;
    @Column(name = "chief_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date chiefDate;
    @Column(name = "chief_status")
    private String chiefStatus;
    @Column(name = "RW_NAME", updatable = false)
    private String rwName;
    @Column(name = "RW_DATE", updatable = false)
    @Temporal(TemporalType.DATE)
    private java.util.Date rwDate;
    @Column(name = "RW_STATUS", updatable = false, columnDefinition = "VARCHAR(1) DEFAULT 'P'")
    private String rwStatus;
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

    public Long getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(Long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getEnterBy() {
        return enterBy;
    }

    public void setEnterBy(String enterBy) {
        this.enterBy = enterBy;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
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

    public Date getSerDate() {
        return serDate;
    }

    public void setSerDate(Date serDate) {
        this.serDate = serDate;
    }

    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
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

    public String getSerStatus() {
        return serStatus;
    }

    public void setSerStatus(String serStatus) {
        this.serStatus = serStatus;
    }

    public String getChiefName() {
        return chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    public Date getChiefDate() {
        return chiefDate;
    }

    public void setChiefDate(Date chiefDate) {
        this.chiefDate = chiefDate;
    }

    public String getChiefStatus() {
        return chiefStatus;
    }

    public void setChiefStatus(String chiefStatus) {
        this.chiefStatus = chiefStatus;
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

    public String getEName() {
        return eName;
    }

    public void setEName(String eName) {
        this.eName = eName;
    }

    public Date getEDate() {
        return eDate;
    }

    public void setEDate(Date eDate) {
        this.eDate = eDate;
    }

    public String getEStatus() {
        return eStatus;
    }

    public void setEStatus(String eStatus) {
        this.eStatus = eStatus;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public Date getFDate() {
        return fDate;
    }

    public void setFDate(Date fDate) {
        this.fDate = fDate;
    }

    public String getFStatus() {
        return fStatus;
    }

    public void setFStatus(String fStatus) {
        this.fStatus = fStatus;
    }

    public String getGName() {
        return gName;
    }

    public void setGName(String gName) {
        this.gName = gName;
    }

    public Date getGDate() {
        return gDate;
    }

    public void setGDate(Date gDate) {
        this.gDate = gDate;
    }

    public String getGStatus() {
        return gStatus;
    }

    public void setGStatus(String gStatus) {
        this.gStatus = gStatus;
    }

    @Override
    public String toString() {
        return "\n{\"applicationNo\":\"" + applicationNo+"\",\"jsonData\":\"" + jsonData+"\",\"enterBy\":\"" + enterBy+"\",\"enterDate\":\"" + enterDate+"\",\"aminiName\":\"" + aminiName+"\",\"aminiDate\":\"" + aminiDate+"\",\"aminiStatus\":\"" + aminiStatus+"\",\"serDate\":\"" + serDate+"\",\"serName\":\"" + serName+"\",\"erName\":\"" + erName+"\",\"erDate\":\"" + erDate+"\",\"erStatus\":\"" + erStatus+"\",\"serStatus\":\"" + serStatus+"\",\"chiefName\":\"" + chiefName+"\",\"chiefDate\":\"" + chiefDate+"\",\"chiefStatus\":\"" + chiefStatus+"\",\"rwName\":\"" + rwName+"\",\"rwDate\":\"" + rwDate+"\",\"rwStatus\":\"" + rwStatus+"\",\"eName\":\"" + eName+"\",\"eDate\":\"" + eDate+"\",\"eStatus\":\"" + eStatus+"\",\"fName\":\"" + fName+"\",\"fDate\":\"" + fDate+"\",\"fStatus\":\"" + fStatus+"\",\"gName\":\"" + gName+"\",\"gDate\":\"" + gDate+"\",\"gStatus\":\"" + gStatus + "\"}";
    }

}
