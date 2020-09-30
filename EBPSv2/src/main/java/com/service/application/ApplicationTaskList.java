/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.config.JWTToken;

import model.ApplicationForm;
import model.DateConvert;
import model.Message;

public class ApplicationTaskList {

	public Object get(Long applicationNo, String constructionType, String year, String nibedakName, String kittaNo, String wardNo, String applicationStatus,
			String Authorization) {
		Message message = new Message();
		List list = new ArrayList<>();
		Map m = new HashMap<>();

		JWTToken td = new JWTToken(Authorization);
		if (!td.isValid()) {
			return message.respondWithError("Authorization Error");
		}
		try {
			if (applicationStatus.equalsIgnoreCase("C")) {
				applicationStatus = " AND application_status ='C'";
			} else if (applicationStatus.equalsIgnoreCase("P")) {
				applicationStatus = " AND application_status !='C'";
			} else {
				applicationStatus = " AND application_status !='C' ";
			}
		} catch (Exception e) {
			applicationStatus = "";
		}
		if (nibedakName.length() > 1) {
			nibedakName = " AND nibedak_name LIKE '%" + nibedakName + "%'";
		} else {
			nibedakName = "";
		}
		if (constructionType.length() > 0) {
			constructionType = " AND construction_type = '" + constructionType + "'";
		} else {
			constructionType = "";
		}
		if (year.length() > 1) {
			year = " AND applicant_year = '" + year + "'";
		} else {
			year = "";
		}
		if (kittaNo.length() > 0) {
			kittaNo = " AND kitta_no LIKE '%" + kittaNo + "%'";
		} else {
			kittaNo = "";
		}
		if (wardNo.length() > 0) {
			wardNo = " AND new_ward_no ='" + wardNo + "'";
		} else {
			wardNo = "";
		}

		String sql = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 1);
		String userType = td.getUserType();

		sql = "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='"
				+ userType + "' ORDER  BY G.group_position,FG.group_position";
		message.list = message.db.getRecord(sql);
		String firstForm = "0";
		if (!message.list.isEmpty()) {
			message.map = (Map) message.list.get(0);
			firstForm = message.map.get("id").toString();
		}
		td.getUserTypeName(userType);
		String actionStatus = td.getActionStatus();
		String enterBy = "";
		if (userType.equalsIgnoreCase("D")) {
			enterBy = " enter_by='" + td.getUserId() + "' AND ";
		}
		sql = "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce("
				+ actionStatus + "action,'" + firstForm + "') \"yourForm\"," + actionStatus
				+ "status \"yourStatus\",forward_to_d_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\",coalesce(rejected_a,'N') \"rejectedA\",coalesce(rejected_b,'N') \"rejectedB\",coalesce(rejected_c,'N') \"rejectedC\",coalesce(rejected_d,'N') \"rejectedD\",coalesce(rejected_da,'N') AS \"rejectedAD\",coalesce(rejected_r,'N') \"rejectedR\",coalesce(rejected_e,'N') AS \"rejectedE\",coalesce(rejected_f,'N') \"rejectedF\",coalesce(rejected_g,'N') \"rejectedG\",coalesce(NAAMSARI_STATUS,'N') \"naamsariStatus\"  FROM building_permit_application where "
				+ enterBy + " forward_to_" + userType + "='I' AND coalesce(notice15_till_date,'" + DateConvert.today() + "')<='" + DateConvert.today()
				+ "' AND ID=COALESCE(" + applicationNo + ",ID) " + applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType
				+ " AND COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(COALESCE(enter_date,'" + DateConvert.now()
				+ "'),''),'YYYY-MM-DD') + INTERVAL '735 day')   order by forward_to_d_date desc";

