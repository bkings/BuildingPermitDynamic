package com.controller.rest.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.JWTToken;
import com.model.application.ApplicationApproved;
import com.model.processing.DosrocharanBillVuktani;
import com.model.processing.PahilocharanBillVuktani;
import com.model.processing.SansodhanBillVuktani;
import com.service.processing.DosrocharanBillVuktaniService;
import com.service.processing.PahilocharanBillVuktaniService;
import com.service.processing.SansodhanBillVuktaniService;

import model.DB;
import model.DateConvert;
import model.Message;

@RestController
@CrossOrigin
@RequestMapping("api/Processing/Skip")
public class SkipCaseRestController {

	@Autowired
	SansodhanBillVuktaniService sbvService;

	@Autowired
	PahilocharanBillVuktaniService pcbvService;

	@Autowired
	DosrocharanBillVuktaniService dcbvService;
	
	private String tableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	Message message = new Message();
	String sql;
	DB db = new DB();

	@PostMapping("/{applicationNo}/{id}")
	public Object saveForms(HttpServletRequest request, @PathVariable Long applicationNo, @RequestHeader(value = "Authorization") String Authorization,
			@PathVariable String id) {

		List l = new ArrayList<>(), list = new ArrayList<>();
		Object obj1 = "";

		ApplicationApproved approved = new ApplicationApproved();
		approved.setComment("Auto");
		approved.setRemark("Auto");
		approved.setStatus("Auto");

		if (id.equalsIgnoreCase("46")) {
			try {
				SansodhanBillVuktani sbv = new SansodhanBillVuktani();
				sbv.setJsonData("Auto");
				sbv.setApplicationNo(applicationNo);
				obj1 = sbvService.save(sbv, getToken(request, Authorization, "R"));
				l.add(obj1);
				list = userTypes(id);
				for (int i = 0; i < list.size(); i++) {
					String userType = (String) list.get(i);
					String userTableColumn = userTableColumn(userType);
					String buildingPermitApplicationColumn = buildingPermitApplicationColumn(userType);
//					obj1 = sbvService.doApprove(applicationNo, approved, getToken(request, Authorization, userType));
					obj1 = approve(userTableColumn, buildingPermitApplicationColumn, applicationNo, userType, id, getToken(request, Authorization, userType));
					l.add(obj1);
				}
				if (l.size() < 2) {
					return message.respondWithError("Form could not be saved or approved.");
				}

				return message.respondWithMessage("Message", l);

			} catch (Exception e) {
				System.out.println("exc from sbv " + e.getMessage());
			}
		}

		if (id.equalsIgnoreCase("47")) {
			try {
				PahilocharanBillVuktani pcbv = new PahilocharanBillVuktani();
				pcbv.setJsonData("Auto");
				pcbv.setApplicationNo(applicationNo);
				obj1 = pcbvService.save(pcbv, getToken(request, Authorization, "R"));
				l.add(obj1);
				list = userTypes(id);
				for (int i = 0; i < list.size(); i++) {
					String userType = (String) list.get(i);
					String userTableColumn = userTableColumn(userType);
					String buildingPermitApplicationColumn = buildingPermitApplicationColumn(userType);
//					obj1 = pcbvService.doApprove(applicationNo, approved, getToken(request, Authorization, userType));
					obj1 = approve(userTableColumn, buildingPermitApplicationColumn, applicationNo, userType, id, getToken(request, Authorization, userType));
					l.add(obj1);
				}
				if (l.size() < 2) {
					return message.respondWithError("Form could not be saved or approved.");
				}

				return message.respondWithMessage("Message",l);

			} catch (Exception e) {
				System.out.println("exc from pcbv " + e.getMessage());
			}

		}

		if (id.equalsIgnoreCase("48")) {
			try {
				DosrocharanBillVuktani dcbv = new DosrocharanBillVuktani();
				dcbv.setJsonData("Auto");
				dcbv.setApplicationNo(applicationNo);
				obj1 = dcbvService.save(dcbv, getToken(request, Authorization, "R"));
				l.add(obj1);
				list = userTypes(id);
				for (int i = 0; i < list.size(); i++) {
					String userType = (String) list.get(i);
					String userTableColumn = userTableColumn(userType);
					String buildingPermitApplicationColumn = buildingPermitApplicationColumn(userType);
//					obj1 = dcbvService.doApprove(applicationNo, approved, getToken(request, Authorization, userType));
					obj1 = approve(userTableColumn, buildingPermitApplicationColumn, applicationNo, userType, id, getToken(request, Authorization, userType));
					l.add(obj1);
				}
				if (l.size() < 2) {
					return message.respondWithError("Form could not be saved or approved.");
				}

				return message.respondWithMessage("Message",l);
			} catch (Exception e) {
				System.out.println("exc from dcbv " + e.getMessage());
			}
		}
		return null;
	}

	public String getToken(HttpServletRequest request, String Authorization, String userType) {
		JWTToken token = new JWTToken(Authorization);
		Map map = new HashMap<>();
		map.put("userCode", token.getUserId());
		map.put("userName", token.getUserName());
		map.put("userType", userType);
		map.put("remoteUrl", request.getRemoteHost());
		String tokenNew = "Bearer " + JWTToken.get(map);
		return tokenNew;
	}

