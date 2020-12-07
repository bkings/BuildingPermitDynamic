package com;

import java.util.List;
import java.util.Map;

import model.DB;

public class HasRevisedGet {
	
	public String get(String userType, Long applicationNo) {
		DB db = new DB();
		Long form;
		String sql;
		List list,l2;
		Map map,m2;
		String hasReviseStatus, tableName;
		boolean billStatus;
		String hasRevisedIdList = "-1";
		
		/**
		 * get all forms that has samsodhan for that applicationNo.
		 */
//		sql = "SELECT form_id as \"formId\" FROM has_revised_form_setup WHERE application_no=" + applicationNo + " AND type != 'Bill'";
		sql = "SELECT distinct(has_revised) FROM has_revised";
		list = db.getRecord(sql);
		for (int i = 0; i < list.size(); i++) {
			map = (Map) list.get(i);
			form = Long.parseLong(map.get("formId").toString());
			sql = "SELECT coalesce(has_revised,'') \"hasRevisedStatus\" FROM status WHERE application_no=" + applicationNo + " AND form_id=" + form
					+ " AND user_type=" + userType + "";
			l2 = db.getRecord(sql);
			if (!l2.isEmpty()) {
				map = (Map) l2.get(0);
				hasReviseStatus = map.get("hasReviseStatus").toString();
				billStatus = true;
				sql = "SELECT application_no FROM sansodhan_bill_vuktani WHERE application_no=" + applicationNo;
				if (db.getRecord(sql).size() > 0) {
					billStatus = false;
				}
				if (hasReviseStatus.equalsIgnoreCase("N")) {
					sql = "SELECT ignored_form \"hasRevised\" FROM has_revised WHERE status='N' AND user_type='" + userType + "' AND has_revised='" + form
							+ "'";
					l2 = db.getRecord(sql);
					for (int k = 0; k < l2.size(); k++) {
						map = (Map) l2.get(k);
						hasRevisedIdList = hasRevisedIdList + "," + map.get("hasRevised").toString();
					}
				} else if (billStatus && hasReviseStatus.equalsIgnoreCase("Y")) {
					sql = "SELECT ignored_form \"hasRevised\" FROM has_revised WHERE status='Y' AND  user_type='" + userType + "' AND has_revised='" + form
							+ "'";
					l2 = db.getRecord(sql);
					for (int k = 0; k < l2.size(); k++) {
						map = (Map) list.get(k);
						hasRevisedIdList = hasRevisedIdList + "," + map.get("hasRevised").toString();
					}
				}
			}
		}

		return hasRevisedIdList;
	}

