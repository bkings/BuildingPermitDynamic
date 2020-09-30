package com.model.utility;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "organization_master")
public class OrganizationMaster implements java.io.Serializable {

	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "NAME", columnDefinition = "VARCHAR")
	private String name;
	@Column(name = "OFFICE_NAME", columnDefinition = "VARCHAR")
	private String officeName;
	@Column(name = "ADDRESS", columnDefinition = "VARCHAR")
	private String address;
	@Column(name = "province")
	private String province;
	@Column(name = "MAYOR_NAME", columnDefinition = "VARCHAR")
	private String mayorName;
	@Column(name = "MAYOR_CONTAT_NO", columnDefinition = "VARCHAR")
	private String mayorContactNo;
	@Column(name = "SUB_MAYOR_NAME", columnDefinition = "VARCHAR")
	private String subMayorName;
	@Column(name = "SUB_MAYOR_CONTAT_NO", columnDefinition = "VARCHAR")
	private String subMayorContactNo;
	@Column(name = "SECRETARY_NAME", columnDefinition = "VARCHAR")
	private String secretaryName;
	@Column(name = "SECRETARY_CONTAT_NO", columnDefinition = "VARCHAR")
	private String secretaryContactNo;
	@Column(name = "OFFICE_CONTACT_NO", columnDefinition = "VARCHAR")
	private String officeContactNo;
	@Column(name = "EMAIL", columnDefinition = "VARCHAR")
	private String email;
	@Column(name = "URL")
	private String url;
	@Column(name = "FILE_LOCATION")
	private String fileLocation;
	@Column(name = "organization_code", updatable = false, insertable = false, nullable = true)
	private String organizationCode;
	@Column(name = "DESIGNER_LOGIN_TYPE")
	private String designerLoginType;

	/**
	 * email server
	 * 
	 */
	@Column(name = "MAIL_SERVER")
	private String mailServer;
	@Column(name = "MAIL_SERVER_PORT")
	private String mailServerPort;
	@Column(name = "MAIL_SENDER")
	private String mailSender;
	@Column(name = "MAIL_PASSWORD")
	private String mailPassword;
	@Column(name = "SHOW_SIGNATURE_IMAGE")
	private String showSignatureImage;
	
	public String getShowSignatureImage() {
		return showSignatureImage;
	}

	public void setShowSignatureImage(String showSignatureImage) {
		this.showSignatureImage = showSignatureImage;
	}

	public String getMailServer() {
		return mailServer;
	}

	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getMailSender() {
		return mailSender;
	}

	public void setMailSender(String mailSender) {
		this.mailSender = mailSender;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
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

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMayorName() {
		return mayorName;
	}

	public void setMayorName(String mayorName) {
		this.mayorName = mayorName;
	}

	public String getMayorContactNo() {
		return mayorContactNo;
	}

	public void setMayorContactNo(String mayorContactNo) {
		this.mayorContactNo = mayorContactNo;
	}

	public String getSubMayorName() {
		return subMayorName;
	}

	public void setSubMayorName(String subMayorName) {
		this.subMayorName = subMayorName;
	}

	public String getSubMayorContactNo() {
		return subMayorContactNo;
	}

	public void setSubMayorContactNo(String subMayorContactNo) {
		this.subMayorContactNo = subMayorContactNo;
	}

	public String getSecretaryName() {
		return secretaryName;
	}

	public void setSecretaryName(String secretaryName) {
		this.secretaryName = secretaryName;
	}

	public String getSecretaryContactNo() {
		return secretaryContactNo;
	}

	public void setSecretaryContactNo(String secretaryContactNo) {
		this.secretaryContactNo = secretaryContactNo;
	}

	public String getOfficeContactNo() {
		return officeContactNo;
	}

	public void setOfficeContactNo(String officeContactNo) {
		this.officeContactNo = officeContactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getDesignerLoginType() {
		return designerLoginType;
	}

	public void setDesignerLoginType(String designerLoginType) {
		this.designerLoginType = designerLoginType;
	}

	@Override
	public String toString() {
		return "\n{\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"officeName\":\"" + officeName + "\",\"address\":\""
				+ address + "\",\"province\":\"" + province + "\",\"mayorName\":\"" + mayorName
				+ "\",\"mayorContactNo\":\"" + mayorContactNo + "\",\"subMayorName\":\"" + subMayorName
				+ "\",\"subMayorContactNo\":\"" + subMayorContactNo + "\",\"secretaryName\":\"" + secretaryName
				+ "\",\"secretaryContactNo\":\"" + secretaryContactNo + "\",\"officeContactNo\":\"" + officeContactNo
				+ "\",\"email\":\"" + email + "\",\"url\":\"" + url + "\",\"fileLocation\":\"" + fileLocation
				+ "\",\"organizationCode\":\"" + organizationCode + "\",\"designerLoginType\":\"" + designerLoginType
				+ "\"}";
	}

}
