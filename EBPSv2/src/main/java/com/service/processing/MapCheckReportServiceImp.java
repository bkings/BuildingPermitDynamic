package com.service.processing;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.DaoImpMapCheckReport;
import com.dao.processing.DaoMapCheckReport;
import com.model.application.ApplicationApproved;
import com.model.processing.MapCheckReport;
import com.model.processing.MapCheckReportDetails;
import com.model.processing.MapCheckReportDetailsPK;

import model.DB;

@Service
public class MapCheckReportServiceImp implements MapCheckReportService {

	DaoMapCheckReport da = new DaoImpMapCheckReport();
	model.Message message = new model.Message();
	String msg = "", sql, pageId = "NJPP";
	int row;

	@Override
	public Object getindex(long applicationNo) {
		DB db = new DB();
		Map map;
		List list;
		sql = "SELECT R.ENTER_BY \"enterBy\",R.ENTER_DATE \"enterDate\",R.ER_DATE \"erDate\",R.ER_NAME \"erName\",R.ER_STATUS \"erStatus\",R.SER_DATE \"serDate\",R.SER_NAME \"serName\",R.SER_STATUS \"serStatus\",R.SUB_NAME \"subName\",R.SUB_DESIGNATION \"subDesignation\",R.SUB_SIGNATURE \"subSignature\",R.SUB_DATE \"subDate\" FROM building_permit_application A LEFT JOIN  map_check_report R ON A.id=R.application_no WHERE A.id='"
				+ applicationNo + "'";
		list = db.getRecord(sql);
		if (list.isEmpty()) {
			return message.respondWithError("invalid application no");
		}
		map = (Map) list.get(0);
		sql = "SELECT M.id AS \"descriptionId\",M.building_description description,M.SN SN,M.type,D.application_no \"applicationNo\",coalesce(D.design_data,'') AS \"designData\",coalesce(D.remark,'') AS remark FROM map_check_report_master M ,map_check_report_details D WHERE M.ID=D.class_id AND D.application_no='"
				+ applicationNo + "' UNION SELECT M.id AS \"descriptionId\",M.building_description description,M.sn,M.type,'" + applicationNo
				+ "' \"applicationNo\",'' AS \"designData\",'' AS remark FROM map_check_report_master M WHERE M.ID NOT IN(SELECT D.class_id FROM map_check_report_details D WHERE D.application_no='"
				+ applicationNo + "') order by  \"descriptionId\"";
		list = db.getRecord(sql);
		map.put("details", list);
		map.put("comment", message.getComment("" + applicationNo, "11"));
		map.put("history", message.getHistory(applicationNo, "11"));
		return map;
	}

	@Override
	public Object save(Long applicationNo, String jsonData, String Authorization) {
		try {
			JWTToken td = new JWTToken(Authorization);
			if (!td.isValid()) {
				return message.respondWithError("Authorization Error");
			}
			String userType = td.getUserType();
			if (!message.checkSaveStatus(userType, "11", applicationNo)) {
				return message.respondWithError(message.getMsg());
			}
			MapCheckReport obj = new MapCheckReport();
			MapCheckReportDetails details = new MapCheckReportDetails();
			MapCheckReportDetailsPK pk = new MapCheckReportDetailsPK();
			String jsonDataArray[] = message.jsonDataToStringArray(jsonData);
			Map map;
			map = message.objectMapper.readValue(jsonDataArray[0], new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {
			});
			obj.setEnterBy(td.getUserId());
			obj.setEnterDate(new Date());
			obj.setSerDate(null);
			obj.setErStatus("N");
			obj.setErDate(null);
			obj.setSerStatus("N");
			obj.setApplicationNo(applicationNo);
			try {
				obj.setSubName(map.get("subName").toString());
			} catch (Exception e) {
				obj.setSubName("");
			}

			try {
				obj.setSubDesignation(map.get("subDesignation").toString());
			} catch (Exception e) {
				obj.setSubDesignation("");
			}

			try {
				obj.setSubDate(map.get("subDate").toString());
			} catch (Exception e) {
				obj.setSubDate("");
			}
			try {
				obj.setSubSignature(map.get("subSignature").toString());
			} catch (Exception e) {
				obj.setSubSignature("");
			}

			row = da.save(obj);
			message.setHistory(applicationNo, td.getUserType(), "11", "S", td.getUserName());
			if (row == 0) {
				return message.respondWithError(da.getMsg());
			} else {
				message.list = message.objectMapper.readValue(jsonDataArray[1], new com.fasterxml.jackson.core.type.TypeReference<List>() {
				});
				int descriptionId;
				for (int i = 0; i < message.list.size(); i++) {
					map = (Map) message.list.get(i);
					descriptionId = Integer.parseInt(map.get("descriptionId").toString());
					details.setDesignData(map.get("designData").toString());
					details.setRemark(map.get("remark").toString());
					pk.setApplicationNo(applicationNo);
					pk.setClassId(descriptionId);
					details.setPk(pk);
					da.save(details);
				}
			}

			if (row > 0) {
				message.db.save(message.getEnterByStatus(userType, 11, applicationNo));
				message.map = new HashMap();// ("12");
				message.map.put("message", "Success");
				return message.map;
			} else {
				if (msg.contains("Duplicate entry")) {
					msg = "This record already exist";
				}
				return message.respondWithError(msg);
			}

		} catch (Exception e) {
			return message.respondWithError("String " + e.getMessage());
		}
	}

