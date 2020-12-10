package com.service.application;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.application.DaoImpBuildingPermitApplication;
import com.model.application.ApplicationApproved;
import com.model.application.BuildingMemberDetails;
import com.model.application.BuildingMemberDetailsPK;
import com.model.application.BuildingPermitApplication;
import com.model.application.BuildingPermitFloor;
import com.model.application.BuildingPermitFloorPK;
import com.model.application.BuildingPermitSurrounding;
import com.model.application.BuildingPermitSurroundingPK;
import com.model.dynamic.ApplicationStatus;
import com.model.dynamic.ApplicationStatusPK;
import com.model.dynamic.Status;
import com.model.dynamic.StatusPK;
import com.service.dynamic.ApplicationStatusService;
import com.service.dynamic.StatusService;

import model.ApplicationForm;
import model.DateConvert;
import model.Message;

@Service
public class BuildingPermitApplicationServiceImp implements BuildingPermitApplicationService {

	@Autowired
	DaoImpBuildingPermitApplication da;
	@Autowired
	ApplicationStatusService applicationStatusService;
	@Autowired
	StatusService statusService;
	Message message = new Message();
	String msg = "", sql;
	int row;

	@Override
	public Object getIndex(String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isValid()) {
			return new Message().respondWithError("Authorization Error");
		}
		String userType = td.getUserType();
		if (userType.equalsIgnoreCase("D")) {
			sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(designer_action,'1') \"yourForm\",designer_status \"yourStatus\",forward_to_d_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application where enter_by='"
					+ td.getUserId() + "' AND COALESCE(IS_DISCARD,'N')!='Y' order by forward_to_d_date desc";
		} else if (userType.equalsIgnoreCase("B")) {
			sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(sub_engineer_action,'1') \"yourForm\",coalesce(sub_engineer_status,'P') \"yourStatus\",forward_to_b_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application where COALESCE(IS_DISCARD,'N')!='Y' order by forward_to_b_date desc";
		} else if (userType.equalsIgnoreCase("A")) {
			sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(engineer_action,'1') \"yourForm\",coalesce(engineer_status,'P') \"yourStatus\",forward_to_a_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application  where COALESCE(IS_DISCARD,'N')!='Y' order by forward_to_a_date desc";
		} else if (userType.equalsIgnoreCase("C")) {
			sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(chief_action,'1') \"yourForm\",coalesce(chief_status,'P') \"yourStatus\",forward_to_c_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application where COALESCE(IS_DISCARD,'N')!='Y'  order by forward_to_c_date desc";
		} else if (userType.equalsIgnoreCase("R")) {
			sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(rajasow_action,'1') \"yourForm\",coalesce(rajasow_status,'P') \"yourStatus\",forward_to_r_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application where COALESCE(IS_DISCARD,'N')!='Y'  order by forward_to_r_date desc";
		} else if (userType.equalsIgnoreCase("AD")) {
			sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(amin_action,'1') \"yourForm\",coalesce(amin_status,'P') \"yourStatus\",forward_to_ad_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application order where COALESCE(IS_DISCARD,'N')!='Y'  by forward_to_ad_date desc";
		} else if (userType.equalsIgnoreCase("E")) {
			sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(poste_action,'1') \"yourForm\",coalesce(poste_status,'P') \"yourStatus\",forward_to_e_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application order where COALESCE(IS_DISCARD,'N')!='Y'  by forward_to_e_date desc";
		} else if (userType.equalsIgnoreCase("F")) {
			sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(postf_action,'1') \"yourForm\",coalesce(postf_status,'P') \"yourStatus\",forward_to_f_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application order where COALESCE(IS_DISCARD,'N')!='Y'  by forward_to_f_date desc";
		} else if (userType.equalsIgnoreCase("G")) {
			sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(postg_action,'1') \"yourForm\",coalesce(postg_status,'P') \"yourStatus\",forward_to_g_remark \"forwardTo\",construction_type \"constructionType\"  FROM building_permit_application order where COALESCE(IS_DISCARD,'N')!='Y'  by forward_to_g_date desc";
		}

