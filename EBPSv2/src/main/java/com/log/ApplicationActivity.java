package com.log;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "application_activity")
public class ApplicationActivity implements Serializable {

@EmbeddedId
protected ApplicationActivityPK pk;
@Column(name = "ACTIVITY", columnDefinition = "VARCHAR")
private String activity;

public ApplicationActivity() {
}

public ApplicationActivity(ApplicationActivityPK pk, String activity) {
    this.pk = pk;
    this.activity = activity;
}

public ApplicationActivity(String activity) {
    this.activity = activity;
}

public void setPk(ApplicationActivityPK pk) {
    this.pk = pk;
}

public String getActivity() {
    return activity;
}

public void setActivity(String activity) {
    this.activity = activity;
}

public String getApi() {
    return pk.getApi();
}

public String getMethod() {
    return pk.getMethod();
}

public Date getAccessTime() {
    return pk.getAccessTime();
}

public String getLoginBy() {
    return pk.getLoginBy();
}

}
