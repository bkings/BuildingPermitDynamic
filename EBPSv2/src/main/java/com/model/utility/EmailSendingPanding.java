package com.model.utility;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "email_sending_panding")
public class EmailSendingPanding implements java.io.Serializable {

    @EmbeddedId
    protected EmailSendingPandingPK pk;
    @Column(name = "application_no", insertable = false, updatable = false)
    Long applicationNo;
    @Column(name = "email", insertable = false, updatable = false)
    private String email;
    @Column(name = "date_time", insertable = false, updatable = false)
    private String dateTime;
    @Column(name = "subject", columnDefinition = "VARCHAR(500)")
    private String subject;
    @Column(name = "body", columnDefinition = "VARCHAR(5000)")
    private String body;
    @Column(name = "status", columnDefinition = "VARCHAR(1)")
    private String status;

    public EmailSendingPanding() {
    }

    public EmailSendingPanding(Long applicationNo, String email, String dateTime, String subject, String body, String status, String enterBY) {
        this.pk = new EmailSendingPandingPK(applicationNo, email, dateTime, enterBY);
        this.subject = subject;
        this.body = body;
        this.status = status;
    }

    public void setPk(EmailSendingPandingPK pk) {
        this.pk = pk;
    }

    public String getId() {
        return pk.getApplicationNo() + pk.getEmail() + pk.getDateTime();
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\n{\"email\": \"" + email + "\",\"dateTime\": \"" + dateTime + "\",\"subject\": \"" + subject + "\",\"body\": \"" + body + "\",\"status\": \"" + status + "\"}";
    }
}
