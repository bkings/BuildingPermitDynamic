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
@Table(name = "road_set_back_master")
public class RoadSetBackMaster implements Serializable {
@Id
@Column(name = "id")
private Long id;
@Column(name = "road_name")
private String roadName;
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

public String getRoadName() {
    return roadName;
}

public void setRoadName(String roadName) {
    this.roadName = roadName;
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
    return "RoadSetBackMaster{" + "id=" + id + ", roadName=" + roadName + ", setBackMeter=" + setBackMeter + ", setBackFoot=" + setBackFoot + ", status=" + status + '}';
}

}