		message.list = message.db.getRecord(sql);
		List l = new ArrayList();
		Long yourForm = 1l;
		for (int i = 0; i < message.list.size(); i++) {
			message.map = (Map) message.list.get(i);
			try {
				System.out.println("id " + message.map.get("applicationAction").toString());
				message.map.put("applicationAction", ApplicationForm.getById(message.map.get("applicationAction").toString()));
			} catch (Exception e) {
			}
			try {
				yourForm = Long.parseLong("0" + message.map.get("yourForm").toString());
				if (yourForm < 1) {
					yourForm = 1l;
				}
//                System.out.println("yourForm "+yourForm);
				message.map.put("yourForm", "" + yourForm + "");
				message.map.put("yourAction", ApplicationForm.getById(yourForm.toString()));
			} catch (Exception e) {
				message.map.put("yourAction", ApplicationForm.getById("1"));
				message.map.put("yourForm", "1");
			}
			try {
				applicationStatus = message.map.get("applicationStatus").toString();
				if (applicationStatus.equalsIgnoreCase("R")) {
					message.map.put("isRejected", "Y");
				} else {
					message.map.put("isRejected", "N");
				}
				if (!applicationStatus.equalsIgnoreCase("C")) {
					applicationStatus = "P";
				}
				message.map.put("applicationStatus", Message.applicationStatus(applicationStatus));

			} catch (Exception e) {
				message.map.put("applicationStatus", e.getMessage());
			}
			try {
				message.map.put("yourStatus", Message.status(message.map.get("yourStatus").toString()));
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

			/**
			 * To receive designer change status and name after changing designer for an
			 * applicationNo
			 */

			sql = "select C.application_no as \"applicationNo\" ,(select user_name from organization_user where login_id = P.enter_by) as \"newDesignerName\" from change_designer C,building_permit_application P where C.application_no=P.application_no and C.application_no = "
					+ Long.parseLong(message.map.get("applicantNo").toString()) + " order by C.change_date,C.id desc";
			list = message.db.getRecord(sql);
			if (!list.isEmpty()) {
				m = (Map) list.get(0);
				message.map.put("designerChangeStatus", "Y");
				message.map.put("newDesignerName", m.get("newDesignerName"));
			} else {
				message.map.put("designerChangeStatus", "N");
			}

			l.add(message.map);
		}

		return l;
	}
}
/*
 * if (userType.equalsIgnoreCase("D")) { } else if
 * (userType.equalsIgnoreCase("B")) { sql =
 * "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='B' ORDER  BY G.group_position,FG.group_position"
 * ; message.list = message.db.getRecord(sql); String firstForm = "0"; if
 * (!message.list.isEmpty()) { message.map = (Map) message.list.get(0);
 * firstForm = message.map.get("id").toString(); } sql =
 * "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(sub_engineer_action ,'"
 * + firstForm +
 * "') \"yourForm\",coalesce(sub_engineer_status,'P') \"yourStatus\",forward_to_b_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\",coalesce(rejected_a,'N') \"rejectedA\",coalesce(rejected_b,'N') \"rejectedB\",coalesce(rejected_c,'N') \"rejectedC\",coalesce(rejected_d,'N') \"rejectedD\",coalesce(rejected_da,'N') AS \"rejectedAD\",coalesce(rejected_r,'N') \"rejectedR\"  FROM building_permit_application where forward_to_b='I' AND coalesce(notice15_till_date,'"
 * + DateConvert.today() + "')<='" + DateConvert.today() + "' AND ID=COALESCE("
 * + applicationNo + ",ID) " + applicationStatus + nibedakName + year + kittaNo
 * + wardNo + constructionType +
 * " AND COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(COALESCE(enter_date,'"
 * + DateConvert.now() +
 * "'),''),'YYYY-MM-DD') + INTERVAL '735 day')  order by forward_to_b_date desc"
 * ; } else if (userType.equalsIgnoreCase("A")) { sql =
 * "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='A' ORDER  BY G.group_position,FG.group_position"
 * ; message.list = message.db.getRecord(sql); String firstForm = "0"; if
 * (!message.list.isEmpty()) { message.map = (Map) message.list.get(0);
 * firstForm = message.map.get("id").toString(); } sql =
 * "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(engineer_action, '"
 * + firstForm +
 * "') \"yourForm\",coalesce(engineer_status,'P') \"yourStatus\",forward_to_a_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\",coalesce(rejected_a,'N') \"rejectedA\",coalesce(rejected_b,'N') \"rejectedB\",coalesce(rejected_c,'N') \"rejectedC\",coalesce(rejected_d,'N') \"rejectedD\",coalesce(rejected_da,'N') AS \"rejectedAD\",coalesce(rejected_r,'N') \"rejectedR\"  FROM building_permit_application  where forward_to_a='I' AND coalesce(notice15_till_date,'"
 * + DateConvert.today() + "')<='" + DateConvert.today() +
 * "' AND application_status!='C'  AND ID=COALESCE(" + applicationNo + ",ID) " +
 * applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType
 * +
 * " AND COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(COALESCE(enter_date,'"
 * + DateConvert.now() +
 * "'),''),'YYYY-MM-DD') + INTERVAL '735 day')  order by forward_to_a_date desc"
 * ; } else if (userType.equalsIgnoreCase("C")) { sql =
 * "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='C' ORDER  BY G.group_position,FG.group_position"
 * ; message.list = message.db.getRecord(sql); String firstForm = "0"; if
 * (!message.list.isEmpty()) { message.map = (Map) message.list.get(0);
 * firstForm = message.map.get("id").toString(); } sql =
 * "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(chief_action,'"
 * + firstForm +
 * "') \"yourForm\",coalesce(chief_status,'P') \"yourStatus\",forward_to_c_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\",coalesce(rejected_a,'N') \"rejectedA\",coalesce(rejected_b,'N') \"rejectedB\",coalesce(rejected_c,'N') \"rejectedC\",coalesce(rejected_d,'N') \"rejectedD\",coalesce(rejected_da,'N') AS \"rejectedAD\",coalesce(rejected_r,'N') \"rejectedR\"  FROM building_permit_application where forward_to_c='I' AND coalesce(notice15_till_date,'"
 * + DateConvert.today() + "') <= '" + DateConvert.today() +
 * "' AND application_status!='C'  AND ID=COALESCE(" + applicationNo + ",ID) " +
 * applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType
 * +
 * " AND COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(COALESCE(enter_date,'"
 * + DateConvert.now() +
 * "'),''),'YYYY-MM-DD') + INTERVAL '735 day')  order by forward_to_c_date desc"
 * ; } else if (userType.equalsIgnoreCase("AD")) { sql =
 * "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='AD' ORDER  BY G.group_position,FG.group_position"
 * ; message.list = message.db.getRecord(sql); String firstForm = "0"; if
 * (!message.list.isEmpty()) { message.map = (Map) message.list.get(0);
 * firstForm = message.map.get("id").toString(); } sql =
 * "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(amin_action,'"
 * + firstForm +
 * "') \"yourForm\",coalesce(amin_status,'P') \"yourStatus\",forward_to_ad_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\",coalesce(rejected_a,'N') \"rejectedA\",coalesce(rejected_b,'N') \"rejectedB\",coalesce(rejected_c,'N') \"rejectedC\",coalesce(rejected_d,'N') \"rejectedD\",coalesce(rejected_da,'N') AS \"rejectedAD\",coalesce(rejected_r,'N') \"rejectedR\" FROM building_permit_application where forward_to_ad='I' AND coalesce(notice15_till_date,'"
 * + DateConvert.today() + "')<='" + DateConvert.today() +
 * "' AND application_status!='C' AND ID=COALESCE(" + applicationNo + ",ID) " +
 * applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType
 * +
 * " AND COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(COALESCE(enter_date,'"
 * + DateConvert.now() +
 * "'),''),'YYYY-MM-DD') + INTERVAL '735 day')  order by forward_to_ad_date desc"
 * ; } else if (userType.equalsIgnoreCase("R")) { sql =
 * "SELECT F.id AS id FROM form_name_master F,form_group_master G,form_group FG WHERE F.id=FG.form_id AND G.id=FG.group_id AND FG.user_type='R' ORDER  BY G.group_position,FG.group_position"
 * ; message.list = message.db.getRecord(sql); String firstForm = "0"; if
 * (!message.list.isEmpty()) { message.map = (Map) message.list.get(0);
 * firstForm = message.map.get("id").toString(); } sql =
 * "SELECT id,id AS \"applicantNo\",nibedak_name \"nibedakName\",applicant_ms \"applicantMs\",applicant_name \"applicantName\",applicant_address \"applicantAddress\",applicant_mobile_no \"applicantMobileNo\",application_action \"applicationAction\",application_action_by \"applicationActionBy\",application_status \"applicationStatus\",get_bsdate(applicant_date) \"applicantDate\",coalesce(rajasow_action,'"
 * + firstForm +
 * "') \"yourForm\",coalesce(rajasow_status,'P') \"yourStatus\",forward_to_r_remark \"forwardTo\",construction_type \"constructionType\",name_transafer_id AS \"nameTransaferId\",coalesce(rejected_a,'N') \"rejectedA\",coalesce(rejected_b,'N') \"rejectedB\",coalesce(rejected_c,'N') \"rejectedC\",coalesce(rejected_d,'N') \"rejectedD\",coalesce(rejected_da,'N') AS \"rejectedAD\",coalesce(rejected_r,'N') \"rejectedR\" FROM building_permit_application where forward_to_r='I' AND coalesce(notice15_till_date,'"
 * + DateConvert.today() + "')<='" + DateConvert.today() +
 * "' AND application_status!='C'  AND ID=COALESCE(" + applicationNo + ",ID) " +
 * applicationStatus + nibedakName + year + kittaNo + wardNo + constructionType
 * +
 * " AND COALESCE(IS_DISCARD,'N')!='Y' AND NOW()<= (TO_DATE(CONCAT(COALESCE(enter_date,'"
 * + DateConvert.now() +
 * "'),''),'YYYY-MM-DD') + INTERVAL '735 day')  order by forward_to_r_date desc"
 * ; } else { return message.respondWithError("Please provide user type " +
 * userType + " Can not access this feature!!"); }
 */
