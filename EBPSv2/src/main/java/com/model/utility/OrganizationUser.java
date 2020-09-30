package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.DateConvert;

@Entity
@Table(name = "organization_user")
public class OrganizationUser implements java.io.Serializable {

    @Id
    @Column(name = "LOGIN_ID")
    private String loginId;
    @Column(name = "ID", updatable = false)
    private long id;
    @Column(name = "USER_NAME", columnDefinition = "VARCHAR")
    private String userName;
    @Column(name = "ADDRESS", columnDefinition = "VARCHAR")
    private String address;
    @Column(name = "EMAIL", unique = true)
    private String email;
    @Column(name = "MOBILE")
    private String mobile;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "TOKEN", columnDefinition = "VARCHAR", updatable = false)
    private String token;
    @Column(name = "DB_PASSWORD", updatable = false)
    private String dbPassword;
    @Column(name = "JOIN_DATE", updatable = false)
    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//@JsonFormat(pattern="yyyy-MM-dd")
    private java.util.Date joinDate;
    @Column(name = "SIGNATURE", updatable = false)
    private String signature;
    @Column(name = "PHOTO", updatable = false)
    private String photo;
    @Column(name = "EDUCATION_QUALIFICATION", columnDefinition = "VARCHAR")
    private String educationQualification;
    @Column(name = "LICENSE_NO", columnDefinition = "VARCHAR")
    private String licenseNo;
    @Column(name = "MUNICIPAL_REG_NO", columnDefinition = "VARCHAR")
    private String municipalRegNo;
    @Column(name = "STAMP", updatable = false)
    private String stamp;
    @Column(name = "consultancy_name")
    private String consultancyName;
    @Column(name = "VALID_DATE", updatable = true)
    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date validDate;
    @JoinColumn(name = "USER_TYPE", referencedColumnName = "ID", updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UserTypeMaster userType;

    public OrganizationUser() {
    }

    public OrganizationUser(String loginId) {
        this.loginId = loginId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getJoinDate() {
        return DateConvert.toStringDateTime(joinDate);
    }

    public String getJoinDateBS() {
        try {
            if (joinDate == null) {
                return "";
            }
        } catch (Exception e) {
        }
        return DateConvert.adtobsDate(joinDate);
    }
//    public void setJoinDate(Date joinDate) {
//        this.joinDate = joinDate;
//    }

    public String getValidDateBS() {
        if (validDate == null) {
            return "";
        }
        return DateConvert.adtobsDate(validDate);
    }
//public void setValidDate(Date validDate) {
//    this.validDate = validDate;
//}

    public String getLoginId() {
        return loginId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setUserType(UserTypeMaster userType) {
        this.userType = userType;
    }

    public UserTypeMaster getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJoinDate(String joinDate) {
//    System.out.println("joinDate: " + joinDate);
        this.joinDate = DateConvert.toDate(joinDate);
//    System.out.println("joinDate: " + joinDate);
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEducationQualification() {
        return educationQualification;
    }

    public void setEducationQualification(String educationQualification) {
        this.educationQualification = educationQualification;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getMunicipalRegNo() {
        return municipalRegNo;
    }

    public void setMunicipalRegNo(String municipalRegNo) {
        this.municipalRegNo = municipalRegNo;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public Object getValidDate() {
        if (validDate == null) {
            return "";
        }

        return DateConvert.toStringDateTime(validDate);
    }

    public void setValidDate(String validDate) {
        this.validDate = DateConvert.toDate(validDate);
    }

    public String getDbPassword() {
        return "";
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getConsultancyName() {
        return consultancyName;
    }

    public void setConsultancyName(String consultancyName) {
        this.consultancyName = consultancyName;
    }

    @Override
    public String toString() {
        return "OrganizationUser{" + "loginId=" + loginId + ", id=" + id + ", userName=" + userName + ", address=" + address + ", email=" + email + ", mobile=" + mobile + ", status=" + status + ", token=" + token + ", dbPassword=" + dbPassword + ", joinDate=" + joinDate + ", signature=" + signature + ", photo=" + photo + ", educationQualification=" + educationQualification + ", licenseNo=" + licenseNo + ", municipalRegNo=" + municipalRegNo + ", stamp=" + stamp + ", validDate=" + validDate + ", userType=" + userType + '}';
    }

}
