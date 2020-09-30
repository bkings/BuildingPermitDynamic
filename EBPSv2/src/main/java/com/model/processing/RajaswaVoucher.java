package com.model.processing;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "rajaswa_voucher")
public class RajaswaVoucher implements java.io.Serializable {

@Id
@Column(name = "application_no")
private Long applicationNo;
@Column(name = "amt_of_dharauti")
private Float amtOfDharauti;
@Column(name = "amount_in_words")
private String amountInWords;
@Column(name = "amt_received_date")
private String amtReceivedDate;
@Column(name = "rashid_number")
private String rashidNumber;
@Column(name = "receive_sign")
private String receiveSign;
@Column(name = "voucher_url")
private String voucherUrl;

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


public RajaswaVoucher(Long applicationNo, Float amtOfDharauti, String amountInWords, String amtReceivedDate, String rashidNumber, String receiveSign) {
    this.applicationNo = applicationNo;
    this.amtOfDharauti = amtOfDharauti;
    this.amountInWords = amountInWords;
    this.amtReceivedDate = amtReceivedDate;
    this.rashidNumber = rashidNumber;
    this.receiveSign = receiveSign;
  
}

public RajaswaVoucher() {
}

public Long getApplicationNo() {
    return applicationNo;
}

public void setApplicationNo(Long applicationNo) {
    this.applicationNo = applicationNo;
}

public Float getAmtOfDharauti() {
    return amtOfDharauti;
}

public void setAmtOfDharauti(Float amtOfDharauti) {
    this.amtOfDharauti = amtOfDharauti;
}

public String getAmountInWords() {
    return amountInWords;
}

public void setAmountInWords(String amountInWords) {
    this.amountInWords = amountInWords;
}

public String getAmtReceivedDate() {
    return amtReceivedDate;
}

public void setAmtReceivedDate(String amtReceivedDate) {
    this.amtReceivedDate = amtReceivedDate;
}

public String getRashidNumber() {
    return rashidNumber;
}

public void setRashidNumber(String rashidNumber) {
    this.rashidNumber = rashidNumber;
}

public String getReceiveSign() {
    return receiveSign;
}

public void setReceiveSign(String receiveSign) {
    this.receiveSign = receiveSign;
}

public String getVoucherUrl() {
    return voucherUrl;
}

public void setVoucherUrl(String voucherUrl) {
    this.voucherUrl = voucherUrl;
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
        return "RajaswaVoucher{" + "applicationNo=" + applicationNo + ", amtOfDharauti=" + amtOfDharauti + ", amountInWords=" + amountInWords + ", amtReceivedDate=" + amtReceivedDate + ", rashidNumber=" + rashidNumber + ", receiveSign=" + receiveSign + ", voucherUrl=" + voucherUrl + ", enterBy=" + enterBy + ", enterDate=" + enterDate + ", aminiName=" + aminiName + ", aminiDate=" + aminiDate + ", aminiStatus=" + aminiStatus + ", serDate=" + serDate + ", serName=" + serName + ", erName=" + erName + ", erDate=" + erDate + ", erStatus=" + erStatus + ", serStatus=" + serStatus + ", chiefName=" + chiefName + ", chiefDate=" + chiefDate + ", chiefStatus=" + chiefStatus + ", rwName=" + rwName + ", rwDate=" + rwDate + ", rwStatus=" + rwStatus + '}';
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
