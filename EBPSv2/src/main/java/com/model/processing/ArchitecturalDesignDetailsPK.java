package com.model.processing;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArchitecturalDesignDetailsPK implements Serializable {

@Basic(optional = false)
@Column(name = "APPLICATION_NO")
private long applicationNo;
@Basic(optional = false)
@Column(name = "CLASS_ID")
private String classId;

public ArchitecturalDesignDetailsPK() {
}

public ArchitecturalDesignDetailsPK(long applicationNo, String classId) {
    this.applicationNo = applicationNo;
    this.classId = classId;
}

public long getApplicationNo() {
    return applicationNo;
}

public void setApplicationNo(long applicationNo) {
    this.applicationNo = applicationNo;
}

public String getClassId() {
    return classId;
}

public void setClassId(String classId) {
    this.classId = classId;
}

@Override
public int hashCode() {
    int hash = 0;
    hash += (int) applicationNo;
    hash += (classId != null ? classId.hashCode() : 0);
    return hash;
}

@Override
public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof ArchitecturalDesignDetailsPK)) {
        return false;
    }
    ArchitecturalDesignDetailsPK other = (ArchitecturalDesignDetailsPK) object;
    if (this.applicationNo != other.applicationNo) {
        return false;
    }
    if ((this.classId == null && other.classId != null) || (this.classId != null && !this.classId.equals(other.classId))) {
        return false;
    }
    return true;
}

@Override
public String toString() {
    return "{\"applicationNo=" + applicationNo + ", classId=" + classId + "}";
}

}
