package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu_master")
public class MenuMaster implements java.io.Serializable {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "menu")
    private String menu;
    @Column(name = "menu_type")
    private String menuType;
    @Column(name = "url")
    private String url;

    public MenuMaster() {
    }

    public MenuMaster(Long id) {
        this.id = id;
    }

    public MenuMaster(String id) {
        this.id = Long.parseLong(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

}
