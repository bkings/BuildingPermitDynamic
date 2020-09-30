/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.utility;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "other_set_back_master")

public class OtherSetBackMaster implements Serializable{ 
@Id
@Column(name = "id")
private Long id;
@Column(name = "place_name")
private String placeName;
@Column(name = "set_back_meter")
private Long setBackMeter;
@Column(name = "set_back_foot")
private Long setBackFoot;
@Column(name = "status")
private String status;

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getPlaceName() {
    return placeName;
}

public void setPlaceName(String placeName) {
    this.placeName = placeName;
}

public Long getSetBackMeter() {
    return setBackMeter;
}

public void setSetBackMeter(Long setBackMeter) {
    this.setBackMeter = setBackMeter;
}

public Long getSetBackFoot() {
    return setBackFoot;
}

public void setSetBackFoot(Long setBackFoot) {
    this.setBackFoot = setBackFoot;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

@Override
public String toString() {
    return "OtherSetBackMaster{" + "id=" + id + ", placeName=" + placeName + ", setBackMeter=" + setBackMeter + ", setBackFoot=" + setBackFoot + ", status=" + status + '}';
}

}