	@Override
	public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization) {
		return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "11");
	}
}/*
	 * JWTToken td = new JWTToken(Authorization); if (!td.isValid()) { return
	 * message.respondWithError("Authorization Error"); }
	 * 
	 * if (!message.checkApproveStatus(td.getUserType(), "11", applicationNo)) {
	 * return message.respondWithError(message.getMsg()); } String status =
	 * obj.getStatus(); sql =
	 * "SELECT coalesce(er_status,'P') \"erStatus\" , coalesce(ser_status,'P') \"serStatus\", coalesce(chief_status,'P') \"chiefStatus\", enter_by \"enterBy\" FROM map_check_report WHERE application_no="
	 * + applicationNo; message.list = message.db.getRecord(sql); if
	 * (message.list.isEmpty()) { return
	 * message.respondWithError("Invalid Application No."); } message.map = (Map)
	 * message.list.get(0); String erStatus =
	 * message.map.get("erStatus").toString(); String serStatus =
	 * message.map.get("serStatus").toString(); String chiefStatus =
	 * message.map.get("chiefStatus").toString(); String enterBy=
	 * message.map.get("enterBy").toString();
	 * 
	 * if (td.getUserType().equalsIgnoreCase("B")) { if
	 * (chiefStatus.equalsIgnoreCase("A")) { return
	 * message.respondWithError("Application has been already approved by Chief!!");
	 * } if (erStatus.equalsIgnoreCase("A")) { return message.
	 * respondWithError("Application has been already approved by Engineer!!"); }
	 * 
	 * if (serStatus.equalsIgnoreCase("A")) { return message.
	 * respondWithError("Application has been already approved by SubEngineer!!"); }
	 * 
	 * if (status.equalsIgnoreCase("A")) { sql =
	 * "UPDATE map_check_report SET ser_date=NOW(),ser_name='" + td.getUserName() +
	 * "',ser_status='" + status + "' WHERE application_no=" + applicationNo;
	 * message.db.save(sql); message.setHistory(applicationNo, td.getUserType(),
	 * "11", status, td.getUserName());
	 * 
	 * sql = "UPDATE building_permit_application SET sub_engineer_status='" + status
	 * + "',sub_engineer_action='11',application_status='" + status +
	 * "',application_action='11',application_action_by='B' WHERE id=" +
	 * applicationNo; message.db.save(sql); message.map = new HashMap();
	 * message.map.put("message", "Application Approved"); } else { sql =
	 * "UPDATE map_check_report SET enter_date=null, ser_date=NOW(),ser_name='" +
	 * td.getUserName() + "',ser_status='" + status + "' WHERE application_no=" +
	 * applicationNo; message.db.save(sql); message.setHistory(applicationNo,
	 * td.getUserType(), "11", status, td.getUserName());
	 * 
	 * sql = "UPDATE building_permit_application SET sub_engineer_status='" + status
	 * + "',sub_engineer_action='11',application_status='" + status +
	 * "',application_action='11',application_action_by='B' WHERE id=" +
	 * applicationNo; message.db.save(sql); message.map = new HashMap();
	 * message.map.put("message", "Application Rejected.");
	 * message.comment(applicationNo, td.getUserName(), "B", 11, obj.getComment(),
	 * obj.getRemark(),enterBy); } //return message.map; } else if
	 * (td.getUserType().equalsIgnoreCase("A")) { if
	 * (chiefStatus.equalsIgnoreCase("A")) { return
	 * message.respondWithError("Application has been already approved by Chief!!");
	 * } if (erStatus.equalsIgnoreCase("A")) { return message.
	 * respondWithError("Application has been already approved by Engineer!!"); }
	 * 
	 * //("11"); if (status.equalsIgnoreCase("A")) { sql =
	 * "UPDATE map_check_report SET er_date=NOW(),er_name='" + td.getUserName() +
	 * "',er_status='" + status + "' WHERE application_no=" + applicationNo;
	 * message.db.save(sql); message.setHistory(applicationNo, td.getUserType(),
	 * "11", status, td.getUserName());
	 * 
	 * sql = "UPDATE building_permit_application SET engineer_status='" + status +
	 * "',engineer_action='11',application_status='" + status +
	 * "',application_action='11',application_action_by='A' WHERE id=" +
	 * applicationNo; message.db.save(sql); message.map = new HashMap();
	 * message.map.put("message", "Application Approved"); } else { sql =
	 * "UPDATE map_check_report SET enter_date=null, er_date=NOW(),er_name='" +
	 * td.getUserName() + "',er_status='" + status + "' WHERE application_no=" +
	 * applicationNo; message.db.save(sql); message.setHistory(applicationNo,
	 * td.getUserType(), "11", status, td.getUserName());
	 * 
	 * sql = "UPDATE building_permit_application SET engineer_status='" + status +
	 * "',engineer_action='11',application_status='" + status +
	 * "',application_action='11',application_action_by='A' WHERE id=" +
	 * applicationNo; message.db.save(sql); message.map = new HashMap();
	 * message.map.put("message", "Application Rejected.");
	 * message.comment(applicationNo, td.getUserName(), td.getUserType(), 11,
	 * obj.getComment(), obj.getRemark(),enterBy); } } else if
	 * (td.getUserType().equalsIgnoreCase("C")) { if
	 * (chiefStatus.equalsIgnoreCase("A")) { return
	 * message.respondWithError("Application has been already approved by Chief!!");
	 * }
	 * 
	 * if (status.equalsIgnoreCase("A")) { sql =
	 * "UPDATE map_check_report SET chief_date=NOW(),chief_name='" +
	 * td.getUserName() + "',chief_status='" + status + "' WHERE application_no=" +
	 * applicationNo; message.db.save(sql); message.setHistory(applicationNo,
	 * td.getUserType(), "11", status, td.getUserName());
	 * 
	 * sql = "UPDATE building_permit_application SET chief_status='" + status +
	 * "',chief_action='11',application_status='" + status +
	 * "',application_action='11',application_action_by='C' WHERE id=" +
	 * applicationNo; message.db.save(sql); message.map = new HashMap();
	 * message.map.put("message", "Application Approved"); } else { sql =
	 * "UPDATE map_check_report SET enter_date=null, chief_date=NOW(),chief_name='"
	 * + td.getUserName() + "',chief_status='" + status + "' WHERE application_no="
	 * + applicationNo; message.db.save(sql); message.setHistory(applicationNo,
	 * td.getUserType(), "11", status, td.getUserName());
	 * 
	 * sql = "UPDATE building_permit_application SET chief_status='" + status +
	 * "',chief_action='11',application_status='" + status +
	 * "',application_action='11',application_action_by='C' WHERE id=" +
	 * applicationNo; message.db.save(sql); message.map = new HashMap();
	 * message.map.put("message", "Application Rejected.");
	 * message.comment(applicationNo, td.getUserName(), td.getUserType(), 11,
	 * obj.getComment(), obj.getRemark(),enterBy); } } //
	 * message.setApplicationActionStatus(applicationNo, td.getUserType(), 11,
	 * status); return message.map; } }
	 */
