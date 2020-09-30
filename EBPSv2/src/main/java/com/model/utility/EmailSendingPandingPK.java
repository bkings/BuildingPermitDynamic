/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmailSendingPandingPK implements java.io.Serializable {

    @Column(name = "application_no")
    private Long applicationNo;
    @Column(name = "email", columnDefinition = "VARCHAR(150)")
    private String email;
    @Column(name = "date_time", columnDefinition = "VARCHAR(150)")
    private String dateTime;
    @Column(name = "enter_by_id", columnDefinition = "VARCHAR(150)")
    private String enterById;

    public EmailSendingPandingPK() {
    }

    public EmailSendingPandingPK(Long applicationNo, String email, String dateTime, String enterById) {
        this.applicationNo = applicationNo;
        this.email = email;
        this.dateTime = dateTime;
        this.enterById = enterById;
    }

    public Long getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(Long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getEnterById() {
        return enterById;
    }

    public void setEnterById(String enterById) {
        this.enterById = enterById;
    }

}
