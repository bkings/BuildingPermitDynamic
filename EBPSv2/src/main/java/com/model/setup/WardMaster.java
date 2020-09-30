package com.model.setup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ward_master")
public class WardMaster implements java.io.Serializable {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\n{\"id\": \"" + id + "\",\"name\": \"" + name + "\"}";
    }
}
