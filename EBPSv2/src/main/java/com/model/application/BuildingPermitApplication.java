package com.model.application;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.model.processing.RajaswaEntry;

import model.ApplicationForm;
import model.DateConvert;
import model.Message;

@Entity
@Table(name = "building_permit_application", uniqueConstraints = @UniqueConstraint(columnNames = { "APPLICANT_YEAR",
		"APPLICANT_SN" }, name = "UNIQUE_APPLICANT_SN"))
public class BuildingPermitApplication implements java.io.Serializable {

	@Id
	@Column(name = "ID")
	private long id;
	@Column(name = "application_no", updatable = false)
	private long applicationNo;
	@Column(name = "REG_NO")
	private String regNo;
	@Column(name = "APPLICANT_YEAR", updatable = false)
	private int applicantYear;
	@Column(name = "APPLICANT_SN", updatable = false)
	private int applicantSn;
	@Column(name = "APPLICANT_DATE", updatable = false, columnDefinition = "timestamp")
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

	@Column(name = "REMOTE_ADDR_DATE_TIME", updatable = false, unique = true, columnDefinition = "VARCHAR(500)")
	private String remoteAddrDateTime;
	@Column(name = "ENTER_BY", updatable = false)
	private String enterBy;
	@Column(name = "enter_date", updatable = true)
	private String enterDate;
	
	/*@Column(name = "amini_name")
	private String aminiName;
	@Column(name = "amini_date")
	@Temporal(TemporalType.DATE)
	private java.util.Date aminiDate;
	@Column(name = "amini_status", updatable = false, columnDefinition = "VARCHAR(1) DEFAULT 'P'")
	private String aminiStatus;

	@Column(name = "RW_NAME", updatable = false)
	private String rwName;
	@Column(name = "RW_DATE", updatable = false)
	@Temporal(TemporalType.DATE)
	private java.util.Date rwDate;
	@Column(name = "RW_STATUS", updatable = false, columnDefinition = "VARCHAR(1) DEFAULT 'P'")
	private String rwStatus;

	@Column(name = "SER_NAME", updatable = false)
	private String serName;
	@Column(name = "SER_DATE", updatable = false)
	@Temporal(TemporalType.DATE)
	private java.util.Date serDate;
	@Column(name = "SER_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String serStatus;

	@Column(name = "ER_NAME", updatable = false)
	private String erName;
	@Column(name = "ER_DATE", updatable = false)
	@Temporal(TemporalType.DATE)
	private java.util.Date erDate;
	@Column(name = "ER_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String erStatus;

	@Column(name = "E_NAME", updatable = false)
	private String eName;
	@Column(name = "E_DATE", updatable = false)
	@Temporal(TemporalType.DATE)
	private java.util.Date eDate;
	@Column(name = "E_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String eStatus;

	@Column(name = "F_NAME", updatable = false)
	private String fName;
	@Column(name = "F_DATE", updatable = false)
	@Temporal(TemporalType.DATE)
	private java.util.Date fDate;
	@Column(name = "F_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String fStatus;

	@Column(name = "G_NAME", updatable = false)
	private String gName;
	@Column(name = "G_DATE", updatable = false)
	@Temporal(TemporalType.DATE)
	private java.util.Date gDate;
	@Column(name = "G_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String gStatus;

	@Column(name = "CHIEF_NAME", updatable = false)
	private String chiefName;
	@Column(name = "CHIEF_DATE", updatable = false)
	@Temporal(TemporalType.DATE)
	private java.util.Date chiefDate;

	@Column(name = "DESIGNER_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String designerStatus;
	@Column(name = "AMIN_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String aminStatus;
	@Column(name = "RAJASOW_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String rajasowStatus;
	@Column(name = "SUB_ENGINEER_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String subEngineerStatus;
	@Column(name = "ENGINEER_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String engineerStatus;
	@Column(name = "CHIEF_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String chiefStatus;
	@Column(name = "POSTE_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String posteStatus;
	@Column(name = "POSTF_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String postfStatus;
	@Column(name = "POSTG_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String postgStatus;

	@Column(name = "DESIGNER_ACTION", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT '1'")
	private String designerAction;
	@Column(name = "AMIN_ACTION", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT '1'")
	private String aminAction;
	@Column(name = "RAJASOW_ACTION", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT '1'")
	private String rajasowAction;
	@Column(name = "SUB_ENGINEER_ACTION", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT '1'")
	private String subEngineerAction;
	@Column(name = "ENGINEER_ACTION", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT '1'")
	private String engineerAction;
	@Column(name = "CHIEF_ACTION", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT '1'")
	private String chiefAction;
	@Column(name = "POSTE_ACTION", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String posteAction;
	@Column(name = "POSTF_ACTION", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String postfAction;
	@Column(name = "POSTG_ACTION", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT 'P'")
	private String postgAction;*/

