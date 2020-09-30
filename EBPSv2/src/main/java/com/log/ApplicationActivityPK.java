/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.log;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author kamal
 */
@Embeddable
public class ApplicationActivityPK implements Serializable {

@Column(name = "API", columnDefinition = "VARCHAR(200)")
private String api;
@Column(name = "METHOD", columnDefinition = "VARCHAR(8)")
private String method;
@Column(name = "ACCESS_TIME", columnDefinition = "timestamp ")
private Date accessTime;
@Column(name = "LOGIN_BY", columnDefinition = "VARCHAR(30)")
private String loginBy;

public ApplicationActivityPK() {
}

public ApplicationActivityPK(String api, String method, String loginBy) {
    this.api = api;
    this.method = method;
    this.loginBy = loginBy;
    accessTime = new Date();
}

public String getApi() {
    return api;
}

public String getMethod() {
    return method;
}

public Date getAccessTime() {
    return accessTime;
}

public String getLoginBy() {
    return loginBy;
}

public void setApi(String api) {
    this.api = api;
}

public void setMethod(String method) {
    this.method = method;
}

public void setAccessTime(Date accessTime) {
    this.accessTime = accessTime;
}

public void setLoginBy(String loginBy) {
    this.loginBy = loginBy;
}

}
