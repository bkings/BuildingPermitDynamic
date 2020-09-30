package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mason_list")

public class MasonList {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "cotact_no")
    private String contactNo;
    @Column(name = "municipal_reg_no")
    private String municipalRegNo;
    @Column(name = "status")
    private String status;
    @Column(name = "photo")
    private String photo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getMunicipalRegNo() {
        return municipalRegNo;
    }

    public void setMunicipalRegNo(String municipalRegNo) {
        this.municipalRegNo = municipalRegNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "MasonList{" + "id=" + id + ", name=" + name + ", address=" + address + ", contactNo=" + contactNo + ", municipalRegNo=" + municipalRegNo + ", status=" + status + '}';
    }

}
