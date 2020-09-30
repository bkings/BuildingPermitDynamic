/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.application;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "building_permit_surrounding")
public class BuildingPermitSurrounding implements Serializable {

	@EmbeddedId
	protected BuildingPermitSurroundingPK pk;

	@Column(name = "SIDE", insertable = false, updatable = false)
	private int side;
	@Column(name = "SANDHIYAR")
	private String sandhiyar;
	@Column(name = "FEET")
	private String feet;
	@Basic(optional = false)
	@Column(name = "KITTA_NO")
	private String kittaNo;
	@Column(name = "side_unit", columnDefinition = "VARCHAR(10) DEFAULT NULL")
	private String sideUnit;

	@Column(name = "LANDSCAPE_TYPE")
	private String landScapeType;
	@Column(name = "LANDSCAPE_NAME")
	private String landScapeName;
	@Column(name = "ACTUAL_SETBACK")
	private String actualSetback;
	@Column(name = "ROAD_WIDTH")
	private String roadWidth;
	@Column(name = "ROAD_STANDARD_SET_BACK")
	private String roadStandardSetback;
	@Column(name = "DISTANCE_FROM_HIGH_TENSION_LINE")
	private String distanceFromHighTensionLine;
	@Column(name = "DISTANCE_FROM_RIVER")
	private String distanceFromRiver;
	@Column(name = "DOOR_OR_WINDOWS")
	private String doorOrWindows;
	@Column(name = "DISTANCE_FROM_BORDER")
	private String distanceFromBorder;

	@JoinColumn(name = "APPLICATION_NO", referencedColumnName = "ID", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private BuildingPermitApplication applicationNo;

	public BuildingPermitSurrounding() {
	}

	public BuildingPermitSurrounding(BuildingPermitSurroundingPK pk) {
		this.pk = pk;
	}

	public void setPk(BuildingPermitSurroundingPK pk) {
		this.pk = pk;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public String getSandhiyar() {
		return sandhiyar;
	}

	public void setSandhiyar(String sandhiyar) {
		this.sandhiyar = sandhiyar;
	}

	public String getFeet() {
		return feet;
	}

	public void setFeet(String feet) {
		this.feet = feet;
	}

	public String getKittaNo() {
		return kittaNo;
	}

	public void setKittaNo(String kittaNo) {
		this.kittaNo = kittaNo;
	}

	public String getSideUnit() {
		return sideUnit;
	}

	public void setSideUnit(String sideUnit) {
		this.sideUnit = sideUnit;
	}

	public String getLandScapeType() {
		return landScapeType;
	}

	public void setLandScapeType(String landScapeType) {
		this.landScapeType = landScapeType;
	}

	public String getLandScapeName() {
		return landScapeName;
	}

	public void setLandScapeName(String landScapeName) {
		this.landScapeName = landScapeName;
	}

	public String getActualSetback() {
		return actualSetback;
	}

	public void setActualSetback(String actualSetback) {
		this.actualSetback = actualSetback;
	}

	public String getRoadWidth() {
		return roadWidth;
	}

	public void setRoadWidth(String roadWidth) {
		this.roadWidth = roadWidth;
	}

	public String getRoadStandardSetback() {
		return roadStandardSetback;
	}

	public void setRoadStandardSetback(String roadStandardSetback) {
		this.roadStandardSetback = roadStandardSetback;
	}

	public String getDistanceFromHighTensionLine() {
		return distanceFromHighTensionLine;
	}

	public void setDistanceFromHighTensionLine(String distanceFromHighTensionLine) {
		this.distanceFromHighTensionLine = distanceFromHighTensionLine;
	}

	public String getDistanceFromRiver() {
		return distanceFromRiver;
	}

	public void setDistanceFromRiver(String distanceFromRiver) {
		this.distanceFromRiver = distanceFromRiver;
	}

	public String getDoorOrWindows() {
		return doorOrWindows;
	}

	public void setDoorOrWindows(String doorOrWindows) {
		this.doorOrWindows = doorOrWindows;
	}

	public String getDistanceFromBorder() {
		return distanceFromBorder;
	}

	public void setDistanceFromBorder(String distanceFromBorder) {
		this.distanceFromBorder = distanceFromBorder;
	}

	@Override
	public String toString() {
		return "\n{\"side\":\"" + side + "\",\"sandhiyar\":\"" + sandhiyar + "\",\"feet\":\"" + feet
				+ "\",\"kittaNo\":\"" + kittaNo + "\",\"sideUnit\":\"" + sideUnit + "\"}";
	}

}
