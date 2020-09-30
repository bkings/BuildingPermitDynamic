package com.model.application;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import model.ApplicationForm;
import model.DateConvert;

@Entity
@Table(name = "building_permit_application_name_transafer", uniqueConstraints = @UniqueConstraint(columnNames = {"APPLICATION_NO", "TRANSAFER_TIME"}, name = "UNIQUE_APPLICANT_SN"))
public class BuildingPermitApplicationNameTransafer implements java.io.Serializable {

    @Id
    @Column(name = "ID")
    private long id;
    @Column(name = "application_no")
    private long applicationNo;
    @Column(name = "TRANSAFER_TIME")
    private Integer transaferTime;
    @JoinColumn(name = "APPLICATION_NO", referencedColumnName = "ID", insertable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BuildingPermitApplication buildingPermitApplication;

    @Column(name = "APPLICANT_DATE", columnDefinition = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicantDate;

    @Column(name = "APPLICANT_MS")
    private String applicantMs;
    @Column(name = "APPLICANT_NAME", columnDefinition = "VARCHAR(1000)")
    private String applicantName;
    @Column(name = "APPLICANT_ADDRESS")
    private String applicantAddress;
    @Column(name = "APPLICANT_MOBILE_NO")
    private String applicantMobileNo;
    @Column(name = "CITIZENSHIP_NO")
    private String citizenshipNo;
    @Column(name = "NATIONAL_ID_NO")
    private String nationalIdNo;
    @Column(name = "FATHERS_SPOUSE_NAME")
    private String fathersSpouseName;
    @Column(name = "OLD_MUNICIPAL")
    private String oldMunicipal;
    @Column(name = "OLD_WARD_NO")
    private String oldWardNo;
    @Column(name = "NEW_MUNICIPAL")
    private String newMunicipal;
    @Column(name = "NEW_WARD_NO")
    private String newWardNo;
    @Column(name = "KITTA_NO")
    private String kittaNo;
    @Column(name = "LAND_AREA")
    private String landArea;
    @Column(name = "LAND_AREA_TYPE")
    private String landAreaType;
    @Column(name = "NEAREST_LOCATION")
    private String nearestLocation;
    @Column(name = "BUILDING_JOIN_ROAD")
    private String buildingJoinRoad;
    @Column(name = "BUILDING_JOIN_ROAD_TYPE")
    private String buildingJoinRoadType;
    @Column(name = "BUILDING_JOIN_ROAD_TYPE_OTHER")
    private String buildingJoinRoadTypeOther;
    @Column(name = "LAND_PASS_DATE")
    private String landPassDate;
    @Column(name = "LAND_REG_NO")
    private String landRegNo;
    @Column(name = "OWNERSHIP_NAME")
    private String ownershipName;
    @Column(name = "CERTIFICATE_AREA")
    private String certificateArea;
    @Column(name = "LAND_CERTIFICATE_NO")
    private String landCertificateNo;
    @Column(name = "LAND_CERTIFICATE_ISSUE_DATE")
    private String landCertificateIssueDate;
    @Column(name = "PURPOSE_OF_CONSTRUCTION")
    private String purposeOfConstruction;
    @Column(name = "PURPOSE_OF_CONSTRUCTION_OTHER")
    private String purposeOfConstructionOther;
    @Column(name = "CONSTRUCTION_TYPE")
    private String constructionType;
    @Column(name = "CONSTRUCTION_TYPE_OTHER")
    private String constructionTypeOther;
    @Column(name = "OLD_MAP_DATE")
    private String oldMapDate;
    @Column(name = "MOHADA")
    private String mohada;
    @Column(name = "CONSTRUCTION_FINISHING")
    private String constructionFinishing;
    @Column(name = "CONSTRUCTION_FINISHING_OTHER")
    private String constructionFinishingOther;
    @Column(name = "DHAL_NIKAS_ARRANGEMENT")
    private String dhalNikasArrangement;
    @Column(name = "DHAL_NIKAS_ARRANGEMENT_OTHER")
    private String dhalNikasArrangementOther;
    @Column(name = "FOHAR_ARRANGEMENT")
    private String foharArrangement;
    @Column(name = "FOHAR_ARRANGEMENT_OTHER")
    private String foharArrangementOther;
    @Column(name = "PIPELINE")
    private String pipeline;
    @Column(name = "PIPELINE_DISTANCE")
    private String pipelineDistance;
    @Column(name = "DO_PIPELINE_CONNECTION")
    private String doPipelineConnection;
    @Column(name = "IS_HIGH_TENSION_LINE")
    private String isHighTensionLine;
    @Column(name = "IS_HIGH_TENSION_LINE_DISTANCE")
    private String isHighTensionLineDistance;
    @Column(name = "sadak", columnDefinition = "VARCHAR(1000) DEFAULT  null")
    private String sadak;
    @Column(name = "tol", columnDefinition = "VARCHAR(1000) DEFAULT NULL")
    private String tol;
    @Column(name = "dhal_unit", columnDefinition = "VARCHAR(1000) DEFAULT NULL")
    private String dhalUnit;
    @Column(name = "pipeline_unit", columnDefinition = "VARCHAR(1000) DEFAULT NULL")
    private String pipelineUnit;
    @Column(name = "high_tension_line_unit", columnDefinition = "VARCHAR(1000) DEFAULT NULL")
    private String highTensionLineUnit;
    @Column(name = "nibedak_name")
    private String nibedakName;
    @Column(name = "nibedak_additional")
    private String nibedakAdditional;
    @Column(name = "nibedak_sadak")
    private String nibedakSadak;
    @Column(name = "nibedak_tol")
    private String nibedakTol;

    @Column(name = "ENTER_BY")
    private String enterBy;
    @Column(name = "enter_date", updatable = true)
    private String enterDate;

    @Column(name = "SER_NAME")
    private String serName;
    @Column(name = "SER_DATE")
    @Temporal(TemporalType.DATE)
    private java.util.Date serDate;
    @Column(name = "SER_STATUS", columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
    private String serStatus;

    @Column(name = "ER_NAME")
    private String erName;
    @Column(name = "ER_DATE")
    @Temporal(TemporalType.DATE)
    private java.util.Date erDate;
    @Column(name = "ER_STATUS", columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
    private String erStatus;

    @Column(name = "DESIGNER_STATUS", columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
    private String designerStatus;
    @Column(name = "AMIN_STATUS", columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
    private String aminStatus;
    @Column(name = "RAJASOW_STATUS", columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
    private String rajasowStatus;
    @Column(name = "SUB_ENGINEER_STATUS", columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
    private String subEngineerStatus;
    @Column(name = "ENGINEER_STATUS", columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
    private String engineerStatus;
    @Column(name = "CHIEF_STATUS", columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
    private String chiefStatus;

    @Column(name = "DESIGNER_ACTION", columnDefinition = "VARCHAR(1000) DEFAULT '1'")
    private String designerAction;
    @Column(name = "AMIN_ACTION", columnDefinition = "VARCHAR(1000) DEFAULT '1'")
    private String aminAction;
    @Column(name = "RAJASOW_ACTION", columnDefinition = "VARCHAR(1000) DEFAULT '1'")
    private String rajasowAction;
    @Column(name = "SUB_ENGINEER_ACTION", columnDefinition = "VARCHAR(1000) DEFAULT '1'")
    private String subEngineerAction;
    @Column(name = "ENGINEER_ACTION", columnDefinition = "VARCHAR(1000) DEFAULT '1'")
    private String engineerAction;
    @Column(name = "CHIEF_ACTION", columnDefinition = "VARCHAR(1000) DEFAULT '1'")
    private String chiefAction;
    @Column(name = "amini_name")
    private String aminiName;
    @Column(name = "amini_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date aminiDate;
    @Column(name = "AMINI_STATUS", columnDefinition = "VARCHAR(1) DEFAULT 'P'")
    private String aminiStatus;
    @Column(name = "RW_NAME")
    private String rwName;
    @Column(name = "RW_DATE")
    @Temporal(TemporalType.DATE)
    private java.util.Date rwDate;
    @Column(name = "RW_STATUS", columnDefinition = "VARCHAR(1) DEFAULT 'P'")
    private String rwStatus;

    @Column(name = "is_low_tension_line", columnDefinition = "VARCHAR(30)")
    private String isLowTensionLine;
    @Column(name = "old_application_no")
    private Long oldApplicationNo;
    @Column(name = "photo")
    private String photo;

    @Column(name = "TALA_THAP_ASSIGN", columnDefinition = "VARCHAR(1)")
    private String talaThapAssign;
    @Column(name = "tala_thap_document", columnDefinition = "VARCHAR(255)")
    private String talathapDocument;
    @Column(name = "TALA_THAP_ASSIGN_BY", columnDefinition = "VARCHAR(19)")
    private String talaThapAssignBy;
    @Column(name = "TALA_THAP_ASSIGN_DATE", columnDefinition = "VARCHAR(50)")
    private String talaThapAssignDate;
    @Column(name = "talathap_application_no")
    private Long talathapApplicationNo;

    @Column(name = "TRANSAFER_ENTER_BY", columnDefinition = "VARCHAR(30)")
    private String transaferEnterBy;
    @Column(name = "TRANSAFER_ENTER_DATE")
    @Temporal(TemporalType.DATE)
    private Date transaferEnterDate;
    @Column(name = "TRANSAFER_APPROVE_BY")
    private String transaferApproveBy;
    @Column(name = "TRANSAFER_APPROVE_DATE")
    @Temporal(TemporalType.DATE)
    private Date transaferApproveDate;

    public Long getTalathapApplicationNo() {
        return talathapApplicationNo;
    }

    public void setTalathapApplicationNo(Long talathapApplicationNo) {
        this.talathapApplicationNo = talathapApplicationNo;
    }

    public String getAminiName() {
        return aminiName;
    }

    public void setAminiName(String aminiName) {
        this.aminiName = aminiName;
    }

    public Date getAminiDate() {
        return aminiDate;
    }

    public void setAminiDate(Date aminiDate) {
        this.aminiDate = aminiDate;
    }

    public String getAminiStatus() {
        return aminiStatus;
    }

    public void setAminiStatus(String aminiStatus) {
        this.aminiStatus = aminiStatus;
    }

    public String getRwName() {
        return rwName;
    }

    public void setRwName(String rwName) {
        this.rwName = rwName;
    }

    public Date getRwDate() {
        return rwDate;
    }

    public void setRwDate(Date rwDate) {
        this.rwDate = rwDate;
    }

    public String getRwStatus() {
        return rwStatus;
    }

    public void setRwStatus(String rwStatus) {
        this.rwStatus = rwStatus;
    }

    public String getNibedakSadak() {
        return nibedakSadak;
    }

    public void setNibedakSadak(String nibedakSadak) {
        this.nibedakSadak = nibedakSadak;
    }

    public String getNibedakTol() {
        return nibedakTol;
    }

    public void setNibedakTol(String nibedakTol) {
        this.nibedakTol = nibedakTol;
    }

    public String getDhalUnit() {
        return dhalUnit;
    }

    public void setDhalUnit(String dhalUnit) {
        this.dhalUnit = dhalUnit;
    }

    public String getPipelineUnit() {
        return pipelineUnit;
    }

    public void setPipelineUnit(String pipelineUnit) {
        this.pipelineUnit = pipelineUnit;
    }

    public String getHighTensionLineUnit() {
        return highTensionLineUnit;
    }

    public void setHighTensionLineUnit(String highTensionLineUnit) {
        this.highTensionLineUnit = highTensionLineUnit;
    }

    public String getNibedakName() {
        return nibedakName;
    }

    public void setNibedakName(String nibedakName) {
        this.nibedakName = nibedakName;
    }

    public String getNibedakAdditional() {
        return nibedakAdditional;
    }

    public void setNibedakAdditional(String nibedakAdditional) {
        this.nibedakAdditional = nibedakAdditional;
    }

    public BuildingPermitApplicationNameTransafer() {
    }

    public BuildingPermitApplicationNameTransafer(Long id) {
        this.id = id;
    }

    public BuildingPermitApplicationNameTransafer(String id) {
        this.id = Long.parseLong(id);
    }

    public String getApplicantMobileNo() {
        return applicantMobileNo;
    }

    public void setApplicantMobileNo(String applicantMobileNo) {
        this.applicantMobileNo = applicantMobileNo;
    }

    public String getApplicantMs() {
        return applicantMs;
    }

    public void setApplicantMs(String applicantMs) {
        this.applicantMs = applicantMs;
    }

    public Long getApplicantNo() {
        return id;
    }

    public long getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantAddress() {
        return applicantAddress;
    }

    public void setApplicantAddress(String applicantAddress) {
        this.applicantAddress = applicantAddress;
    }

    public String getCitizenshipNo() {
        return citizenshipNo;
    }

    public void setCitizenshipNo(String citizenshipNo) {
        this.citizenshipNo = citizenshipNo;
    }

    public String getNationalIdNo() {
        return nationalIdNo;
    }

    public void setNationalIdNo(String nationalIdNo) {
        this.nationalIdNo = nationalIdNo;
    }

    public String getFathersSpouseName() {
        return fathersSpouseName;
    }

    public void setFathersSpouseName(String fathersSpouseName) {
        this.fathersSpouseName = fathersSpouseName;
    }

    public String getOldMunicipal() {
        return oldMunicipal;
    }

    public void setOldMunicipal(String oldMunicipal) {
        this.oldMunicipal = oldMunicipal;
    }

    public String getOldWardNo() {
        return oldWardNo;
    }

    public void setOldWardNo(String oldWardNo) {
        this.oldWardNo = oldWardNo;
    }

    public String getNewMunicipal() {
        return newMunicipal;
    }

    public void setNewMunicipal(String newMunicipal) {
        this.newMunicipal = newMunicipal;
    }

    public String getNewWardNo() {
        return newWardNo;
    }

    public void setNewWardNo(String newWardNo) {
        this.newWardNo = newWardNo;
    }

    public String getKittaNo() {
        return kittaNo;
    }

    public void setKittaNo(String kittaNo) {
        this.kittaNo = kittaNo;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public String getNearestLocation() {
        return nearestLocation;
    }

    public void setNearestLocation(String nearestLocation) {
        this.nearestLocation = nearestLocation;
    }

    public String getBuildingJoinRoad() {
        return buildingJoinRoad;
    }

    public void setBuildingJoinRoad(String buildingJoinRoad) {
        this.buildingJoinRoad = buildingJoinRoad;
    }

    public String getBuildingJoinRoadType() {
        return buildingJoinRoadType;
    }

    public void setBuildingJoinRoadType(String buildingJoinRoadType) {
        this.buildingJoinRoadType = buildingJoinRoadType;
    }

    public String getLandPassDate() {
        return landPassDate;
    }

    public void setLandPassDate(String landPassDate) {
        this.landPassDate = landPassDate;
    }

    public String getLandRegNo() {
        return landRegNo;
    }

    public void setLandRegNo(String landRegNo) {
        this.landRegNo = landRegNo;
    }

    public String getOwnershipName() {
        return ownershipName;
    }

    public void setOwnershipName(String ownershipName) {
        this.ownershipName = ownershipName;
    }

    public String getCertificateArea() {
        return certificateArea;
    }

    public void setCertificateArea(String certificateArea) {
        this.certificateArea = certificateArea;
    }

    public String getLandCertificateNo() {
        return landCertificateNo;
    }

    public void setLandCertificateNo(String landCertificateNo) {
        this.landCertificateNo = landCertificateNo;
    }

    public String getLandCertificateIssueDate() {
        return landCertificateIssueDate;
    }

    public void setLandCertificateIssueDate(String landCertificateIssueDate) {
        this.landCertificateIssueDate = landCertificateIssueDate;
    }

    public String getPurposeOfConstruction() {
        return purposeOfConstruction;
    }

    public void setPurposeOfConstruction(String purposeOfConstruction) {
        this.purposeOfConstruction = purposeOfConstruction;
    }

    public String getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(String constructionType) {
        this.constructionType = constructionType;
    }

    public String getOldMapDate() {
        return oldMapDate;
    }

    public void setOldMapDate(String oldMapDate) {
        this.oldMapDate = oldMapDate;
    }

    public String getMohada() {
        return mohada;
    }

    public void setMohada(String mohada) {
        this.mohada = mohada;
    }

    public String getConstructionFinishing() {
        return constructionFinishing;
    }

    public void setConstructionFinishing(String constructionFinishing) {
        this.constructionFinishing = constructionFinishing;
    }

    public String getDhalNikasArrangement() {
        return dhalNikasArrangement;
    }

    public void setDhalNikasArrangement(String dhalNikasArrangement) {
        this.dhalNikasArrangement = dhalNikasArrangement;
    }

    public String getFoharArrangement() {
        return foharArrangement;
    }

    public void setFoharArrangement(String foharArrangement) {
        this.foharArrangement = foharArrangement;
    }

    public String getPipeline() {
        return pipeline;
    }

    public void setPipeline(String pipeline) {
        this.pipeline = pipeline;
    }

    public String getPipelineDistance() {
        return pipelineDistance;
    }

    public void setPipelineDistance(String pipelineDistance) {
        this.pipelineDistance = pipelineDistance;
    }

    public String getDoPipelineConnection() {
        return doPipelineConnection;
    }

    public void setDoPipelineConnection(String doPipelineConnection) {
        this.doPipelineConnection = doPipelineConnection;
    }

    public String getIsHighTensionLine() {
        return isHighTensionLine;
    }

    public void setIsHighTensionLine(String isHighTensionLine) {
        this.isHighTensionLine = isHighTensionLine;
    }

    public String getIsHighTensionLineDistance() {
        return isHighTensionLineDistance;
    }

    public void setIsHighTensionLineDistance(String isHighTensionLineDistance) {
        this.isHighTensionLineDistance = isHighTensionLineDistance;
    }

    public String getApplicantDate() {
        return DateConvert.toStringDateTime(applicantDate);
    }

    public String getApplicantDateBS() {
        return DateConvert.adtobsDate(applicantDate);
    }

    public String getApplicantTime() {
        return DateConvert.getTime(applicantDate);
    }

    public void setApplicantDate(Date applicantDate) {

        this.applicantDate = applicantDate;
    }

    public void setApplicantDate(String applicantDate) {
        this.applicantDate = new Date();
    }

    public String getEnterBy() {
        return enterBy;
    }

    public void setEnterBy(String enterBy) {
        this.enterBy = enterBy;
    }

    public String getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(String enterDate) {
        this.enterDate = enterDate;
    }

    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }

    public Date getSerDate() {
        return serDate;
    }

    public void setSerDate(Date serDate) {
        this.serDate = serDate;
    }

    public String getSerStatus() {
        return serStatus;
    }

    public void setSerStatus(String serStatus) {
        this.serStatus = serStatus;
    }

    public String getErName() {
        return erName;
    }

    public void setErName(String erName) {
        this.erName = erName;
    }

    public Date getErDate() {
        return erDate;
    }

    public void setErDate(Date erDate) {
        this.erDate = erDate;
    }

    public String getErStatus() {
        return erStatus;
    }

    public void setErStatus(String erStatus) {
        this.erStatus = erStatus;
    }

    public String getBuildingJoinRoadTypeOther() {
        return buildingJoinRoadTypeOther;
    }

    public void setBuildingJoinRoadTypeOther(String buildingJoinRoadTypeOther) {
        this.buildingJoinRoadTypeOther = buildingJoinRoadTypeOther;
    }

    public String getPurposeOfConstructionOther() {
        return purposeOfConstructionOther;
    }

    public void setPurposeOfConstructionOther(String purposeOfConstructionOther) {
        this.purposeOfConstructionOther = purposeOfConstructionOther;
    }

    public String getConstructionTypeOther() {
        return constructionTypeOther;
    }

    public void setConstructionTypeOther(String constructionTypeOther) {
        this.constructionTypeOther = constructionTypeOther;
    }

    public String getConstructionFinishingOther() {
        return constructionFinishingOther;
    }

    public void setConstructionFinishingOther(String constructionFinishingOther) {
        this.constructionFinishingOther = constructionFinishingOther;
    }

    public String getDhalNikasArrangementOther() {
        return dhalNikasArrangementOther;
    }

    public void setDhalNikasArrangementOther(String dhalNikasArrangementOther) {
        this.dhalNikasArrangementOther = dhalNikasArrangementOther;
    }

    public String getFoharArrangementOther() {
        return foharArrangementOther;
    }

    public void setFoharArrangementOther(String foharArrangementOther) {
        this.foharArrangementOther = foharArrangementOther;
    }

    public String getDesignerStatus() {
        return designerStatus;
    }

    public void setDesignerStatus(String designerStatus) {
        this.designerStatus = designerStatus;
    }

    public String getAminStatus() {
        return aminStatus;
    }

    public void setAminStatus(String aminStatus) {
        this.aminStatus = aminStatus;
    }

    public String getRajasowStatus() {
        return rajasowStatus;
    }

    public void setRajasowStatus(String rajasowStatus) {
        this.rajasowStatus = rajasowStatus;
    }

    public String getSubEngineerStatus() {
        return subEngineerStatus;
    }

    public void setSubEngineerStatus(String subEngineerStatus) {
        this.subEngineerStatus = subEngineerStatus;
    }

    public String getEngineerStatus() {
        return engineerStatus;
    }

    public void setEngineerStatus(String engineerStatus) {
        this.engineerStatus = engineerStatus;
    }

    public String getChiefStatus() {
        return chiefStatus;
    }

    public void setChiefStatus(String chiefStatus) {
        this.chiefStatus = chiefStatus;
    }

    public Object getDesignerAction() {
        return ApplicationForm.getById(designerAction);
    }

    public void setDesignerAction(String designerAction) {
        this.designerAction = designerAction;
    }

    public Object getAminAction() {
        return ApplicationForm.getById(aminAction);
    }

    public void setAminAction(String aminAction) {
        this.aminAction = aminAction;
    }

    public Object getRajasowAction() {
        return ApplicationForm.getById(rajasowAction);
    }

    public void setRajasowAction(String rajasowAction) {
        this.rajasowAction = rajasowAction;
    }

    public Object getSubEngineerAction() {
        return ApplicationForm.getById(subEngineerAction);
    }

    public void setSubEngineerAction(String subEngineerAction) {
        this.subEngineerAction = subEngineerAction;
    }

    public Object getChiefAction() {
        return ApplicationForm.getById(chiefAction);
    }

    public void setChiefAction(String chiefAction) {
        this.chiefAction = chiefAction;
    }

    public String getChiefActionNo() {
        return chiefAction;
    }

    public Object getEngineerAction() {
        return ApplicationForm.getById(engineerAction);
    }

    public void setEngineerAction(String engineerAction) {
        this.engineerAction = engineerAction;
    }

    public String getDesignerActionNo() {
        return designerAction;
    }

    public String getLandAreaType() {
        return landAreaType;
    }

    public void setLandAreaType(String landAreaType) {
        this.landAreaType = landAreaType;
    }

    public String getSadak() {
        return sadak;
    }

    public void setSadak(String sadak) {
        this.sadak = sadak;
    }

    public String getTol() {
        return tol;
    }

    public void setTol(String tol) {
        this.tol = tol;
    }
  
    

    public String getIsLowTensionLine() {
        return isLowTensionLine;
    }

    public void setIsLowTensionLine(String isLowTensionLine) {
        this.isLowTensionLine = isLowTensionLine;
    }

    public Long getOldApplicationNo() {
        return oldApplicationNo;
    }

    public void setOldApplicationNo(Long oldApplicationNo) {
        this.oldApplicationNo = oldApplicationNo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTalaThapAssign() {
        return talaThapAssign;
    }

    public void setTalaThapAssign(String talaThapAssign) {
        this.talaThapAssign = talaThapAssign;
    }

    public String getTalathapDocument() {
        return talathapDocument;
    }

    public void setTalathapDocument(String talathapDocument) {
        this.talathapDocument = talathapDocument;
    }

    public String getTalaThapAssignBy() {
        return talaThapAssignBy;
    }

    public void setTalaThapAssignBy(String talaThapAssignBy) {
        this.talaThapAssignBy = talaThapAssignBy;
    }

    public String getTalaThapAssignDate() {
        return talaThapAssignDate;
    }

    public void setTalaThapAssignDate(String talaThapAssignDate) {
        this.talaThapAssignDate = talaThapAssignDate;
    }

    public String getTransaferEnterBy() {
        return transaferEnterBy;
    }

    public void setTransaferEnterBy(String transaferEnterBy) {
        this.transaferEnterBy = transaferEnterBy;
    }

    public String getTransaferApproveBy() {
        return transaferApproveBy;
    }

    public void setTransaferApproveBy(String transaferApproveBy) {
        this.transaferApproveBy = transaferApproveBy;
    }

    public Date getTransaferEnterDate() {
        return transaferEnterDate;
    }

    public void setTransaferEnterDate(Date transaferEnterDate) {
        this.transaferEnterDate = transaferEnterDate;
    }

    public Date getTransaferApproveDate() {
        return transaferApproveDate;
    }

    public void setTransaferApproveDate(Date transaferApproveDate) {
        this.transaferApproveDate = transaferApproveDate;
    }

    public Integer getTransaferTime() {
        return transaferTime;
    }

    public void setTransaferTime(Integer transaferTime) {
        this.transaferTime = transaferTime;
    }

}
