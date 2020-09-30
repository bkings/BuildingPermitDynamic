package com.model.utility;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "menu_user_access")
public class MenuUserAccess implements java.io.Serializable {

    @EmbeddedId
    protected MenuUserAccessPK pk;
    @Column(name = "status")
    private String status;
    @Column(name = "USER_TYPE", insertable = false, updatable = false)
    private String userType;
    @JoinColumn(name = "MENU", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MenuMaster menu;

    public MenuUserAccess() {
    }

    public MenuUserAccess(Long menu, String userType, String status) {
        this.pk = new MenuUserAccessPK(menu, userType);
        this.status = status;
    }

    public String getId() {
        return menu.getId() + "-" + userType;
    }

    public Long getMenu() {
        return menu.getId();
    }

    public MenuMaster getMenuMaster() {
        return menu;
    }

    public void setMenu(MenuMaster menu) {
        this.menu = menu;
    }

    public void setPk(MenuUserAccessPK pk) {
        this.pk = pk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "\n{\"menu\": \"" + pk.getMenu() + "\",\"userType\": \"" + pk.getUserType() + "\",\"status\": \"" + status + "\"}";
    }
}