		message.list = message.db.getRecord(sql);
		List l = new ArrayList();
		String constructionType;
		for (int i = 0; i < message.list.size(); i++) {
			message.map = (Map) message.list.get(i);

			try {
				message.map.put("applicationAction", ApplicationForm.getById(message.map.get("applicationAction").toString()));
			} catch (Exception e) {
			}
			try {
				message.map.put("yourAction", ApplicationForm.getById(message.map.get("yourForm").toString()));
			} catch (Exception e) {
			}
			try {
				String applicationStatus = message.map.get("applicationStatus").toString();
				if (!applicationStatus.equalsIgnoreCase("C")) {
					applicationStatus = "P";
				}
				message.map.put("applicationStatus", Message.applicationStatus(applicationStatus));
			} catch (Exception e) {
			}
			try {
				message.map.put("yourStatus", Message.status(message.map.get("yourStatus").toString()));
			} catch (Exception e) {
			}
			try {
				message.map.put("applicationActionBy", new com.UserTypeName().userTypeName(message.map.get("applicationActionBy").toString()));
			} catch (Exception e) {
			}
			try {
				constructionType = message.map.get("constructionType").toString();
				if (constructionType.equalsIgnoreCase("1")) {
					message.map.put("constructionType", "नयाँ");
				} else if (constructionType.equalsIgnoreCase("2")) {
					message.map.put("constructionType", "पुरानो");
				} else if (constructionType.equalsIgnoreCase("3")) {
					message.map.put("constructionType", "तला थप");
				}
			} catch (Exception e) {
				constructionType = "1";
				message.map.put("constructionType", "नयाँ");
			}
			l.add(message.map);
		}
		return l;
	}

	@Override
	public Object getIndex(String Authorization, Long id) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isValid()) {
			return new Message().respondWithError("Authorization Error");
		}
		List<BuildingPermitApplication> l = da.getAll("from BuildingPermitApplication where id='" + id + "'");
		if (l.isEmpty()) {
			return message.respondWithError("Invalid Application no " + id);
		}

		BuildingPermitApplication obj = l.get(0);
		Long nameTransferId = 0l;
		try {
			nameTransferId = obj.getNameTransaferId();
		} catch (Exception e) {
			nameTransferId = 0l;
		}

		String naamsariStatus = obj.getNaamsariStatus();
		String userType = td.getUserType().toLowerCase();
		String buildingClass = "";
		try {
			sql = "SELECT building_class \"buildingClass\" FROM anusuchi_ka WHERE application_no='" + obj.getId() + "'";
			message.list = message.db.getRecord(sql);
			if (!message.list.isEmpty()) {
				message.map = (Map) message.list.get(0);
				buildingClass = message.map.get("buildingClass").toString();
			}
		} catch (Exception e) {
		}
		message.map = new HashMap();
		userType = td.getUserType();
		String approveBy = "";
		/*
		 * if (userType.equalsIgnoreCase("A")) { approveBy =
		 * ",COALESCE(engr_approval,'N') \"approveBy\""; } else if
		 * (userType.equalsIgnoreCase("B")) { approveBy =
		 * ",COALESCE(sub_engr_approval,'N') \"approveBy\""; } else if
		 * (userType.equalsIgnoreCase("C")) { approveBy =
		 * ",COALESCE(chief_approval,'N') \"approveBy\""; } else if
		 * (userType.equalsIgnoreCase("D")) { approveBy =
		 * ",COALESCE(designer_approval,'N') \"approveBy\""; } else if
		 * (userType.equalsIgnoreCase("R")) { approveBy =
		 * ",COALESCE(rajasow_approval,'N') \"approveBy\""; } else if
		 * (userType.equalsIgnoreCase("AD")) { approveBy =
		 * ",COALESCE(amin_approval,'N') \"approveBy\""; } else if
		 * (userType.equalsIgnoreCase("E")) { approveBy =
		 * ",COALESCE(poste_approval,'N') \"approveBy\""; } else if
		 * (userType.equalsIgnoreCase("F")) { approveBy =
		 * ",COALESCE(postf_approval,'N') \"approveBy\""; } else if
		 * (userType.equalsIgnoreCase("G")) { approveBy =
		 * ",COALESCE(postg_approval,'N') \"approveBy\""; }
		 */
		sql = "SELECT id \"groupId\" FROM form_group_master  ORDER BY group_position";
		List list = message.db.getRecord(sql);
		List list1, list2 = new ArrayList();
		String groupId;
		String hasRevised = message.hasRevisedForm(userType, id);
