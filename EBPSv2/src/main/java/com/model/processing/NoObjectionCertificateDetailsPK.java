package com.model.processing;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NoObjectionCertificateDetailsPK implements Serializable {

	@Basic(optional = false)
	@Column(name = "APPLICATION_NO")
	private long applicationNo;
	@Basic(optional = false)
	@Column(name = "CLASS_ID")
	private int classId;

	public NoObjectionCertificateDetailsPK() {
	}

	public NoObjectionCertificateDetailsPK(long applicationNo, int classId) {
	    this.applicationNo = applicationNo;
	    this.classId = classId;
	}

	public long getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(long applicationNo) {
		this.applicationNo = applicationNo;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 19 * hash + (int) (this.applicationNo ^ (this.applicationNo >>> 32));
		hash = 19 * hash + this.classId;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final NoObjectionCertificateDetailsPK other = (NoObjectionCertificateDetailsPK) obj;
		if (this.applicationNo != other.applicationNo) {
			return false;
		}
		if (this.classId != other.classId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "{\"applicationNo=" + applicationNo + ", classId=" + classId + "}";
	}

}
