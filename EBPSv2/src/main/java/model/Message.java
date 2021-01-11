package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HasRevisedGet;
import com.log.ApplicationActivity;
import com.log.ApplicationActivityPK;
import com.model.application.ApplicationHistory;
import com.model.application.ApplicationHistoryPK;
import com.model.utility.EmailSendingPanding;

public class Message {

	public model.DB db = new model.DB();
	public Map map;
	public List list;
	public com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
	public org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();
	String msg = "";
	int row;

	public String getEnterByStatus(String userType, int formId, Long applicationNo) {
		String sql = "";
		/**
		 * To run both in a single transaction.
		 */
		String[] multiSql = { "UPDATE application_status SET user_action= '" + formId + "',user_full_status='S' WHERE application_no=" + applicationNo
				+ " AND user_type='" + userType + "'", "UPDATE building_permit_application SET application_status='A',application_action='" + formId
						+ "',application_action_by='" + userType + "' WHERE id=" + applicationNo };
		try {
			db.saveMultiple(multiSql);
		} catch (Exception e) {
			System.out.println("Error = " + e.getMessage());
		}
		/*
		 * if (userType.equalsIgnoreCase("D")) { sql =
		 * "UPDATE building_permit_application SET designer_action='" + formId +
		 * "',designer_status='S',application_status='A',application_action='" + formId
		 * + "',application_action_by='" + userType + "' WHERE id=" + applicationNo;
		 * 
		 * } else if (userType.equalsIgnoreCase("C")) { sql =
		 * "UPDATE building_permit_application SET CHIEF_ACTION='" + formId +
		 * "',CHIEF_STATUS='S',application_status='A',application_action='" + formId +
		 * "',application_action_by='" + userType + "' WHERE id=" + applicationNo;
		 * 
		 * } else if (userType.equalsIgnoreCase("B")) { sql =
		 * "UPDATE building_permit_application SET SUB_engineer_ACTION='" + formId +
		 * "',SUB_engineer_STATUS='S',application_status='A',application_action='" +
		 * formId + "',application_action_by='" + userType + "' WHERE id=" +
		 * applicationNo;
		 * 
		 * } else if (userType.equalsIgnoreCase("A")) { sql =
		 * "UPDATE building_permit_application SET engineer_ACTION='" + formId +
		 * "',engineer_STATUS='S',application_status='A',application_action='" + formId
		 * + "',application_action_by='" + userType + "' WHERE id=" + applicationNo;
		 * 
		 * } else if (userType.equalsIgnoreCase("AD")) { sql =
		 * "UPDATE building_permit_application SET AMIN_ACTION='" + formId +
		 * "',AMIN_STATUS='S',application_status='A',application_action='" + formId +
		 * "',application_action_by='" + userType + "' WHERE id=" + applicationNo;
		 * 
		 * } else if (userType.equalsIgnoreCase("R")) { sql =
		 * "UPDATE building_permit_application SET RAJASOW_ACTION='" + formId +
		 * "',RAJASOW_STATUS='S',application_status='A',application_action='" + formId +
		 * "',application_action_by='" + userType + "' WHERE id=" + applicationNo;
		 * 
		 * } else if (userType.equalsIgnoreCase("E")) { sql =
		 * "UPDATE building_permit_application SET poste_action = '" + formId +
		 * "',poste_status = 'S',application_status='A',application_action='" + formId +
		 * "',application_action_by='" + userType + "' WHERE id=" + applicationNo;
		 * 
		 * } else if (userType.equalsIgnoreCase("F")) { sql =
		 * "UPDATE building_permit_application SET postf_action = '" + formId +
		 * "',postf_status = 'S',application_status='A',application_action='" + formId +
		 * "',application_action_by='" + userType + "' WHERE id=" + applicationNo;
		 * 
		 * } else if (userType.equalsIgnoreCase("G")) { sql =
		 * "UPDATE building_permit_application SET postg_action = '" + formId +
		 * "',postg_status = 'S',application_status='A',application_action='" + formId +
		 * "',application_action_by='" + userType + "' WHERE id=" + applicationNo;
		 * 
		 * }
		 */
		return sql;
	}