	public String getX(String userType, Long applicationNo) {
		DB db = new DB();
		String sql;
		List list;
		Map map;
		String hasReviseStatus;
		boolean billStatus;
		String hasRevisedIdList = "-1";
		
		try {
			sql = "SELECT coalesce(has_revised,'') \"hasReviseStatus\" FROM prabhidik_pratibedhan_pesh WHERE application_no='" + applicationNo + "';";
			list = db.getRecord(sql);
			if (!list.isEmpty()) {
				map = (Map) list.get(0);
				hasReviseStatus = map.get("hasReviseStatus").toString();
				billStatus = true;
				sql = "SELECT application_no FROM sansodhan_bill_vuktani WHERE application_no=" + applicationNo;
				if (db.getRecord(sql).size() > 0) {
					billStatus = false;
				}
				if (hasReviseStatus.equalsIgnoreCase("N")) {
					sql = "SELECT ignored_form \"hasRevised\" FROM has_revised WHERE status='N' AND user_type='" + userType + "' AND has_revised=17";
					list = db.getRecord(sql);
					for (int i = 0; i < list.size(); i++) {
						map = (Map) list.get(i);
						hasRevisedIdList = hasRevisedIdList + "," + map.get("hasRevised").toString();
					}
				} else if (billStatus && hasReviseStatus.equalsIgnoreCase("Y")) {
					sql = "SELECT ignored_form \"hasRevised\" FROM has_revised WHERE status='Y' AND  user_type='" + userType + "' AND has_revised=17";
					list = db.getRecord(sql);
					for (int i = 0; i < list.size(); i++) {
						map = (Map) list.get(i);
						hasRevisedIdList = hasRevisedIdList + "," + map.get("hasRevised").toString();
					}
				} 
				/*else if (hasReviseStatus.equalsIgnoreCase("Y")) {
					if(!billStatus) {
						sql = "SELECT ignored_form \"hasRevised\" FROM has_revised WHERE status='Y' AND  user_type='" + userType + "' AND has_revised=17 AND ignored_form NOT IN (SELECT ignored_form FROM has_revised where status='T' and user_type='"+userType+"' and has_revised=17)";
					}else {
						sql = "SELECT ignored_form \"hasRevised\" FROM has_revised WHERE status='Y' AND  user_type='" + userType + "' AND has_revised=17";
					}
					list = db.getRecord(sql);
					for (int i = 0; i < list.size(); i++) {
						map = (Map) list.get(i);
						hasRevisedIdList = hasRevisedIdList + "," + map.get("hasRevised").toString();
					}
				}*/
			}
		} catch (Exception e) {
		}

		// form 17 end
		try {
			sql = "SELECT coalesce(has_revised,'') \"hasReviseStatus\" FROM plinth_level_owner_representation WHERE application_no='" + applicationNo + "';";
			list = db.getRecord(sql);
			if (!list.isEmpty()) {
				map = (Map) list.get(0);
				hasReviseStatus = map.get("hasReviseStatus").toString();
				billStatus = true;
				sql = "SELECT application_no FROM pahilocharan_bill_vuktani WHERE application_no=" + applicationNo;
				if (db.getRecord(sql).size() > 0) {
					billStatus = false;
				}
				if (hasReviseStatus.equalsIgnoreCase("N")) {
					sql = "SELECT ignored_form \"hasRevised\" FROM has_revised WHERE status='N' AND user_type='" + userType + "' AND has_revised=23";
					list = db.getRecord(sql);
					for (int i = 0; i < list.size(); i++) {
						map = (Map) list.get(i);
						hasRevisedIdList = hasRevisedIdList + "," + map.get("hasRevised").toString();
					}
				} else if (billStatus && hasReviseStatus.equalsIgnoreCase("Y")) {
					sql = "SELECT ignored_form \"hasRevised\" FROM has_revised WHERE status='Y' AND  user_type='" + userType + "' AND has_revised=23";
					list = db.getRecord(sql);
					for (int i = 0; i < list.size(); i++) {
						map = (Map) list.get(i);
						hasRevisedIdList = hasRevisedIdList + "," + map.get("hasRevised").toString();
					}
				}
			}
		} catch (Exception e) {
		}

		// form 23 end
		try {
			sql = "SELECT coalesce(has_revised,'N') \"hasReviseStatus\" FROM dosrocharan_supervisor WHERE application_no='" + applicationNo + "';";
			list = db.getRecord(sql);
			if (!list.isEmpty()) {
				map = (Map) list.get(0);
				hasReviseStatus = map.get("hasReviseStatus").toString();
				billStatus = true;
				sql = "SELECT application_no FROM dosrocharan_bill_vuktani WHERE application_no=" + applicationNo;
				if (db.getRecord(sql).size() > 0) {
					billStatus = false;
				}
				if (hasReviseStatus.equalsIgnoreCase("N")) {
					sql = "SELECT ignored_form \"hasRevised\" FROM has_revised WHERE status='N' AND user_type='" + userType + "' AND has_revised=29";
					list = db.getRecord(sql);
					for (int i = 0; i < list.size(); i++) {
						map = (Map) list.get(i);
						hasRevisedIdList = hasRevisedIdList + "," + map.get("hasRevised").toString();
					}
//				} else if (billStatus && hasReviseStatus.equalsIgnoreCase("Y")) {
				} else if (hasReviseStatus.equalsIgnoreCase("Y")) {
					if(!billStatus) {
						sql = "SELECT ignored_form \"hasRevised\" FROM has_revised WHERE status='Y' AND  user_type='" + userType + "' AND has_revised=29 AND ignored_form NOT IN (SELECT ignored_form FROM has_revised where status='T' and user_type='"+userType+"' and has_revised=29)";
					} else {
						sql = "SELECT ignored_form \"hasRevised\" FROM has_revised WHERE status='Y' AND  user_type='" + userType + "' AND has_revised=29";
					}
					list = db.getRecord(sql);
					for (int i = 0; i < list.size(); i++) {
						map = (Map) list.get(i);
						hasRevisedIdList = hasRevisedIdList + "," + map.get("hasRevised").toString();
					}
//				} else if (billStatus && hasReviseStatus.equalsIgnoreCase("T")) {
				} else if (hasReviseStatus.equalsIgnoreCase("T")) { // this case added later
					if(!billStatus) {
						sql = "SELECT ignored_form \"hasRevised\" FROM has_revised WHERE status='T' AND  user_type='" + userType + "' AND has_revised=29 AND ignored_form NOT IN (SELECT ignored_form FROM has_revised where status='Y' and user_type='"+userType+"' and has_revised=29)";
					} else {
						sql = "SELECT ignored_form \"hasRevised\" FROM has_revised WHERE status='T' AND  user_type='" + userType + "' AND has_revised=29";
					}
					list = db.getRecord(sql);
					for (int i = 0; i < list.size(); i++) {
						map = (Map) list.get(i);
						hasRevisedIdList = hasRevisedIdList + "," + map.get("hasRevised").toString();
					}
				}
			}
		} catch (Exception e) {
		}
		// form 29 end

		return hasRevisedIdList;
	}
	
}
