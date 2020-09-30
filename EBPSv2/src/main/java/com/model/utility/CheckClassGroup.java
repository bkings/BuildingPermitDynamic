/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "check_class_group")
public class CheckClassGroup {
    @Id
    @Column(name = "id")
    private Long id;
    
    @Column(name = "building_class")
    private String buildingClass;
    
    @Column(name = "construction_type")
    private String ConstructionType;
    
    @Column(name = "ignored_form")
    private String ignoredForm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuildingClass() {
        return buildingClass;
    }

    public void setBuildingClass(String buildingClass) {
        this.buildingClass = buildingClass;
    }

    public String getConstructionType() {
        return ConstructionType;
    }

    public void setConstructionType(String ConstructionType) {
        this.ConstructionType = ConstructionType;
    }

    public String getIgnoredForm() {
        return ignoredForm;
    }

    public void setIgnoredForm(String ignoredForm) {
        this.ignoredForm = ignoredForm;
    }

    @Override
    public String toString() {
        return "CheckGroupClassType{" + "id=" + id + ", buildingClass=" + buildingClass + ", ConstructionType=" + ConstructionType + ", ignoredForm=" + ignoredForm + '}';
    }

   
   
}