	public void setApplicationStatus(long applicationNo, String userType, String status, String pageNo) {
		String sql = "";
		if (status.equalsIgnoreCase("A")) {
			if (userType.equalsIgnoreCase("D")) {
				sql = "UPDATE building_permit_application SET designer_action='" + pageNo + "',designer_status='" + status + "',application_action='" + pageNo
						+ "',application_action_by='D',application_status='" + status + "' WHERE id='" + applicationNo + "'";
			} else if (userType.equalsIgnoreCase("B")) {
				sql = "UPDATE building_permit_application SET sub_engineer_action='" + pageNo + "',sub_engineer_status='" + status + "',application_action='"
						+ pageNo + "',application_action_by='B',application_status='" + status + "' WHERE id='" + applicationNo + "'";
			} else if (userType.equalsIgnoreCase("A")) {
				sql = "UPDATE building_permit_application SET engineer_action='" + pageNo + "',engineer_status='" + status + "',application_action='" + pageNo
						+ "',application_action_by='A',application_status='" + status + "' WHERE id='" + applicationNo + "'";
			} else if (userType.equalsIgnoreCase("R")) {
				sql = "UPDATE building_permit_application SET rajasow_action='" + pageNo + "',rajasow_status='" + status + "',application_action='" + pageNo
						+ "',application_action_by='R',application_status='" + status + "' WHERE id='" + applicationNo + "'";
			} else if (userType.equalsIgnoreCase("C")) {
				sql = "UPDATE building_permit_application SET chief_action='" + pageNo + "',chief_status='" + status + "',application_action='" + pageNo
						+ "',application_action_by='C',application_status='" + status + "' WHERE id='" + applicationNo + "'";
			} else if (userType.equalsIgnoreCase("AD")) {
				sql = "UPDATE building_permit_application SET amin_action='" + pageNo + "',amin_status='" + status + "',application_action='" + pageNo
						+ "',application_action_by='AD',application_status='" + status + "' WHERE id='" + applicationNo + "'";
			} else if (userType.equalsIgnoreCase("E")) {
				sql = "UPDATE building_permit_application SET poste_action='" + pageNo + "',poste_status='" + status + "',application_action='" + pageNo
						+ "',application_action_by='E',application_status='" + status + "' WHERE id='" + applicationNo + "'";
			} else if (userType.equalsIgnoreCase("F")) {
				sql = "UPDATE building_permit_application SET postf_action='" + pageNo + "',postf_status='" + status + "',application_action='" + pageNo
						+ "',application_action_by='F',application_status='" + status + "' WHERE id='" + applicationNo + "'";
			} else if (userType.equalsIgnoreCase("G")) {
				sql = "UPDATE building_permit_application SET postg_action='" + pageNo + "',postg_status='" + status + "',application_action='" + pageNo
						+ "',application_action_by='G',application_status='" + status + "' WHERE id='" + applicationNo + "'";
			}
			db.save(sql);
		}
	}

	public boolean checkSaveStatus(String userType, String formId, long applicationNo) {
		CheckSaveStatus checksavestatus = new CheckSaveStatus();
		if (checksavestatus.get(userType, formId, applicationNo)) {
			return true;
		}
		setMsg(checksavestatus.getMsg());
		return false;
	}

	public void comment(long applicationNo, String commentBy, String userType, int formId, String comment, String remark, String enterBy, String forwardByName,
			String forwardToName) {
		new ApplicationReject().doAction(applicationNo, commentBy, userType, String.valueOf(formId), comment, remark, forwardByName, forwardToName);
	}

	public Object getComment(String applicationNo, float page) {
		return getComment(applicationNo, page + "");
	}

