package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MenuUserAccessPK implements java.io.Serializable {

    @Column(name = "menu")
    private Long menu;
    @Column(name = "USER_TYPE")
    private String userType;

    public MenuUserAccessPK() {
    }

    public MenuUserAccessPK(Long menu, String userType) {
        this.menu = menu;
        this.userType = userType;
    }

    public Long getMenu() {
        return menu;
    }

    public void setMenu(Long menu) {
        this.menu = menu;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
