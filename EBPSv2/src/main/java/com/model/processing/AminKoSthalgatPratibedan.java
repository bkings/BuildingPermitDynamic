package com.model.processing;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "amin_ko_sthalgat_pratibedan")
public class AminKoSthalgatPratibedan implements java.io.Serializable {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "amini_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date aminiDate;
    @Column(name = "amini_name")
    private String aminiName;
    @Column(name = "amini_status")
    private String aminiStatus;
    @Column(name = "application_no")
    private Long applicationNo;
    @Column(name = "chief_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date chiefDate;
    @Column(name = "chief_name")
    private String chiefName;
    @Column(name = "chief_status")
    private String chiefStatus;
    @Column(name = "enter_by")
    private String enterBy;
    @Column(name = "enter_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date enterDate;
    @Column(name = "er_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date erDate;
    @Column(name = "er_name")
    private String erName;
    @Column(name = "er_status")
    private String erStatus;
    @Column(name = "json_data", columnDefinition = "VARCHAR")
    private String jsonData;
    @Column(name = "rw_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date rwDate;
    @Column(name = "rw_name")
    private String rwName;
    @Column(name = "rw_status")
    private String rwStatus;
    @Column(name = "ser_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date serDate;
    @Column(name = "ser_name")
    private String serName;
    @Column(name = "ser_status")
    private String serStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAminiDate() {
        return aminiDate;
    }

    public void setAminiDate(Date aminiDate) {
        this.aminiDate = aminiDate;
    }

    public String getAminiName() {
        return aminiName;
    }

    public void setAminiName(String aminiName) {
        this.aminiName = aminiName;
    }

    public String getAminiStatus() {
        return aminiStatus;
    }

    public void setAminiStatus(String aminiStatus) {
        this.aminiStatus = aminiStatus;
    }

    public Long getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(Long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Date getChiefDate() {
        return chiefDate;
    }

    public void setChiefDate(Date chiefDate) {
        this.chiefDate = chiefDate;
    }

    public String getChiefName() {
        return chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    public String getChiefStatus() {
        return chiefStatus;
    }

    public void setChiefStatus(String chiefStatus) {
        this.chiefStatus = chiefStatus;
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

    public Date getErDate() {
        return erDate;
    }

    public void setErDate(Date erDate) {
        this.erDate = erDate;
    }

    public String getErName() {
        return erName;
    }

    public void setErName(String erName) {
        this.erName = erName;
    }

    public String getErStatus() {
        return erStatus;
    }

    public void setErStatus(String erStatus) {
        this.erStatus = erStatus;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public Date getRwDate() {
        return rwDate;
    }

    public void setRwDate(Date rwDate) {
        this.rwDate = rwDate;
    }

    public String getRwName() {
        return rwName;
    }

    public void setRwName(String rwName) {
        this.rwName = rwName;
    }

    public String getRwStatus() {
        return rwStatus;
    }

    public void setRwStatus(String rwStatus) {
        this.rwStatus = rwStatus;
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

    public String getSerStatus() {
        return serStatus;
    }

    public void setSerStatus(String serStatus) {
        this.serStatus = serStatus;
    }

    @Override
    public String toString() {
        return "\n{\"id\": \"" + id + "\",\"aminiDate\": \"" + aminiDate + "\",\"aminiName\": \"" + aminiName + "\",\"aminiStatus\": \"" + aminiStatus + "\",\"applicationNo\": \"" + applicationNo + "\",\"chiefDate\": \"" + chiefDate + "\",\"chiefName\": \"" + chiefName + "\",\"chiefStatus\": \"" + chiefStatus + "\",\"enterBy\": \"" + enterBy + "\",\"enterDate\": \"" + enterDate + "\",\"erDate\": \"" + erDate + "\",\"erName\": \"" + erName + "\",\"erStatus\": \"" + erStatus + "\",\"jsonData\": \"" + jsonData + "\",\"rwDate\": \"" + rwDate + "\",\"rwName\": \"" + rwName + "\",\"rwStatus\": \"" + rwStatus + "\",\"serDate\": \"" + serDate + "\",\"serName\": \"" + serName + "\",\"serStatus\": \"" + serStatus + "\"}";
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