	public Object getComment(Long applicationNo, float page) {
		return getComment(applicationNo + "", page);
	}

	public Object getComment(String applicationNo, String page) {
		List list = new ArrayList();
		try {
			Session session = model.HibernateUtil.getSession();
			Transaction tr = session.beginTransaction();
			try {
				list = session.createQuery("from ApplicationsComments where pk.applicationNo='" + applicationNo + "' and page='" + page
						+ "' order by pk.commentDate desc").list();
				tr.commit();
			} catch (Exception e) {
				tr.rollback();
			}
			try {
				session.close();
			} catch (HibernateException e) {
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public void setEmailSending(long applicationNo, String email, String dateTime, String subject, String body, String enterBy) {
		EmailSendingPanding obj = new EmailSendingPanding(applicationNo, email, dateTime, subject, body, "Y", enterBy);
		Session session = model.HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		try {
			session.save(obj);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
		}
		try {
			session.close();
		} catch (Exception e) {
		}
	}

	public void setHistory(Long applicationNo, String userType, String page, String status, String userName) {
		ApplicationHistory obj = new ApplicationHistory();
		obj.setPk(new ApplicationHistoryPK(applicationNo, userType, page, new Date()));
		obj.setStatus(status);
		obj.setEnterBy(userName);
		Session session = model.HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		try {
			session.save(obj);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
		}
		try {
			session.close();
		} catch (HibernateException e) {
		}
	}

	public void setHistory(String applicationNo, String userType, String page, String status, String userName) {

		setHistory(Long.parseLong(applicationNo), userType, page, status, userName);
	}

	public Object getHistory(Long applicationNo, String page) {
		List list = new ArrayList();
		try {
			Session session = model.HibernateUtil.getSession();
			Transaction tr = session.beginTransaction();
			try {
				list = session.createQuery("from ApplicationHistory where pk.applicationNo='" + applicationNo + "' and pk.pageNo='" + page
						+ "' order by pk.actionDate desc").list();
				tr.commit();
			} catch (Exception e) {
				tr.rollback();
			}
			try {
				session.close();
			} catch (HibernateException e) {
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public Object getHistory(String applicationNo, String page) {
		return getHistory(Long.parseLong(applicationNo), page);
	}

	public String buildingClass(String classId) {
		if (classId.equalsIgnoreCase("A")) {
			return "इन्टरनेशनल स्टेट अफ आर्ट";
		} else if (classId.equalsIgnoreCase("B")) {
			return "प्रोफेसनली इन्जिनियर्ड विल्डिङ";
		} else if (classId.equalsIgnoreCase("C")) {
			return "म्यान्डेटरी रुल्स अफ थम्ब";
		} else if (classId.equalsIgnoreCase("D")) {
			return "ग्रामिण क्षेत्रको लागि भवन निर्देशिका";
		}
		return "Invalid";

	}

	public String today() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date());
	}

	public String[] jsonDataToStringArray(String jsonData) {
		String val[] = new String[2];
		int arrayStart = jsonData.indexOf("[");
		int arrayEnd = jsonData.lastIndexOf("]") + 1;
		int len = jsonData.length();
		String array = jsonData.substring(arrayStart, arrayEnd);
		String jsconStart = jsonData.substring(0, arrayStart);
		String jsconEnd = jsonData.substring(arrayEnd, len);
		val[1] = (array);
		val[0] = (jsconStart + "0" + jsconEnd);
		return val;
	}

	public String filePath(String contextPath) {
		list = db.getRecord("SELECT coalesce(FILE_LOCATION,'') \"fileLocation\" FROM organization_master");
		if (list.isEmpty()) {
			return "Please set file storage location";
		}
		map = (Map) list.get(0);
		return map.get("fileLocation").toString();

//        return "E:/project/ERP/EBPS/src/main/webapp/";
	}

	public static String exceptionMsg(Exception e) {
		try {
			return e.getCause().getMessage();
		} catch (Exception ex) {
		}
		return e.getMessage();
	}

	public Object respondWithError(Object message) {
		Map m = new HashMap();
		Map m1 = new HashMap();
		m1.put("message", message);
		m.put("error", m1);
		return m;
	}

	public Object respondWithError(String message, Object data) {
		Map m = new HashMap();
		Map m1 = new HashMap();
		m1.put("message", message);
		m.put("error", m1);
		m.put("data", data);
		return m;
	}

	public Object respondWithMessage(Object message) {
		Map m = new HashMap();
		m.put("message", message);
		return m;
	}

	public Object respondWithMessage(String message, Object data) {
		Map m = new HashMap();
		m.put("message", message);
		m.put("data", data);
		return m;

	}

	public static String status(String code) {
		try {
			if (code.equalsIgnoreCase("A")) {
				return "स्वीकृत भएको";
			} else if (code.equalsIgnoreCase("R")) {
				return "अस्वीकृत गरिएको";
			} else if (code.equalsIgnoreCase("P")) {
				return "कारबाही चलिरहेको";
			} else if (code.equalsIgnoreCase("C")) {
				return "कारबाही पूर्ण भएको ";
			} else {
				return "दर्ता गरिएको";
			}
		} catch (Exception e) {
		}
		return "दर्ता गरिएको";
	}

	public static String applicationStatus(String code) {
		try {
			if (code.equalsIgnoreCase("A")) {
				return "स्वीकृत भएको";
			} else if (code.equalsIgnoreCase("R")) {
				return "अस्वीकृत गरिएको";
			} else if (code.equalsIgnoreCase("P")) {
				return "चलिरहेको";
			} else if (code.equalsIgnoreCase("C")) {
				return "पूर्ण भएको ";
			} else {
				return "दर्ता गरिएको";
			}
		} catch (Exception e) {
		}
		return "दर्ता गरिएको";
	}

	public String reverseStatus(String code) {
		try {
			if (code.equalsIgnoreCase("C")) {
				return "C";
			} else {
				return "";
			}
		} catch (Exception e) {
		}
		return "";
	}

	public String getGroupType(String constructionType) {
		try {
			/*
			 * if (constructionType.equalsIgnoreCase("नयाँ निर्माण")) { return "1"; } else
			 * if (constructionType.equalsIgnoreCase("पुरानो घर")) { return "2"; } else if
			 * (constructionType.equalsIgnoreCase("तला थप")) { return "3"; } else return
			 * "1";
			 */
			if (constructionType.equalsIgnoreCase("1") || constructionType.equalsIgnoreCase("2") || constructionType.equalsIgnoreCase("3")) {
				return constructionType;
			} else {
				return "1";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return constructionType;
	}

	public String getGroupType1(String constructionType, Long nameTransferId) {
		try {
			List l = new ArrayList<>(), fields = new ArrayList<>();
			Map m = new HashMap<>();
			String formId = "38", approvalStatus = "";

			String sql = "SELECT user_type \"userType\",coalesce(approval_status,'N') \"approvalStatus\" FROM form_permissions WHERE form_id=" + formId;
			l = db.getRecord(sql);
			for (Object o : l) {
				m = (Map) o;
				approvalStatus = m.get("approvalStatus").toString();
				if (approvalStatus.equalsIgnoreCase("Y")) {
					fields.add(m.get("userType").toString());
				}
			}

			/*
			 * String sql = "select * from form_name_master where id=" + formId; l =
			 * db.getRecord(sql); m = (Map) l.get(0); String aminApproval = "",
			 * chiefApproval = "", engrApproval = "", posteAppr = "", postfAppr = "",
			 * postgAppr = "", rajasowAppr = "", subErAppr = ""; try { aminApproval =
			 * m.get("amin_approval").toString(); } catch (Exception e) { aminApproval =
			 * "N"; }
			 * 
			 * try { chiefApproval = m.get("chief_approval").toString(); } catch (Exception
			 * e) { chiefApproval = "N"; }
			 * 
			 * try { engrApproval = m.get("engr_approval").toString(); } catch (Exception e)
			 * { engrApproval = "N"; }
			 * 
			 * try { posteAppr = m.get("poste_approval").toString(); } catch (Exception e) {
			 * posteAppr = "N"; }
			 * 
			 * try { postfAppr = m.get("postf_approval").toString(); } catch (Exception e) {
			 * postfAppr = "N"; }
			 * 
			 * try { postgAppr = m.get("postg_approval").toString(); } catch (Exception e) {
			 * postgAppr = "N"; }
			 * 
			 * try { rajasowAppr = m.get("rajasow_approval").toString(); } catch (Exception
			 * e) { rajasowAppr = "N"; }
			 * 
			 * try { subErAppr = m.get("sub_engr_approval").toString(); } catch (Exception
			 * e) { subErAppr = "N"; }
			 */

			/*
			 * if (aminApproval.equalsIgnoreCase("Y")) {
			 * fields.add("COALESCE(amini_status,'P') AS \"aminiStatus\" "); } if
			 * (chiefApproval.equalsIgnoreCase("Y")) {
			 * fields.add("COALESCE(chief_status,'P') AS \"chiefStatus\" "); } if
			 * (engrApproval.equalsIgnoreCase("Y")) {
			 * fields.add("COALESCE(er_status,'P') AS \"erStatus\" "); } if
			 * (posteAppr.equalsIgnoreCase("Y")) {
			 * fields.add("COALESCE(e_status,'P') AS \"eStatus\" "); } if
			 * (postfAppr.equalsIgnoreCase("Y")) {
			 * fields.add("COALESCE(f_status,'P') AS \"fStatus\" "); } if
			 * (postgAppr.equalsIgnoreCase("Y")) {
			 * fields.add("COALESCE(g_status,'P') AS \"gStatus\" "); } if
			 * (rajasowAppr.equalsIgnoreCase("Y")) {
			 * fields.add("COALESCE(rw_status,'P') AS \"rwStatus\" "); } if
			 * (subErAppr.equalsIgnoreCase("Y")) {
			 * fields.add("COALESCE(ser_status,'P') AS \"serStatus\" "); }
			 */

			sql = "SELECT id \"formId\" FROM form_name_master WHERE table_id = (SELECT id FROM ebps_tables WHERE table_name like 'building_build_certificate')";
			l = db.getRecord(sql);
			m = (Map) l.get(0);

			// false->not approved,true->approved
			boolean res = true, temp = false;

			if (!fields.isEmpty()) {
				List l2 = new ArrayList<>();
				Map m2 = new HashMap<>();
				for (Object s : fields) {
					String sql2 = "SELECT coalesce(status,'P') \"status\" FROM status WHERE name_transfer_id=" + nameTransferId + " AND form_id = '" + m.get(
							"formId").toString() + "' AND user_type='" + s + "'";
					l2 = db.getRecord(sql2);
					m2 = (Map) l2.get(0);
					if (m2.get("status").toString().equalsIgnoreCase("A")) {
						temp = true;
					} else {
						temp = false;
					}
					res = res && temp;
				}
			}

			/*
			 * if (!fields.isEmpty()) { for (int j = 0; j < fields.size(); j++) { List l2 =
			 * new ArrayList<>(); Map m2 = new HashMap<>(); String sql2 = "SELECT " +
			 * fields.get(j).toString() + "FROM building_build_certificate WHERE ID= " +
			 * nameTransferId; l2 = db.getRecord(sql2); if (!l2.isEmpty()) { m2 = (Map)
			 * l2.get(0); for (Object key : m2.keySet()) { System.out.println("key " + key);
			 * if (((String) m2.get(key)).equalsIgnoreCase("A")) { temp = true; } else {
			 * temp = false; } } } res = res && temp; } }
			 */

			if (nameTransferId != null) {
				if (nameTransferId != 0l && !res) {
					return "N";
				}
			}

			if (constructionType.equalsIgnoreCase("1") || constructionType.equalsIgnoreCase("2") || constructionType.equalsIgnoreCase("3")) {
				return constructionType;
			} else {
				return "1";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return constructionType;
	}

	public void checkNaamsariCompleted(Long applicationNo) {
		List l = new ArrayList(), fields = new ArrayList<>();
		Map m = new HashMap<>();
		String lastForm = "38", sql = "";
		sql = "select * from form_name_master where id=" + lastForm;
		list = db.getRecord(sql);
		m = (Map) list.get(0);
		String aminApproval = "", chiefApproval = "", engrApproval = "", posteAppr = "", postfAppr = "", postgAppr = "", rajasowAppr = "", subErAppr = "";
		try {
			aminApproval = m.get("amin_approval").toString();
		} catch (Exception e) {
			aminApproval = "N";
		}

		try {
			chiefApproval = m.get("chief_approval").toString();
		} catch (Exception e) {
			chiefApproval = "N";
		}

		try {
			engrApproval = m.get("engr_approval").toString();
		} catch (Exception e) {
			engrApproval = "N";
		}

		try {
			posteAppr = m.get("poste_approval").toString();
		} catch (Exception e) {
			posteAppr = "N";
		}

		try {
			postfAppr = m.get("postf_approval").toString();
		} catch (Exception e) {
			postfAppr = "N";
		}

		try {
			postgAppr = m.get("postg_approval").toString();
		} catch (Exception e) {
			postgAppr = "N";
		}

		try {
			rajasowAppr = m.get("rajasow_approval").toString();
		} catch (Exception e) {
			rajasowAppr = "N";
		}

		try {
			subErAppr = m.get("sub_engr_approval").toString();
		} catch (Exception e) {
			subErAppr = "N";
		}

		if (aminApproval.equalsIgnoreCase("Y")) {
			fields.add("COALESCE(amini_status,'P') AS \"aminiStatus\" ");
		}
		if (chiefApproval.equalsIgnoreCase("Y")) {
			fields.add("COALESCE(chief_status,'P') AS \"chiefStatus\" ");
		}
		if (engrApproval.equalsIgnoreCase("Y")) {
			fields.add("COALESCE(er_status,'P') AS \"erStatus\" ");
		}
		if (posteAppr.equalsIgnoreCase("Y")) {
			fields.add("COALESCE(e_status,'P') AS \"eStatus\" ");
		}
		if (postfAppr.equalsIgnoreCase("Y")) {
			fields.add("COALESCE(f_status,'P') AS \"fStatus\" ");
		}
		if (postgAppr.equalsIgnoreCase("Y")) {
			fields.add("COALESCE(g_status,'P') AS \"gStatus\" ");
		}
		if (rajasowAppr.equalsIgnoreCase("Y")) {
			fields.add("COALESCE(rw_status,'P') AS \"rwStatus\" ");
		}
		if (subErAppr.equalsIgnoreCase("Y")) {
			fields.add("COALESCE(ser_status,'P') AS \"serStatus\" ");
		}

		boolean res = true, temp = false;
		if (!fields.isEmpty()) {
			for (int i = 0; i < fields.size(); i++) {
				sql = "SELECT " + fields.get(i).toString()
						+ " FROM building_build_certificate WHERE id=(SELECT name_transafer_id FROM building_permit_application WHERE id='" + applicationNo
						+ "')";
				l = db.getRecord(sql);
				if (!l.isEmpty()) {
					map = (Map) l.get(0);
					for (Object key : map.keySet()) {
						if (map.get(key).toString().equalsIgnoreCase("A")) {
							temp = true;
						} else {
							temp = false;
						}
					}
				}
				res = res && temp;
			}
		}

		if (res) {
			sql = "UPDATE building_permit_application SET NAAMSARI_STATUS = 'C' WHERE id=" + applicationNo;
			db.save(sql);
		}

	}

	public String hasRevisedForm(String userType, Long applicationNo) {
		return new HasRevisedGet().get(userType, applicationNo);

	}

	public void checkCompletedTask(Long applicationNo) {
		String sql = "SELECT coalesce(construction_type,'1') \"constructionType\" FROM building_permit_application WHERE application_no='" + applicationNo
				+ "'";
		list = db.getRecord(sql);
		map = (Map) list.get(0);
		String lastForm = "", userType, approvalStatus;
		String constructionType = getGroupType(map.get("constructionType").toString());
		System.out.println("construction_type:" + constructionType);
		if (constructionType.equalsIgnoreCase("2")) {
			lastForm = "50";
		} else {
			lastForm = "43";
		}
		sql = "SELECT table_name \"tableName\" FROM ebps_tables WHERE id=(SELECT table_id FROM form_name_master WHERE id='" + lastForm + "')";
		list = db.getRecord(sql);
		map = (Map) list.get(0);
		String tableName = map.get("tableName").toString();

		sql = "SELECT coalesce(approval_status,'N') \"approvalStatus\",user_type \"userType\" FROM form_permissions WHERE form_id=" + lastForm;
		list = db.getRecord(sql);
		List temp = new ArrayList<>();
		Map tempMap = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			map = (Map) list.get(i);
			approvalStatus = map.get("approvalStatus").toString();
			userType = map.get("userType").toString();
			sql = "SELECT coalesce(status,'P') status FROM status WHERE application_no='" + applicationNo + "' AND form_id='" + lastForm + "' AND user_type='"
					+ userType + "'";
			tempMap = (Map) db.getRecord(sql).get(0);
			String userStatus = tempMap.get("status").toString();
			if (userType.equalsIgnoreCase("C") && approvalStatus.equalsIgnoreCase("Y")) {
				if (userStatus.equalsIgnoreCase("A")) {
					sql = "UPDATE building_permit_application SET application_status='C' WHERE id=" + applicationNo;
					db.save(sql);
				}
				break;
			} else {
				if (userType.equalsIgnoreCase("A") && approvalStatus.equalsIgnoreCase("Y")) {
					if (userStatus.equalsIgnoreCase("A")) {
						sql = "UPDATE building_permit_application SET application_status='C' WHERE id=" + applicationNo;
						db.save(sql);
					}
					break;
				} else {
					if (userType.equalsIgnoreCase("B") && approvalStatus.equalsIgnoreCase("Y")) {
						if (userStatus.equalsIgnoreCase("A")) {
							sql = "UPDATE building_permit_application SET application_status='C' WHERE id=" + applicationNo;
							db.save(sql);
						}
						break;
					}
				}
			}
		}

		/*sql = "SELECT coalesce(amin_approval,'N') \"aminApproval\",coalesce(chief_approval,'N') \"chiefApproval\",coalesce(designer_approval,'N')  \"designerApproval\",coalesce(engr_approval,'N')  \"engrApproval\",coalesce(rajasow_approval,'N') \"rajasowApproval\",coalesce(sub_engr_approval,'N') \"subEngrApproval\" FROM form_name_master where id='"
				+ lastForm + "'";
		list = db.getRecord(sql);
		map = (Map) list.get(0);
		String chiefApproval = map.get("chiefApproval").toString();
		String engineerApproval = map.get("engrApproval").toString();
		String subEngineerApproval = map.get("subEngrApproval").toString();
		if (chiefApproval.equalsIgnoreCase("Y")) {
			sql = "SELECT coalesce(chief_status,'P') \"chiefStatus\" from " + tableName + " where application_no='" + applicationNo + "'";
			list = db.getRecord(sql);
			map = (Map) list.get(0);
			String chiefStatus = map.get("chiefStatus").toString();
			if (chiefStatus.equalsIgnoreCase("A")) {
				sql = "UPDATE building_permit_application SET application_status='C' WHERE id=" + applicationNo;
				db.save(sql);
				map = new HashMap();
				map.put("message", "Process Completed.");
			}

		} else {
			if (engineerApproval.equalsIgnoreCase("Y")) {
				sql = "SELECT coalesce(er_status,'P') \"engineerStatus\" from " + tableName + " where application_no='" + applicationNo + "'";
				list = db.getRecord(sql);
				map = (Map) list.get(0);
				String engineerStatus = map.get("engineerStatus").toString();
				if (engineerStatus.equalsIgnoreCase("A")) {
					sql = "UPDATE building_permit_application SET application_status='C' WHERE id=" + applicationNo;
					db.save(sql);
					map = new HashMap();
					map.put("message", "Process Completed.");
				}
			} else {
				if (subEngineerApproval.equalsIgnoreCase("Y")) {
					sql = "SELECT coalesce(ser_status,'P') \"subEngineerStatus\" from " + tableName + " where application_no='" + applicationNo + "'";
					list = db.getRecord(sql);
					map = (Map) list.get(0);
					String subEngineerStatus = map.get("subEngineerStatus").toString();
					if (subEngineerStatus.equalsIgnoreCase("A")) {
						sql = "UPDATE building_permit_application SET application_status='C' WHERE id=" + applicationNo;
						db.save(sql);
						map = new HashMap();
						map.put("message", "Process Completed.");
					}

				} else {
					map = new HashMap();
					map.put("message", "Please select the user to approve form");
				}
			}
		}*/
	}

	public void setApplicationActivity(String api, String loginUser, String method, String activity) {
		if (method.equalsIgnoreCase("GET")) {
			ApplicationActivity obj = new ApplicationActivity();
			ApplicationActivityPK objPK = new ApplicationActivityPK();
			obj.setActivity(activity);
			objPK.setApi(api);
			objPK.setMethod(method);
			objPK.setLoginBy(loginUser);
			objPK.setAccessTime(DateConvert.toDate(DateConvert.today()));
			obj.setPk(objPK);
			DaoApplicationActivity da = new DaoImpApplicationActivity();
			da.save(obj);
		}
	}

	public String classGroup(String constructionType, String buildingClass) {
		String sql = "SELECT ignored_form \"ignoredForm\" FROM check_class_group where construction_type='" + constructionType + "' AND building_class='"
				+ buildingClass + "'";
		list = db.getRecord(sql);
		String ignoredForm = "-1";
		for (int i = 0; i < list.size(); i++) {
			map = (Map) list.get(i);
			ignoredForm = ignoredForm + "," + map.get("ignoredForm").toString();
		}
		return ignoredForm;
	}

	public Object getMsg() {
		return respondWithError(msg);
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String userTypeToactionColumn(String userType) {
		if (userType.equalsIgnoreCase("A")) {
			return "engineer_action";
		} else if (userType.equalsIgnoreCase("B")) {
			return "sub_engineer_action";
		} else if (userType.equalsIgnoreCase("C")) {
			return "chief_action";
		} else if (userType.equalsIgnoreCase("D")) {
			return "designer_action";
		} else if (userType.equalsIgnoreCase("AD")) {
			return "amin_action";
		} else if (userType.equalsIgnoreCase("R")) {
			return "rajasow_action";
		} else if (userType.equalsIgnoreCase("E")) {
			return "poste_action";
		} else if (userType.equalsIgnoreCase("F")) {
			return "postf_action";
		} else if (userType.equalsIgnoreCase("G")) {
			return "postg_status";
		}
		return "Invalid Type";
	}

}