//        String groupType = message.getGroupType(obj.getConstructionType());
		String groupType = message.getGroupType1(obj.getConstructionType(), nameTransferId);
		String classGroup = "-1";
		if (buildingClass.length() > 0) {
			classGroup = message.classGroup(groupType, buildingClass);
		}
		Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			int j = 0;
			try {
				map = (Map) list.get(i);
				groupId = map.get("groupId").toString();
				sql = "SELECT G.group_id \"groupId\",F.\"name\" \"formName\",F.view_url \"viewURL\",F.id,round(CAST(\"group_position\" AS NUMERIC), 2) AS \"position\",CASE WHEN (enter_by=G.user_type) THEN 'Y' ELSE 'N' END AS \"enterBy\","
						+ "coalesce((SELECT coalesce(approval_status,'N') FROM form_permissions P WHERE P.form_id=F.id AND P.user_type='" + userType
						+ "'),'N') \"approveBy\" FROM form_group G,form_name_master F WHERE G.form_id=F.id AND  G.group_id='" + groupId + "' AND G.group_type='"
						+ groupType + "' AND G.user_type='" + td.getUserType() + "' AND F.ID NOT IN(" + hasRevised + "," + classGroup
						+ ") ORDER BY \"position\"";
				list1 = message.db.getRecord(sql);
				for (j = 0; j < list1.size(); j++) {
					try {
						map = (Map) list1.get(j);
						list2.add(map);
					} catch (Exception e) {
					}
				}
				map = new HashMap();
				if (j > 0) {
					map.put("groupId", groupId);
					map.put("formName", "माथिको तह मा स्वीकृत को लागि पठाउनु !");
					map.put("viewURL", "/user/forms/forward-to-next");
					map.put("position", "100");
					map.put("enterBy", "N");
					map.put("approveBy", "Y");

					list2.add(map);
				}
			} catch (Exception e) {
			}
		}

		message.map.put("data", obj);
		message.map.put("menu", list2);
		message.map.put("comment", message.getComment(id.toString(), "1"));
		message.map.put("history", message.getHistory(id.toString(), "1"));
		sql = "SELECT building_class \"buildingClass\" FROM anusuchi_ka WHERE application_no='" + id + "'";
		list2 = message.db.getRecord(sql);
		if (list2.isEmpty()) {
			message.map.put("buildingClass", "NA");
		} else {
			map = (Map) list2.get(0);
			message.map.put("buildingClass", map.get("buildingClass"));
		}

		// naamsari status
		message.map.put("naamsariStatus", naamsariStatus);
		// naamsari bhayo ra approve pani bhayeko awasthama
		if (nameTransferId != null && !groupType.equalsIgnoreCase("N")) {
			sql = "SELECT ID as \"nameTransferId\",application_no AS \"applicationNo\",json_data AS \"jsonData\" from certificate_note";
			message.map.put("certificateNoteData", message.db.getRecord(sql));
		}

		sql = "SELECT user_type \"userType\",status \"userStatus\" FROM status WHERE application_no=" + id + " AND form_id=1";
		message.map.put("status", message.db.getRecord(sql));
		sql = "SELECT user_type\"userType\",user_full_status \"applicationStatus\" FROM application_status WHERE application_no=" + id;
		message.map.put("applicationStatus", message.db.getRecord(sql));
		return message.map;
	}

	@Override
	public Object doApprove(HttpServletRequest request, Long applicationNo, String regNo, ApplicationApproved approved, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (td.getUserType().equalsIgnoreCase("B")) {
			sql = "UPDATE building_permit_application SET REG_NO='" + regNo + "' WHERE id='" + applicationNo + "'";
			message.db.save(sql);
		}
		return new ApplicationApprove().doApprove(applicationNo, approved, Authorization, "1");
	}

	@Override
	public Object getTask(Long applicationNo, String constructionType, String year, String nibedakName, String kittaNo, String wardNo, String applicationStatus,
			String Authorization) {
		return new ApplicationTaskList().get(applicationNo, constructionType, year, nibedakName, kittaNo, wardNo, applicationStatus, Authorization);
	}

	@Override
	public Object getFilter(Long applicationNo, String constructionType, String year, String nibedakName, String kittaNo, String wardNo, String Authorization,
			String applicationStatus) {
		return new ApplicationFIlter().get(applicationNo, constructionType, year, nibedakName, kittaNo, wardNo, Authorization, applicationStatus);
	}

	@Override
	public Object doPhotoUpload(HttpServletRequest request, Long id, MultipartFile photo, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isValid()) {
			return new Message().respondWithError("Authorization Error");
		}
		if (!message.checkSaveStatus(td.getUserType(), "1", 1l)) {
			return message.respondWithError(message.getMsg());
		}
		String fileStorageLocation = message.filePath(request.getContextPath());
		String fiscalYear = String.valueOf(id).substring(0, 4);
		String filePath = "/" + fiscalYear + "/" + id + "/";
		String fileName = "";
		File f = new File(fileStorageLocation + filePath);
		try {
			if (!f.exists()) {
				f.mkdirs();
			}
		} catch (Exception e) {
			return message.respondWithError(e.getMessage());
		}
		fileName = filePath + "photo.png";

		try {
			f = new File(fileStorageLocation + fileName);
			if (f.exists()) {
				f.delete();
			}
		} catch (Exception e) {
		}
		f = new File(fileStorageLocation + fileName);
		try {
			photo.transferTo(f);
			sql = "UPDATE building_permit_application SET photo='" + fileName + "' WHERE ID=" + id;
			message.db.save(sql);
			String ip = request.getServerName();
			int port = request.getServerPort();
			msg = "http://" + ip + ":" + port + request.getContextPath() + "Document/" + fileName;
			return message.respondWithMessage(msg);
		} catch (Exception e) {
			return message.respondWithError(e.getMessage());
		}
	}

	@Override
	public Object completedList(String constructionType, Long year, Long applicationNo, String nibedakName, String Authorization) {
		return new ApplicationCompleted().get(constructionType, year, applicationNo, nibedakName, Authorization);
	}

	@Override
	public Object completedList(HttpServletRequest request, Long applicationNo, String designer, MultipartFile file, String Authorization) {
		return new ApplicationCompleted().issueDesigner(request, applicationNo, designer, file, Authorization);
	}

	@Override
	public Object getTalathap(String Authorization, String status, Long year) {
		return new ApplicationCompleted().talathap(Authorization, status, year);
	}

	@Override
	public Object doSave(HttpServletRequest request, BuildingPermitApplication obj, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isValid()) {
			return new Message().respondWithError("Authorization Error");
		}
		if (!message.checkSaveStatus(td.getUserType(), "1", 1l)) {
			return message.respondWithError(message.getMsg().toString());
		}
		List<BuildingMemberDetails> member = obj.getMember();
		List<BuildingPermitSurrounding> surrounding = obj.getSurrounding();
		List<BuildingPermitFloor> floor = obj.getFloor();
		try {
			Long id = 0l, temp = 0l;
			try {
				id = obj.getId();
				temp = obj.getId(); // for applicationStatus
			} catch (Exception e) {
				id = 0l;
				temp = 0l;
			}
			String today = DateConvert.today();

			if (id.toString().length() < 6) {
				sql = "SELECT year_code as \"fiscalYear\" FROM fiscal_year WHERE '" + today + "' BETWEEN start_date AND end_date";
				message.list = da.getRecord(sql);
				if (message.list.isEmpty() || message.list.size() == 0) {
					return message.respondWithError("please define fiscal year of today");
				}
				message.map = (Map) message.list.get(0);
				int applicantYear = Integer.parseInt(message.map.get("fiscalYear").toString());
				sql = "SELECT coalesce(MAX(APPLICANT_SN),0)+1 AS \"applicantSn\" FROM building_permit_application WHERE APPLICANT_YEAR='" + applicantYear + "'";
				message.map = (Map) da.getRecord(sql).get(0);
				int applicantSn = Integer.parseInt(message.map.get("applicantSn").toString());

				if (applicantSn < 10) {
					id = Long.parseLong(applicantYear + "00000" + applicantSn);
				} else if (applicantSn < 100) {
					id = Long.parseLong(applicantYear + "0000" + applicantSn);
				} else if (applicantSn < 1000) {
					id = Long.parseLong(applicantYear + "000" + applicantSn);
				} else if (applicantSn < 10000) {
					id = Long.parseLong(applicantYear + "00" + applicantSn);
				} else if (applicantSn < 100000) {
					id = Long.parseLong(applicantYear + "0" + applicantSn);
				} else {
					id = Long.parseLong(applicantYear + "" + applicantSn);
				}
				obj.setEnterDate(today);
				obj.setApplicantDate(new Date());
				obj.setApplicantYear(applicantYear);
				obj.setApplicantSn(applicantSn);
				obj.setId(id);
				obj.setApplicationNo(id);
			}
			try {
				for (int i = 0; i < member.size(); i++) {
					member.get(i).setPk(new BuildingMemberDetailsPK(id, member.get(i).getMemberName()));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			try {
				for (int i = 0; i < surrounding.size(); i++) {
					surrounding.get(i).setPk(new BuildingPermitSurroundingPK(id, surrounding.get(i).getSide()));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			try {
				/**
				 * Remove some floor data during update
				 */
				sql = "SELECT * FROM building_permit_floor WHERE application_no=" + id + " ";
				message.list = da.getRecord(sql);
				if (!message.list.isEmpty()) {
					sql = "DELETE FROM building_permit_floor WHERE application_no = " + id + "";
					int row = da.delete(sql);
				}
				for (int i = 0; i < floor.size(); i++) {
					floor.get(i).setPk(new BuildingPermitFloorPK(id, floor.get(i).getFloor(), floor.get(i).getBlock()));
				}
			} catch (Exception e) {
				System.out.println(e);
			}

			String now = DateConvert.now();
			String remoteAddr = request.getRemoteAddr();
			String remoteAddrDateTime = remoteAddr + "_" + now;
			remoteAddrDateTime = remoteAddrDateTime.replace(" ", "");
			remoteAddrDateTime = remoteAddrDateTime.replace("-", "");
			remoteAddrDateTime = remoteAddrDateTime.replace(":", "");
			/*
			 * obj.setForwardToD("I"); obj.setForwardToDDate(DateConvert.now());
			 * obj.setForwardToDName(td.getUserName());
			 * obj.setForwardToDName("डिजाइनर बाट काम भइरहेको छ!");
			 * obj.setForwardToDDate(DateConvert.now());
			 * obj.setForwardToDName(td.getUserName()); obj.setDesignerAction("1");
			 * obj.setDesignerStatus("A");
			 */
			obj.setApplicationStatus("A");
			obj.setApplicationActionBy("D");
			obj.setApplicationAction("1");
			/*
			 * obj.setSerStatus("P"); obj.setSerName(null); obj.setSerDate(null);
			 * obj.setErStatus("P"); obj.setErDate(null); obj.setErName(null);
			 */
			obj.setRemoteAddrDateTime(remoteAddrDateTime);
			obj.setEnterBy(td.getUserId());
			obj.setEnterDate(now);
			obj.setIsDiscard("N");
			row = da.save(obj);
			if (row == 0) {
				return message.respondWithError(da.getMsg());
			}

			if (String.valueOf(temp).length() < 6) {
				sql = "SELECT id as \"userType\" FROM user_type_master WHERE id NOT IN ('ADM','TADM')";
				List userTypeList = da.getRecord(sql);
				String userType;
				Map mm = new HashMap<>();
				for (Object u : userTypeList) {
					mm = (Map) u;
					try {
						userType = mm.get("userType").toString();
						ApplicationStatus applicationStatus = new ApplicationStatus();
						applicationStatus.setPk(new ApplicationStatusPK(id, userType));
						if (userType.equalsIgnoreCase("D")) {
							applicationStatus.setForwardToUser("I");
							applicationStatus.setForwardToUserDate(DateConvert.now());
							applicationStatus.setForwardToUserName(td.getUserName());
							applicationStatus.setForwardToUserRemark("डिजाइनर बाट काम भइरहेको छ!");
						}
						applicationStatusService.save(applicationStatus);
					} catch (Exception e) {
						System.out.println("e msg " + e.getMessage());
					}

					try {
						userType = mm.get("userType").toString();
						sql = "SELECT F.id as \"id\",E.id as \"tableId\" FROM form_name_master F,ebps_tables E WHERE E.id=F.table_id AND E.table_name='building_permit_application'";
						List forId = da.getRecord(sql);
						Map mForId = (Map) forId.get(0);
						Status status = new Status();
						status.setPk(new StatusPK(id, Long.parseLong(mForId.get("id").toString()), userType));
						status.setTableId(Long.parseLong(mForId.get("tableId").toString()));
						statusService.save(status);
					} catch (Exception e) {
						System.out.println("ex " + e.getMessage());
					}
				}
			}
			message.map = new HashMap();
			message.map.put("message", "Success");
			message.map.put("obj", obj);
			return message.map;
		} catch (Exception e) {
			return message.respondWithError(e.getMessage());
		}
	}

	@Override
	public Object doDiscard(HttpServletRequest request, Long id, String date, String reason, MultipartFile file, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isValid()) {
			return message.respondWithError("Authorization Error");
		}
		String userType = td.getUserType();

		if (!(userType.equalsIgnoreCase("ADM") || userType.equalsIgnoreCase("C") || userType.equalsIgnoreCase("A"))) {
			return message.respondWithError("you can't access this feature!!");
		}
		String fileName = "";
		try {

			if (file.getSize() > 10) {

				String fileStorageLocation = message.filePath(request.getContextPath());
				String fiscalYear = String.valueOf(id).substring(0, 4);
				String filePath = "/" + fiscalYear + "/" + id + "/";

				File f = new File(fileStorageLocation + filePath);
				try {
					if (!f.exists()) {
						f.mkdirs();
					}
				} catch (Exception e) {
					return message.respondWithError(e.getMessage());
				}

				fileName = filePath + "Discard" + file.getOriginalFilename().replace(" ", "");
				try {
					f = new File(fileStorageLocation + fileName);
					if (f.exists()) {
						f.delete();
					}
				} catch (Exception e) {
				}
				f = new File(fileStorageLocation + fileName);
				try {
					file.transferTo(f);
				} catch (Exception e) {
					return message.respondWithError(e.getMessage());
				}
			}
		} catch (Exception e) {
		}

		sql = "UPDATE building_permit_application SET IS_DISCARD='Y',DISCARD_REASON='" + reason + "',DISCARD_DATE='" + date + "',DISCARD_FILE='" + fileName
				+ "' WHERE id='" + id + "' AND COALESCE(IS_DISCARD,'N')!='Y'";
		row = da.delete(sql);
		if (row == 0) {

			return message.respondWithError(da.getMsg());
		}
		return message.respondWithMessage("Succcess!!");
	}

	@Override
	public Object doDiscard(HttpServletRequest request, Long year, String Authorization) {

		return da.getAll("from BuildingPermitApplication where discardDate like '" + year + "%'");
	}

	@Override
	public Object expiredApplications(String Authorization) {
		return new ExpiredApplications().get(Authorization);
	}

	@Override
	public Object expiredApplications(Long id, String Authorization) {
		return new ExpiredApplications().set(id, Authorization);
	}

	@Override
	public Object expiredApplicationsRequest(String Authorization) {
		return new ExpiredApplications().getRequest(Authorization);
	}

	@Override
	public Object expiredApplicationsRequest(Long id, String Authorization) {
		return new ExpiredApplications().setRequest(id, Authorization);
	}

}