	@Column(name = "APPLICATION_STATUS", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT  '1'")
	private String applicationStatus;
	@Column(name = "APPLICATION_ACTION_BY", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT  'D'")
	private String applicationActionBy;
	@Column(name = "APPLICATION_ACTION", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT  'P'")
	private String applicationAction;

	/*@Column(name = "forward_to_d", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT  'Y'")
	private String forwardToD;
	@Column(name = "forward_to_b", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT  'Y'")
	private String forwardToB;
	@Column(name = "forward_to_a", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT  'Y'")
	private String forwardToA;
	@Column(name = "forward_to_c", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT  'Y'")
	private String forwardToC;
	@Column(name = "forward_to_ad", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT  'Y'")
	private String forwardToAd;
	@Column(name = "forward_to_r", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT  'Y'")
	private String forwardToR;
	@Column(name = "forward_to_e", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT  'Y'")
	private String forwardToE;
	@Column(name = "forward_to_f", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT  'Y'")
	private String forwardTof;
	@Column(name = "forward_to_g", updatable = false, columnDefinition = "VARCHAR(1000) DEFAULT  'Y'")
	private String forwardTog;

	@Column(name = "forward_to_d_remark", updatable = false, columnDefinition = "VARCHAR")
	private String forwardToDRemark;
	@Column(name = "forward_to_b_remark", updatable = false, columnDefinition = "VARCHAR")
	private String forwardToBRemark;
	@Column(name = "forward_to_a_remark", updatable = false, columnDefinition = "VARCHAR")
	private String forwardToARemark;
	@Column(name = "forward_to_c_remark", updatable = false, columnDefinition = "VARCHAR")
	private String forwardToCRemark;
	@Column(name = "forward_to_ad_remark", updatable = false, columnDefinition = "VARCHAR")
	private String forwardToAdRemark;
	@Column(name = "forward_to_r_remark", updatable = false, columnDefinition = "VARCHAR")
	private String forwardToRRemark;
	@Column(name = "forward_to_e_remark", updatable = false, columnDefinition = "VARCHAR")
	private String forwardToERemark;
	@Column(name = "forward_to_f_remark", updatable = false, columnDefinition = "VARCHAR")
	private String forwardToFRemark;
	@Column(name = "forward_to_g_remark", updatable = false, columnDefinition = "VARCHAR")
	private String forwardToGRemark;

	@Column(name = "forward_to_a_Date", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToADate;
	@Column(name = "forward_to_d_Date", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToDDate;
	@Column(name = "forward_to_c_Date", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToCDate;
	@Column(name = "forward_to_b_Date", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToBDate;
	@Column(name = "forward_to_ad_Date", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToAdDate;
	@Column(name = "forward_to_r_Date", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToRDate;
	@Column(name = "forward_to_e_Date", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToEDate;
	@Column(name = "forward_to_f_Date", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToFDate;
	@Column(name = "forward_to_g_Date", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToGDate;

	@Column(name = "forward_to_a_name", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToAName;
	@Column(name = "forward_to_b_name", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToBName;
	@Column(name = "forward_to_c_name", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToCName;
	@Column(name = "forward_to_d_name", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToDName;
	@Column(name = "forward_to_ad_name", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToAdName;
	@Column(name = "forward_to_r_name", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToRName;
	@Column(name = "forward_to_e_name", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToEName;
	@Column(name = "forward_to_f_name", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToFName;
	@Column(name = "forward_to_g_name", updatable = false, columnDefinition = "VARCHAR(30)")
	private String forwardToGName;*/
	
	@Column(name = "notice15_till_date", updatable = false)
	@Temporal(TemporalType.DATE)
	private java.util.Date notice15TillDate;

	@Column(name = "is_low_tension_line", updatable = false, columnDefinition = "VARCHAR(30)")
	private String isLowTensionLine;

	@Column(name = "old_application_no", updatable = false)
	private Long oldApplicationNo;

	/*@Column(name = "rejected_a", updatable = false, columnDefinition = "VARCHAR(1)")
	private String rejectedA;
	@Column(name = "rejected_b", updatable = false, columnDefinition = "VARCHAR(1)")
	private String rejectedB;
	@Column(name = "rejected_c", updatable = false, columnDefinition = "VARCHAR(1)")
	private String rejectedC;
	@Column(name = "rejected_d", updatable = false, columnDefinition = "VARCHAR(1)")
	private String rejectedD;
	@Column(name = "rejected_da", updatable = false, columnDefinition = "VARCHAR(1)")
	private String rejectedDa;
	@Column(name = "rejected_r", updatable = false, columnDefinition = "VARCHAR(1)")
	private String rejectedR;
	@Column(name = "rejected_e", updatable = false, columnDefinition = "VARCHAR(1)")
	private String rejectedE;
	@Column(name = "rejected_f", updatable = false, columnDefinition = "VARCHAR(1)")
	private String rejectedF;
	@Column(name = "rejected_g", updatable = false, columnDefinition = "VARCHAR(1)")
	private String rejectedG;*/

	@Column(name = "photo", updatable = false)
	private String photo;

	@Column(name = "TALA_THAP_ASSIGN", updatable = false, columnDefinition = "VARCHAR(1)")
	private String talaThapAssign;
	@Column(name = "tala_thap_document", updatable = false, columnDefinition = "VARCHAR(255)")
	private String talathapDocument;
	@Column(name = "TALA_THAP_ASSIGN_BY", updatable = false, columnDefinition = "VARCHAR(19)")
	private String talaThapAssignBy;
	@Column(name = "TALA_THAP_ASSIGN_DATE", updatable = false, columnDefinition = "VARCHAR(50)")
	private String talaThapAssignDate;
	@Column(name = "talathap_application_no", updatable = false)
	private Long talathapApplicationNo;
	@Column(name = "name_transafer_id", updatable = false)
	private Long nameTransaferId;
	@Column(name = "IS_DISCARD", updatable = false, columnDefinition = "VARCHAR(1)")
	private String isDiscard;
	@Column(name = "DISCARD_REASON", updatable = false, columnDefinition = "VARCHAR")
	private String discardReason;
	@Column(name = "DISCARD_DATE", updatable = false, columnDefinition = "VARCHAR(10)")
	private String discardDate;
	@Column(name = "DISCARD_FILE", updatable = false)
	private String discardFile;
	@Column(name = "MAYADTHAP_DATE", updatable = false)
	private String mayadthapDate;
	@Column(name = "MAYADTHAP_REQUEST_DATE", updatable = false)
	@Temporal(TemporalType.DATE)
	private java.util.Date mayadthapRequestDate;
	@Column(name = "MAYADTHAP_BY", updatable = false)
	@Temporal(TemporalType.DATE)
	private java.util.Date mayadthapBy;
	@Column(name = "lat")
	private String lat;
	@Column(name = "lng")
	private String lng;
	@Column(name = "SPOUSE_TYPE")
	private String spouseType;
	@Column(name = "NAKSAWALA_NAME")
	private String naksawalaName;
	@Column(name = "NAKSAWALA_ADDRESS")
	private String naksawalaAddress;
	@Column(name = "CITIZENSHIP_ISSUE_DATE")
	private String citizenshipIssueDate;
	@Column(name = "CITIZENSHIP_ISSUE_ZONE")
	private String citizenshipIssueZone;
	@Column(name = "NAAMSARI_STATUS", columnDefinition = "VARCHAR(1) DEFAULT 'N'")
	private String naamsariStatus;

	public String getNaamsariStatus() {
		return naamsariStatus;
	}

	public void setNaamsariStatus(String naamsariStatus) {
		this.naamsariStatus = naamsariStatus;
	}

	public String getCitizenshipIssueDate() {
		return citizenshipIssueDate;
	}

	public void setCitizenshipIssueDate(String citizenshipIssueDate) {
		this.citizenshipIssueDate = citizenshipIssueDate;
	}

	public String getCitizenshipIssueZone() {
		return citizenshipIssueZone;
	}

	public void setCitizenshipIssueZone(String citizenshipIssueZone) {
		this.citizenshipIssueZone = citizenshipIssueZone;
	}

	public String getNaksawalaName() {
		return naksawalaName;
	}

	public void setNaksawalaName(String naksawalaName) {
		this.naksawalaName = naksawalaName;
	}

	public String getNaksawalaAddress() {
		return naksawalaAddress;
	}

	public void setNaksawalaAddress(String naksawalaAddress) {
		this.naksawalaAddress = naksawalaAddress;
	}

	public String getSpouseType() {
		return spouseType;
	}

	public void setSpouseType(String spouseType) {
		this.spouseType = spouseType;
	}

	public Long getTalathapApplicationNo() {
		return talathapApplicationNo;
	}

	public void setTalathapApplicationNo(Long talathapApplicationNo) {
		this.talathapApplicationNo = talathapApplicationNo;
	}

	/*public String getAminiName() {
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
	}*/

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

	public BuildingPermitApplication() {
	}

	public BuildingPermitApplication(Long id) {
		this.id = id;
	}

	public BuildingPermitApplication(String id) {
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

	public int getApplicantYear() {
		return applicantYear;
	}

	public void setApplicantYear(int applicantYear) {
		this.applicantYear = applicantYear;
	}

	public int getApplicantSn() {
		return applicantSn;
	}

	public void setApplicantSn(int applicantSn) {
		this.applicantSn = applicantSn;
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

	public String getRemoteAddrDateTime() {
		return remoteAddrDateTime;
	}

	public void setRemoteAddrDateTime(String remoteAddrDateTime) {
		this.remoteAddrDateTime = remoteAddrDateTime;
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

	/*public String getSerName() {
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
	}*/

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

	/*public String getDesignerStatus() {
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
	}*/

	public String getApplicationStatus() {
		try {
			return Message.status(applicationStatus);
		} catch (Exception e) {
		}
		return null;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getApplicationActionBy() {
		try {
			return new com.UserTypeName().userTypeName(applicationActionBy);
		} catch (Exception e) {
		}
		return null;
	}

	public void setApplicationActionBy(String applicationActionBy) {
		try {
			this.applicationActionBy = applicationActionBy;
		} catch (Exception e) {
		}
	}

	public String getApplicationActionNo() {
		return this.applicationAction;
	}

	public Object getApplicationAction() {
		return ApplicationForm.getById(applicationAction);
	}

	public void setApplicationAction(String applicationAction) {
		this.applicationAction = applicationAction;
	}

	/*public Object getDesignerAction() {
		try {
			return ApplicationForm.getById(designerAction);
		} catch (Exception e) {
		}
		return null;
	}

	public void setDesignerAction(String designerAction) {
		this.designerAction = designerAction;
	}

	public Object getAminAction() {
		try {
			return ApplicationForm.getById(aminAction);
		} catch (Exception e) {
		}
		return null;
	}

	public void setAminAction(String aminAction) {
		this.aminAction = aminAction;
	}

	public Object getRajasowAction() {
		try {
			return ApplicationForm.getById(rajasowAction);
		} catch (Exception e) {
		}
		return null;
	}

	public void setRajasowAction(String rajasowAction) {
		this.rajasowAction = rajasowAction;
	}

	public Object getSubEngineerAction() {
		try {
			return ApplicationForm.getById(subEngineerAction);
		} catch (Exception e) {
		}
		return null;
	}

	public void setSubEngineerAction(String subEngineerAction) {
		this.subEngineerAction = subEngineerAction;
	}

	public Object getChiefAction() {
		try {
			return ApplicationForm.getById(chiefAction);
		} catch (Exception e) {
		}
		return null;
	}

	public void setChiefAction(String chiefAction) {
		this.chiefAction = chiefAction;
	}

	public String getChiefActionNo() {
		return chiefAction;
	}

	public Object getEngineerAction() {
		try {
			return ApplicationForm.getById(engineerAction);
		} catch (Exception e) {
		}
		return null;
	}

	public void setEngineerAction(String engineerAction) {
		this.engineerAction = engineerAction;
	}

	public String getDesignerActionNo() {
		return designerAction;
	}*/

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

	/*public String getForwardToD() {
		return forwardToD;
	}

	public void setForwardToD(String forwardToD) {
		this.forwardToD = forwardToD;
	}

	public String getForwardToB() {
		return forwardToB;
	}

	public void setForwardToB(String forwardToB) {
		this.forwardToB = forwardToB;
	}

	public String getForwardToA() {
		return forwardToA;
	}

	public void setForwardToA(String forwardToA) {
		this.forwardToA = forwardToA;
	}

	public String getForwardToC() {
		return forwardToC;
	}

	public void setForwardToC(String forwardToC) {
		this.forwardToC = forwardToC;
	}

	public String getForwardToAd() {
		return forwardToAd;
	}

	public void setForwardToAd(String forwardToAd) {
		this.forwardToAd = forwardToAd;
	}

	public String getForwardToR() {
		return forwardToR;
	}

	public void setForwardToR(String forwardToR) {
		this.forwardToR = forwardToR;
	}

	public String getForwardToDRemark() {
		return forwardToDRemark;
	}

	public void setForwardToDRemark(String forwardToDRemark) {
		this.forwardToDRemark = forwardToDRemark;
	}

	public String getForwardToBRemark() {
		return forwardToBRemark;
	}

	public void setForwardToBRemark(String forwardToBRemark) {
		this.forwardToBRemark = forwardToBRemark;
	}

	public String getForwardToARemark() {
		return forwardToARemark;
	}

	public void setForwardToARemark(String forwardToARemark) {
		this.forwardToARemark = forwardToARemark;
	}

	public String getForwardToCRemark() {
		return forwardToCRemark;
	}

	public void setForwardToCRemark(String forwardToCRemark) {
		this.forwardToCRemark = forwardToCRemark;
	}

	public String getForwardToAdRemark() {
		return forwardToAdRemark;
	}

	public void setForwardToAdRemark(String forwardToAdRemark) {
		this.forwardToAdRemark = forwardToAdRemark;
	}

	public String getForwardToRRemark() {
		return forwardToRRemark;
	}

	public void setForwardToRRemark(String forwardToRRemark) {
		this.forwardToRRemark = forwardToRRemark;
	}

	public String getForwardToDDate() {
		return forwardToDDate;
	}

	public void setForwardToDDate(String forwardToDDate) {
		this.forwardToDDate = forwardToDDate;
	}

	public String getForwardToBDate() {
		return forwardToBDate;
	}

	public void setForwardToBDate(String forwardToBDate) {
		this.forwardToBDate = forwardToBDate;
	}

	public String getForwardToADate() {
		return forwardToADate;
	}

	public void setForwardToADate(String forwardToADate) {
		this.forwardToADate = forwardToADate;
	}

	public String getForwardToCDate() {
		return forwardToCDate;
	}

	public void setForwardToCDate(String forwardToCDate) {
		this.forwardToCDate = forwardToCDate;
	}

	public String getForwardToAdDate() {
		return forwardToAdDate;
	}

	public void setForwardToAdDate(String forwardToAdDate) {
		this.forwardToAdDate = forwardToAdDate;
	}

	public String getForwardToRDate() {
		return forwardToRDate;
	}

	public void setForwardToRDate(String forwardToRDate) {
		this.forwardToRDate = forwardToRDate;
	}

	public String getForwardToDName() {
		return forwardToDName;
	}

	public void setForwardToDName(String forwardToDName) {
		this.forwardToDName = forwardToDName;
	}

	public String getForwardToBName() {
		return forwardToBName;
	}

	public void setForwardToBName(String forwardToBName) {
		this.forwardToBName = forwardToBName;
	}

	public String getForwardToAName() {
		return forwardToAName;
	}

	public void setForwardToAName(String forwardToAName) {
		this.forwardToAName = forwardToAName;
	}

	public String getForwardToCName() {
		return forwardToCName;
	}

	public void setForwardToCName(String forwardToCName) {
		this.forwardToCName = forwardToCName;
	}

	public String getForwardToAdName() {
		return forwardToAdName;
	}

	public void setForwardToAdName(String forwardToAdName) {
		this.forwardToAdName = forwardToAdName;
	}

	public String getForwardToRName() {
		return forwardToRName;
	}

	public void setForwardToRName(String forwardToRName) {
		this.forwardToRName = forwardToRName;
	}*/

	public Date getNotice15TillDate() {
		return notice15TillDate;
	}

	public void setNotice15TillDate(Date notice15TillDate) {
		this.notice15TillDate = notice15TillDate;
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

	/*public String getRejectedA() {
		return rejectedA;
	}

	public void setRejectedA(String rejectedA) {
		this.rejectedA = rejectedA;
	}

	public String getRejectedB() {
		return rejectedB;
	}

	public void setRejectedB(String rejectedB) {
		this.rejectedB = rejectedB;
	}

	public String getRejectedC() {
		return rejectedC;
	}

	public void setRejectedC(String rejectedC) {
		this.rejectedC = rejectedC;
	}

	public String getRejectedD() {
		return rejectedD;
	}

	public void setRejectedD(String rejectedD) {
		this.rejectedD = rejectedD;
	}

	public String getRejectedDa() {
		return rejectedDa;
	}

	public void setRejectedDa(String rejectedDa) {
		this.rejectedDa = rejectedDa;
	}

	public String getRejectedR() {
		return rejectedR;
	}

	public void setRejectedR(String rejectedR) {
		this.rejectedR = rejectedR;
	}*/

	public Long getNameTransaferId() {
		return nameTransaferId;
	}

	public void setNameTransaferId(Long nameTransaferId) {
		this.nameTransaferId = nameTransaferId;
	}

	@XmlTransient
	public List<BuildingPermitFloor> getFloor() {
		return floor;
	}

	@XmlTransient
	public List<BuildingMemberDetails> getMember() {
		return member;
	}

	@XmlTransient
	public List<BuildingPermitSurrounding> getSurrounding() {
		return surrounding;
	}

	@XmlTransient
	public Object getRajaswaData() {
		try {
			if (rajaswa.isEmpty()) {
				return null;
			}
			return rajaswa.get(0);
		} catch (Exception e) {
		}
		return null;
	}

	public void setMember(List<BuildingMemberDetails> member) {
		this.member = member;
	}

	public void setSurrounding(List<BuildingPermitSurrounding> surrounding) {
		this.surrounding = surrounding;
	}

	public void setFloor(List<BuildingPermitFloor> floor) {
		this.floor = floor;
	}

	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationNo", fetch = FetchType.EAGER)
	private List<BuildingMemberDetails> member;
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationNo", fetch = FetchType.EAGER)
	private List<BuildingPermitSurrounding> surrounding;

	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationNo", fetch = FetchType.EAGER)
	private List<BuildingPermitFloor> floor;

	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationNo", fetch = FetchType.EAGER)
	private List<RajaswaEntry> rajaswa;

	public String getIsDiscard() {
		return isDiscard;
	}

	public void setIsDiscard(String isDiscard) {
		this.isDiscard = isDiscard;
	}

	public String getDiscardReason() {
		return discardReason;
	}

	public void setDiscardReason(String discardReason) {
		this.discardReason = discardReason;
	}

	public String getDiscardDate() {
		return discardDate;
	}

	public void setDiscardDate(String discardDate) {
		this.discardDate = discardDate;
	}

	/*public String getChiefName() {
		return chiefName;
	}

	public void setChiefName(String chiefName) {
		this.chiefName = chiefName;
	}

	public Date getChiefDate() {
		return chiefDate;
	}

	public void setChiefDate(Date chiefDate) {
		this.chiefDate = chiefDate;
	}*/

	public String getDiscardFile() {
		return discardFile;
	}

	public void setDiscardFile(String discardFile) {
		this.discardFile = discardFile;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getMayadthapDate() {
		return mayadthapDate;
	}

	public void setMayadthapDate(String mayadthapDate) {
		this.mayadthapDate = mayadthapDate;
	}

	public Date getMayadthapRequestDate() {
		return mayadthapRequestDate;
	}

	public void setMayadthapRequestDate(Date mayadthapRequestDate) {
		this.mayadthapRequestDate = mayadthapRequestDate;
	}

	public Date getMayadthapBy() {
		return mayadthapBy;
	}

	public void setMayadthapBy(Date mayadthapBy) {
		this.mayadthapBy = mayadthapBy;
	}

	/*public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public java.util.Date geteDate() {
		return eDate;
	}

	public void seteDate(java.util.Date eDate) {
		this.eDate = eDate;
	}

	public String geteStatus() {
		return eStatus;
	}

	public void seteStatus(String eStatus) {
		this.eStatus = eStatus;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public java.util.Date getfDate() {
		return fDate;
	}

	public void setfDate(java.util.Date fDate) {
		this.fDate = fDate;
	}

	public String getfStatus() {
		return fStatus;
	}

	public void setfStatus(String fStatus) {
		this.fStatus = fStatus;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public java.util.Date getgDate() {
		return gDate;
	}

	public void setgDate(java.util.Date gDate) {
		this.gDate = gDate;
	}

	public String getgStatus() {
		return gStatus;
	}

	public void setgStatus(String gStatus) {
		this.gStatus = gStatus;
	}

	public String getPosteStatus() {
		return posteStatus;
	}

	public void setPosteStatus(String posteStatus) {
		this.posteStatus = posteStatus;
	}

	public String getPostfStatus() {
		return postfStatus;
	}

	public void setPostfStatus(String postfStatus) {
		this.postfStatus = postfStatus;
	}

	public String getPostgStatus() {
		return postgStatus;
	}

	public void setPostgStatus(String postgStatus) {
		this.postgStatus = postgStatus;
	}

	public String getPosteAction() {
		return posteAction;
	}

	public void setPosteAction(String posteAction) {
		this.posteAction = posteAction;
	}

	public String getPostfAction() {
		return postfAction;
	}

	public void setPostfAction(String postfAction) {
		this.postfAction = postfAction;
	}

	public String getPostgAction() {
		return postgAction;
	}

	public void setPostgAction(String postgAction) {
		this.postgAction = postgAction;
	}

	public String getForwardToE() {
		return forwardToE;
	}

	public void setForwardToE(String forwardToE) {
		this.forwardToE = forwardToE;
	}

	public String getForwardTof() {
		return forwardTof;
	}

	public void setForwardTof(String forwardTof) {
		this.forwardTof = forwardTof;
	}

	public String getForwardTog() {
		return forwardTog;
	}

	public void setForwardTog(String forwardTog) {
		this.forwardTog = forwardTog;
	}

	public String getForwardToERemark() {
		return forwardToERemark;
	}

	public void setForwardToERemark(String forwardToERemark) {
		this.forwardToERemark = forwardToERemark;
	}

	public String getForwardToFRemark() {
		return forwardToFRemark;
	}

	public void setForwardToFRemark(String forwardToFRemark) {
		this.forwardToFRemark = forwardToFRemark;
	}

	public String getForwardToGRemark() {
		return forwardToGRemark;
	}

	public void setForwardToGRemark(String forwardToGRemark) {
		this.forwardToGRemark = forwardToGRemark;
	}

	public String getForwardToEDate() {
		return forwardToEDate;
	}

	public void setForwardToEDate(String forwardToEDate) {
		this.forwardToEDate = forwardToEDate;
	}

	public String getForwardToFDate() {
		return forwardToFDate;
	}

	public void setForwardToFDate(String forwardToFDate) {
		this.forwardToFDate = forwardToFDate;
	}

	public String getForwardToGDate() {
		return forwardToGDate;
	}

	public void setForwardToGDate(String forwardToGDate) {
		this.forwardToGDate = forwardToGDate;
	}

	public String getForwardToEName() {
		return forwardToEName;
	}

	public void setForwardToEName(String forwardToEName) {
		this.forwardToEName = forwardToEName;
	}

	public String getForwardToFName() {
		return forwardToFName;
	}

	public void setForwardToFName(String forwardToFName) {
		this.forwardToFName = forwardToFName;
	}

	public String getForwardToGName() {
		return forwardToGName;
	}

	public void setForwardToGName(String forwardToGName) {
		this.forwardToGName = forwardToGName;
	}

	public String getRejectedE() {
		return rejectedE;
	}

	public void setRejectedE(String rejectedE) {
		this.rejectedE = rejectedE;
	}

	public String getRejectedG() {
		return rejectedG;
	}

	public void setRejectedG(String rejectedG) {
		this.rejectedG = rejectedG;
	}*/

	/*@Override
	public String toString() {
		return "\n{\"id\":\"" + id + "\",\"applicationNo\":\"" + applicationNo + "\",\"applicantYear\":\"" + applicantYear + "\",\"applicantSn\":\""
				+ applicantSn + "\",\"applicantDate\":\"" + applicantDate + "\",\"applicantMs\":\"" + applicantMs + "\",\"applicantName\":\"" + applicantName
				+ "\",\"applicantAddress\":\"" + applicantAddress + "\",\"applicantMobileNo\":\"" + applicantMobileNo + "\",\"citizenshipNo\":\""
				+ citizenshipNo + "\",\"nationalIdNo\":\"" + nationalIdNo + "\",\"fathersSpouseName\":\"" + fathersSpouseName + "\",\"oldMunicipal\":\""
				+ oldMunicipal + "\",\"oldWardNo\":\"" + oldWardNo + "\",\"newMunicipal\":\"" + newMunicipal + "\",\"newWardNo\":\"" + newWardNo
				+ "\",\"kittaNo\":\"" + kittaNo + "\",\"landArea\":\"" + landArea + "\",\"landAreaType\":\"" + landAreaType + "\",\"nearestLocation\":\""
				+ nearestLocation + "\",\"buildingJoinRoad\":\"" + buildingJoinRoad + "\",\"buildingJoinRoadType\":\"" + buildingJoinRoadType
				+ "\",\"buildingJoinRoadTypeOther\":\"" + buildingJoinRoadTypeOther + "\",\"landPassDate\":\"" + landPassDate + "\",\"landRegNo\":\""
				+ landRegNo + "\",\"ownershipName\":\"" + ownershipName + "\",\"certificateArea\":\"" + certificateArea + "\",\"landCertificateNo\":\""
				+ landCertificateNo + "\",\"landCertificateIssueDate\":\"" + landCertificateIssueDate + "\",\"purposeOfConstruction\":\""
				+ purposeOfConstruction + "\",\"purposeOfConstructionOther\":\"" + purposeOfConstructionOther + "\",\"constructionType\":\"" + constructionType
				+ "\",\"constructionTypeOther\":\"" + constructionTypeOther + "\",\"oldMapDate\":\"" + oldMapDate + "\",\"mohada\":\"" + mohada
				+ "\",\"constructionFinishing\":\"" + constructionFinishing + "\",\"constructionFinishingOther\":\"" + constructionFinishingOther
				+ "\",\"dhalNikasArrangement\":\"" + dhalNikasArrangement + "\",\"dhalNikasArrangementOther\":\"" + dhalNikasArrangementOther
				+ "\",\"foharArrangement\":\"" + foharArrangement + "\",\"foharArrangementOther\":\"" + foharArrangementOther + "\",\"pipeline\":\"" + pipeline
				+ "\",\"pipelineDistance\":\"" + pipelineDistance + "\",\"doPipelineConnection\":\"" + doPipelineConnection + "\",\"isHighTensionLine\":\""
				+ isHighTensionLine + "\",\"isHighTensionLineDistance\":\"" + isHighTensionLineDistance + "\",\"sadak\":\"" + sadak + "\",\"tol\":\"" + tol
				+ "\",\"dhalUnit\":\"" + dhalUnit + "\",\"pipelineUnit\":\"" + pipelineUnit + "\",\"highTensionLineUnit\":\"" + highTensionLineUnit
				+ "\",\"nibedakName\":\"" + nibedakName + "\",\"nibedakAdditional\":\"" + nibedakAdditional + "\",\"nibedakSadak\":\"" + nibedakSadak
				+ "\",\"nibedakTol\":\"" + nibedakTol + "\",\"remoteAddrDateTime\":\"" + remoteAddrDateTime + "\",\"enterBy\":\"" + enterBy
				+ "\",\"enterDate\":\"" + enterDate + "\",\"serName\":\"" + serName + "\",\"serDate\":\"" + serDate + "\",\"serStatus\":\"" + serStatus
				+ "\",\"erName\":\"" + erName + "\",\"erDate\":\"" + erDate + "\",\"erStatus\":\"" + erStatus + "\",\"designerStatus\":\"" + designerStatus
				+ "\",\"aminStatus\":\"" + aminStatus + "\",\"rajasowStatus\":\"" + rajasowStatus + "\",\"subEngineerStatus\":\"" + subEngineerStatus
				+ "\",\"engineerStatus\":\"" + engineerStatus + "\",\"chiefStatus\":\"" + chiefStatus + "\",\"designerAction\":\"" + designerAction
				+ "\",\"aminAction\":\"" + aminAction + "\",\"rajasowAction\":\"" + rajasowAction + "\",\"subEngineerAction\":\"" + subEngineerAction
				+ "\",\"engineerAction\":\"" + engineerAction + "\",\"chiefAction\":\"" + chiefAction + "\",\"aminiName\":\"" + aminiName
				+ "\",\"aminiDate\":\"" + aminiDate + "\",\"aminiStatus\":\"" + aminiStatus + "\",\"rwName\":\"" + rwName + "\",\"rwDate\":\"" + rwDate
				+ "\",\"rwStatus\":\"" + rwStatus + "\",\"applicationStatus\":\"" + applicationStatus + "\",\"applicationActionBy\":\"" + applicationActionBy
				+ "\",\"applicationAction\":\"" + applicationAction + "\",\"forwardToD\":\"" + forwardToD + "\",\"forwardToB\":\"" + forwardToB
				+ "\",\"forwardToA\":\"" + forwardToA + "\",\"forwardToC\":\"" + forwardToC + "\",\"forwardToAd\":\"" + forwardToAd + "\",\"forwardToR\":\""
				+ forwardToR + "\",\"forwardToDRemark\":\"" + forwardToDRemark + "\",\"forwardToBRemark\":\"" + forwardToBRemark + "\",\"forwardToARemark\":\""
				+ forwardToARemark + "\",\"forwardToCRemark\":\"" + forwardToCRemark + "\",\"forwardToAdRemark\":\"" + forwardToAdRemark
				+ "\",\"forwardToRRemark\":\"" + forwardToRRemark + "\",\"forwardToADate\":\"" + forwardToADate + "\",\"forwardToDDate\":\"" + forwardToDDate
				+ "\",\"forwardToCDate\":\"" + forwardToCDate + "\",\"forwardToBDate\":\"" + forwardToBDate + "\",\"forwardToAdDate\":\"" + forwardToAdDate
				+ "\",\"forwardToRDate\":\"" + forwardToRDate + "\",\"forwardToAName\":\"" + forwardToAName + "\",\"forwardToBName\":\"" + forwardToBName
				+ "\",\"forwardToCName\":\"" + forwardToCName + "\",\"forwardToDName\":\"" + forwardToDName + "\",\"forwardToAdName\":\"" + forwardToAdName
				+ "\",\"forwardToRName\":\"" + forwardToRName + "\",\"notice15TillDate\":\"" + notice15TillDate + "\",\"isLowTensionLine\":\""
				+ isLowTensionLine + "\",\"oldApplicationNo\":\"" + oldApplicationNo + "\",\"rejectedA\":\"" + rejectedA + "\",\"rejectedB\":\"" + rejectedB
				+ "\",\"rejectedC\":\"" + rejectedC + "\",\"rejectedD\":\"" + rejectedD + "\",\"rejectedDa\":\"" + rejectedDa + "\",\"rejectedR\":\""
				+ rejectedR + "\",\"photo\":\"" + photo + "\",\"talaThapAssign\":\"" + talaThapAssign + "\",\"talathapDocument\":\"" + talathapDocument
				+ "\",\"talaThapAssignBy\":\"" + talaThapAssignBy + "\",\"talaThapAssignDate\":\"" + talaThapAssignDate + "\",\"talathapApplicationNo\":\""
				+ talathapApplicationNo + "\",\"nameTransaferId\":\"" + nameTransaferId + "\",\"member\":\"" + member + "\",\"surrounding\":\"" + surrounding
				+ "\",\"floor\":\"" + floor + "\",\"rajaswa\":\"" + rajaswa + "\"}";
	}*/

}
