package com.model.utility;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "organization_user_request")
public class OrganizationUserRequest implements java.io.Serializable {

    @Id
    @Column(name = "LOGIN_ID")
    private String loginId;
    @Column(name = "ID", updatable = false,unique = true,nullable = false)
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

    @Column(name = "JOIN_DATE", updatable = false)
    @Temporal(TemporalType.DATE)
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
    @Column(name = "CONSULTANCY_NAME")
    private String consultancyName;
    @Column(name = "APPROVE_BY")
    private String approveBy;
    @Column(name = "APPROVE_DATE")
    @Temporal(TemporalType.DATE)
    private Date approveDate;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
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

    public String getConsultancyName() {
        return consultancyName;
    }

    public void setConsultancyName(String consultancyName) {
        this.consultancyName = consultancyName;
    }

    public String getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    @Override
    public String toString() {
        return "{\"loginId\":\"" + loginId + "\",\"id\":\"" + id + "\",\"userName\":\"" + userName + "\",\"address\":\"" + address + "\",\"email\":\"" + email + "\",\"mobile\":\"" + mobile + "\",\"status\":\"" + status + "\",\"joinDate\":\"" + joinDate + "\",\"signature\":\"" + signature + "\",\"photo\":\"" + photo + "\",\"educationQualification\":\"" + educationQualification + "\",\"licenseNo\":\"" + licenseNo + "\",\"municipalRegNo\":\"" + municipalRegNo + "\",\"stamp\":\"" + stamp + "\",\"consultancyName\":\"" + consultancyName + "\",\"approveBy\":\"" + approveBy + "\",\"approveDate\":\"" + approveDate + '}';
    }

}
