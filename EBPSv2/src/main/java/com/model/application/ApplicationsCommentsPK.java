/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.application;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author MS
 */
@Embeddable
public class ApplicationsCommentsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "APPLICATION_NO")
    private long applicationNo;
    @Basic(optional = false)
    @Column(name = "COMMENT_BY")
    private String commentBy;
    @Basic(optional = false)
    @Column(name = "COMMENT_DATE",columnDefinition = "timestamp")
    private String commentDate;

    public ApplicationsCommentsPK() {
    }

    public ApplicationsCommentsPK(long applicationNo, String commentBy, String commentDate) {
        this.applicationNo = applicationNo;
        this.commentBy = commentBy;
        this.commentDate = commentDate;
    }

    public long getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(String commentBy) {
        this.commentBy = commentBy;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) applicationNo;
        hash += (commentBy != null ? commentBy.hashCode() : 0);
        hash += (commentDate != null ? commentDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationsCommentsPK)) {
            return false;
        }
        ApplicationsCommentsPK other = (ApplicationsCommentsPK) object;
        if (this.applicationNo != other.applicationNo) {
            return false;
        }
        if ((this.commentBy == null && other.commentBy != null) || (this.commentBy != null && !this.commentBy.equals(other.commentBy))) {
            return false;
        }
        if ((this.commentDate == null && other.commentDate != null) || (this.commentDate != null && !this.commentDate.equals(other.commentDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ApplicationsCommentsPK{" + "applicationNo=" + applicationNo + ", commentBy=" + commentBy + ", commentDate=" + commentDate + '}';
    }

    
}