	public List userTypes(String id) {
		sql = "select coalesce(amin_approval,'N') \"aminApproval\",coalesce(chief_approval,'N') \"chiefApproval\",coalesce(designer_approval,'N') \"designerApproval\",coalesce(engr_approval,'N') \"engrApproval\",coalesce(poste_approval,'N') \"posteApproval\",coalesce(postf_approval,'N') \"postfApproval\",coalesce(postg_approval,'N') \"postgApproval\",coalesce(rajasow_approval,'N') \"rajasowApproval\",coalesce(sub_engr_approval,'N') \"subEngrApproval\",enter_by \"enterBy\",table_name \"tableName\" from form_name_master where id='"
				+ id + "'";
		message.list = db.getRecord(sql);
		message.map = (Map) message.list.get(0);
		setTableName(message.map.get("tableName").toString());
		List userTypes = new ArrayList<>();
		if (message.map.get("aminApproval").toString().equalsIgnoreCase("Y")) {
			userTypes.add("AD");
		}
		if (message.map.get("chiefApproval").toString().equalsIgnoreCase("Y")) {
			userTypes.add("C");
		}
		if (message.map.get("designerApproval").toString().equalsIgnoreCase("Y")) {
			userTypes.add("D");
		}
		if (message.map.get("engrApproval").toString().equalsIgnoreCase("Y")) {
			userTypes.add("A");
		}
		if (message.map.get("posteApproval").toString().equalsIgnoreCase("Y")) {
			userTypes.add("E");
		}
		if (message.map.get("postfApproval").toString().equalsIgnoreCase("Y")) {
			userTypes.add("F");
		}
		if (message.map.get("postgApproval").toString().equalsIgnoreCase("Y")) {
			userTypes.add("G");
		}
		if (message.map.get("rajasowApproval").toString().equalsIgnoreCase("Y")) {
			userTypes.add("R");
		}
		if (message.map.get("subEngrApproval").toString().equalsIgnoreCase("Y")) {
			userTypes.add("B");
		}

		return userTypes;
	}
	
	public ResponseEntity approve(String userTableColumn,String buildingPermitApplicationColumn,Long applicationNo,String userType,String formId,String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		int row;
		sql = "UPDATE " + this.tableName + " SET " + userTableColumn + "date=NOW()," + userTableColumn + "name='" + td.getUserName() + "'," + userTableColumn + "status='A' WHERE COALESCE(" + userTableColumn + "status,'P')!='A' and application_no=" + applicationNo;
        row = db.save(sql);
        System.out.println("row " + row);
        if (row == 0) {
            sql = " SELECT COALESCE(" + userTableColumn + "status,'P') status FROM " + tableName + " WHERE application_no=" + applicationNo;
            message.list = db.getRecord(sql);
            if (message.list.isEmpty() || message.list.size() == 0) {
                return ResponseEntity.ok(message.respondWithError("This form could not be saved!"));
            }
            return ResponseEntity.ok(message.respondWithError("This form already approve by this user!!"));
        }

        message.setHistory(applicationNo, userType, formId, "A", td.getUserName());
        sql = "UPDATE building_permit_application SET " + buildingPermitApplicationColumn + "status='A'," + buildingPermitApplicationColumn + "action='" + formId + "',application_status='A',application_action='" + formId + "',application_action_by='" + userType + "' WHERE id=" + applicationNo;
        db.save(sql);
        sql = "INSERT INTO applications_comments (APPLICATION_NO,USER_TYPE,COMMENT_BY,COMMENT,COMMENT_DATE,PAGE,REMARK) VALUES('" + applicationNo + "','" + userType + "','" + td.getUserName() + "',?,'" + DateConvert.now() + "','" + formId + "',?)";
        db.save(sql, "Auto", "APPROVED_COMMENT");
        
        return ResponseEntity.ok(message.respondWithMessage("Application Approved"));
	}
	
	private String userTableColumn(String type) {
        if (type.equalsIgnoreCase("A")) {
            return "er_";
        } else if (type.equalsIgnoreCase("B")) {
            return "ser_";
        } else if (type.equalsIgnoreCase("C")) {
            return "chief_";
        } else if (type.equalsIgnoreCase("AD")) {
            return "amini_";
        } else if (type.equalsIgnoreCase("E")) {
            return "e_";
        } else if (type.equalsIgnoreCase("F")) {
            return "f_";
        } else if (type.equalsIgnoreCase("G")) {
            return "g_";
        }
        return "";
    }

    private String buildingPermitApplicationColumn(String type) {

        if (type.equalsIgnoreCase("A")) {
            return "engineer_";
        } else if (type.equalsIgnoreCase("B")) {
            return "sub_engineer_";
        } else if (type.equalsIgnoreCase("C")) {
            return "chief_";
        } else if (type.equalsIgnoreCase("AD")) {
            return "amin_";
        } else if (type.equalsIgnoreCase("R")) {
            return "rajasow_";
        } else if (type.equalsIgnoreCase("E")) {
            return "poste_";
        } else if (type.equalsIgnoreCase("F")) {
            return "postf_";
        } else if (type.equalsIgnoreCase("G")) {
            return "postg_";
        }
        return "";
    }

}
