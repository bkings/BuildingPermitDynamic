package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "form_group_master")
public class FormGroupMaster implements java.io.Serializable {

@Id
@Column(name = "id")
private Integer id;
@Column(name = "name")
private String name;
@Column(name = "group_position")
private Integer groupPosition;

public FormGroupMaster() {
}

public FormGroupMaster(Integer id) {
    this.id = id;
}

public FormGroupMaster(String id) {
    this.id = Integer.parseInt(id);
}

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

public Integer getGroupPosition() {
    return groupPosition;
}

public void setGroupPosition(Integer groupPosition) {
    this.groupPosition = groupPosition;
}

@Override
public String toString() {
    return "\n{\"id\": \"" + id + "\",\"name\": \"" + name + "\"}";
}

}
