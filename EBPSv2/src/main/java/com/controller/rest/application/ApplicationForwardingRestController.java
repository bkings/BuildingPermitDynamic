package com.controller.rest.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.config.JWTToken;
import com.dao.application.DaoBuildingPermitApplication;
import com.model.application.BuildingPermitApplication;

import model.DateConvert;
import model.Message;

@RestController
@CrossOrigin
@RequestMapping("api/Application/Forwarding/Phase1")
public class ApplicationForwardingRestController {
	
	@Autowired
	DaoBuildingPermitApplication da;
	List l = new ArrayList<>();
	Map mm = new HashMap<>();

    @PostMapping("/{forwardBy}/{applicationNo}/{groupId}")
    public Object doForwardingByDesigner(@PathVariable String forwardBy, @PathVariable Long applicationNo, @PathVariable Integer groupId, @RequestParam String forwaTo, @RequestParam(required = false) String forcefully, @RequestHeader(value = "Authorization") String Authorization) {
    	
    	/**
    	 * Added
    	 */
    	List<BuildingPermitApplication> lp = da.getAll("from BuildingPermitApplication where id=" + applicationNo);
    	Long nameTransferId = 0l;
    	if(!lp.isEmpty()) {
    		BuildingPermitApplication obj = lp.get(0);
    		try {
    			nameTransferId = obj.getNameTransaferId();
			} catch (Exception e) {
			}
    	}
    	
    	String[] multiSql = new String[3];
    	
    	/*String forwardByForStatus = "",userApprovalStatus = "";
    	if(forwardBy.equalsIgnoreCase("A")) {
    		forwardByForStatus = "er_";
    		userApprovalStatus = "engr_";
    	} else if(forwardBy.equalsIgnoreCase("B")) {
    		forwardByForStatus = "ser_";
    		userApprovalStatus = "sub_engr_";
    	} else if(forwardBy.equalsIgnoreCase("C")) {
    		forwardByForStatus = "chief_";
    		userApprovalStatus = "chief_";
    	} else if(forwardBy.equalsIgnoreCase("E")) {
    		forwardByForStatus = "e_";
    		userApprovalStatus = "poste_";
    	} else if(forwardBy.equalsIgnoreCase("F")) {
    		forwardByForStatus = "f_";
    		userApprovalStatus = "postf_";
    	} else if(forwardBy.equalsIgnoreCase("G")) {
    		forwardByForStatus = "g_";
    		userApprovalStatus = "postg_";
    	} else if(forwardBy.equalsIgnoreCase("AD")) {
    		forwardByForStatus = "amini_";
    		userApprovalStatus = "amin_";
    	} else if(forwardBy.equalsIgnoreCase("R")) {
    		forwardByForStatus = "rw_";
    		userApprovalStatus = "rajasow_";
    	}*/
    	
        Message m = new Message();
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return m.respondWithError("Authorization Error");
        }
        if (!forwardBy.equalsIgnoreCase(td.getUserType())) {
            return m.respondWithError("यो कार्य गर्नका लागि तपाईलाई अनुमति दिईएको छैन । (" + forwardBy + ")");
        } else if (!(forwardBy.length() <= 2)) {
            return m.respondWithError("यो कार्य गर्नका लागि तपाईलाई अनुमति दिईएको छैन ।");
        }
        String forwardToName = td.getUserTypeName(forwaTo);
        String forwardToAction = td.getActionStatus();
        System.out.println("forwardToAction " + forwardToAction);
        String forwardByName = td.getUserTypeName(forwardBy);
//        String forwardByAction = td.getActionStatus();
        ApplicationForwarding call = new ApplicationForwarding();

        String sql, constructionType;
//        String forwaToAction = forwardToAction + "action";
//        System.out.println("forwaToAction " + forwaToAction);
        int forwaToActionPage;

        sql = "SELECT B.construction_type \"constructionType\",S.user_action \"forwaToAction\" FROM building_permit_application B,application_status S WHERE B.id=S.application_no AND S.user_type='"+forwaTo+"' AND S.application_no=" + applicationNo;
        m.list = m.db.getRecord(sql);
        m.map = (Map) m.list.get(0);
        constructionType = m.map.get("constructionType").toString();
        try {
            forwaToActionPage = Integer.parseInt(m.map.get("forwaToAction").toString());
        } catch (Exception e) {
            forwaToActionPage = 0;
        }
        
        sql = "SELECT coalesce(rejected_by_user,'N') \"isRejected\" FROM application_status WHERE user_type='D' AND application_no=" + applicationNo;
        m.list = m.db.getRecord(sql);
        m.map = (Map) m.list.get(0);
        String isRejected = m.map.get("isRejected").toString();
        
        /*sql = "SELECT construction_type \"constructionType\"," + forwaToAction + "  AS \"forwaToAction\",coalesce(rejected_d,'N') AS \"isRejected\" FROM building_permit_application WHERE application_no='" + applicationNo + "'";
        m.list = m.db.getRecord(sql);
        m.map = (Map) m.list.get(0);*/
        
        System.out.println("forwaToActionPage " + forwaToActionPage);
        String buildingClass, groupType = m.getGroupType1(constructionType, nameTransferId); 		//m.getGroupType(constructionType);
        sql = "SELECT building_class \"buildingClass\" FROM anusuchi_ka WHERE application_no='" + applicationNo + "'";
        m.list = m.db.getRecord(sql);
        if (m.list.isEmpty()) {
            return m.respondWithError("अनुसूची क मा काम भएको छैन |");
        }
        m.map = (Map) m.list.get(0);
        buildingClass = m.map.get("buildingClass").toString();
        String classGroup = m.classGroup(groupType, buildingClass);
        String hasRevised = m.hasRevisedForm(forwardBy, applicationNo);
        sql = "SELECT \"name\",enter_by AS \"enterBy\",(SELECT table_name FROM ebps_tables WHERE id=table_id) \"tableName\",F.id \"formId\" FROM form_name_master F,form_group G WHERE F.id=G.form_id AND table_id IS NOT NULL AND G.group_type='" + groupType + "' AND G.group_id=" + groupId + " AND G.user_type='" + forwardBy + "'  AND F.ID NOT IN(" + hasRevised + "," + classGroup + ")  ORDER BY G.group_position";
        m.list = m.db.getRecord(sql);
        String name, tableName, enterBy, status, colunmName = "", msg = "",formId;
        List l;
        int count = 0;
        for (int i = 0; i < m.list.size(); i++) {
            try {
                m.map = (Map) m.list.get(i);
                name = m.map.get("name").toString();
                tableName = m.map.get("tableName").toString();
                formId = m.map.get("formId").toString();
                if (tableName.length() < 2) {
                    continue;
                }
                enterBy = m.map.get("enterBy").toString();

                //save garda ko case
                if (enterBy.equalsIgnoreCase(forwardBy)) {
//                    colunmName = "(CASE WHEN enter_date IS NULL THEN 'N' ELSE 'Y' END) AS \"status\"";
                    sql = "SELECT (CASE WHEN enter_date IS NULL THEN 'N' ELSE 'Y' END) AS \"status\" FROM "+tableName+" WHERE application_no=" + applicationNo;
                } else {
//                  continue;
//                	added later
                	/**
                	 * Approve garda ko case
                	 * Check approve status for only those forms that the logged in user can approve else skip.
                	 */
                	try {
                		sql = "SELECT approval_status AS \"approvalStatus\" FROM form_permissions WHERE formId = '"+formId+"' AND user_type='"+forwardBy+"'";
//                		sql = "SELECT " + userApprovalStatus + "approval AS \"approvalStatus\" FROM form_name_master WHERE table_name='"+tableName+"' ";
                    	l = m.db.getRecord(sql);
                    	m.map = (Map) l.get(0);
                    	String approvalStatus = m.map.get("approvalStatus").toString();
                    	if(approvalStatus.equalsIgnoreCase("Y")) {
//                    		colunmName = "(case when "+ forwardByForStatus +"status='A' THEN 'Y' ELSE 'N' END) AS \"status\"";
                    		sql = "SELECT (case when status='A' THEN 'Y' ELSE 'N' END) AS \"status\" FROM status WHERE application_no="+applicationNo+" AND form_id='"+formId+"' AND user_type='"+forwardBy+"'";
                    	} else {
                    		continue;
                    	}
					} catch (Exception e) {
						System.out.println("error " + e.getMessage());
						continue;
					}
                }
            } catch (Exception e) {
                continue;
            }
//            sql = "SELECT " + colunmName + " FROM " + tableName + " WHERE application_no='" + applicationNo + "'";
            l = m.db.getRecord(sql);
            if (l.isEmpty()) {
                msg = msg + name + " मा काम भएको छैन | ";
                count++;
            } else {
                try {
                    m.map = (Map) l.get(0);
                    status = m.map.get("status").toString();
                    if (!status.equalsIgnoreCase("Y")) {
                        count++;
                        msg = msg + name + " मा काम भएको छैन | ";
                    }
                } catch (Exception e) {
                    count++;
                    msg = msg + name + " मा काम भएको छैन | ";
                }
            }

        }
        if (count == 0) {
            String data = DateConvert.now();
//            String remark = "";
            String remark1 = "",remark2="";
            List list = td.getAllUserType();
            Map map;
//            remark = "";
            for (int i = 0; i < list.size(); i++) {
                map = (Map) list.get(i);
                String ttt = map.get("userType").toString();
                
                remark1 = "forward_to_user_remark='यो फाईल " + forwardByName + " बाट पठाईएको छ ।' ";
                remark2 = "forward_to_user_remark='यो फाईल " + forwardToName + " लाई पठाईएको छ ।' ";
                /*if (ttt.length() <= 2) {
                    if (ttt.equalsIgnoreCase(forwaTo)) {
                        remark = remark + ",forward_to_" + forwaTo + "_remark='यो फाईल " + forwardByName + " बाट पठाईएको छ ।'";
                    } else {
                        remark = remark + ",forward_to_" + map.get("userType") + "_remark='यो फाईल " + forwardToName + " लाई पठाईएको छ ।'";
                    }
                }*/
            }
            String forwardedId;
            try {
                sql = "SELECT coalesce(MAX(id),0)+1 \"forwardId\" FROM forward_status";
                m.map = (Map) m.db.getRecord(sql).get(0);
                forwardedId = m.map.get("forwardId").toString();
            } catch (Exception e) {
                return m.respondWithError("connection error or invalid table name");
            }

            sql = "INSERT INTO forward_status(id,applicatiion_no,group_id,forwarded_by,forwarded_to,forwarded_by_name,status,forwarded_time) VALUES(" + forwardedId + "," + applicationNo + "," + groupId + ",'" + forwardBy + "','" + forwaTo + "','" + td.getUserName() + "','Y','" + DateConvert.now() + "')";
            m.db.save(sql);
            msg = m.db.getMsg();
            if (msg.contains("duplicate key")) {
                try {
                    try {
                        if (forcefully.length() == 0) {
                            forcefully = "N";
                        }
                    } catch (Exception e) {
                        forcefully = "N";
                    }
                    if (!forcefully.equalsIgnoreCase("Y")) {
                        sql = "SELECT application_no FROM sansodhan_bill_vuktani WHERE application_no=" + applicationNo;
                        if (m.db.getRecord(sql).isEmpty()) {
                            return m.respondWithError("This group is already forwarded");
                        }
                        sql = "SELECT application_no FROM pahilocharan_bill_vuktani WHERE application_no=" + applicationNo;
                        if (m.db.getRecord(sql).isEmpty()) {
                            return m.respondWithError("This group is already forwarded");
                        }
                        sql = "SELECT application_no FROM dosrocharan_bill_vuktani WHERE application_no=" + applicationNo;
                        if (m.db.getRecord(sql).isEmpty()) {
                            return m.respondWithError("This group is already forwarded");
                        }
                        return m.respondWithError("This group is already forwarded");
                    }
                } catch (Exception e) {
                    return m.respondWithError("This group is already forwarded");
                }
            }
            if (!isRejected.equalsIgnoreCase("Y")) {
                hasRevised = m.hasRevisedForm(forwaTo, applicationNo);
                System.out.println("forwaTo " + forwaTo + " groupType " + groupType + " hasRevised " + hasRevised + " classGroup " + classGroup + " forwaToActionPage " + forwaToActionPage);
                forwaToActionPage = call.getReceiverAction(forwaTo, groupType, hasRevised, classGroup, forwaToActionPage,applicationNo);
                System.out.println("ForwaToActionPage " + forwaToActionPage);
            }
            if (forwaToActionPage < 1) {
                forwaToActionPage = 0;
            }
            
            try {
            	multiSql[0] = "UPDATE application_status SET user_action='"+forwaToActionPage+"',forward_to_user_date='"+data+"',"+remark1+",forward_to_user='I'.forward_to_user_name='"+forwardToName+"' WHERE application_no="+applicationNo+" AND user_type='"+forwaTo+"' ";
                multiSql[1] = "UPDATE application_status SET forward_to_user_date='"+data+"',"+remark2+",forward_to_user_name='"+td.getUserName()+"',forward_to_user='O' WHERE application_no="+applicationNo+" AND user_type='"+forwardBy+"'";
                multiSql[1] = "UPDATE building_permit_application SET application_action='"+forwaToActionPage+"' WHERE id=" + applicationNo;
                m.db.saveMultiple(multiSql);
			} catch (Exception e) {
				System.out.println("exc " + e.getMessage());
			}
            
            sql = "SELECT user_type \"userType\" FROM application_status WHERE application_no=" + applicationNo;
            l = m.db.getRecord(sql);
            for(Object o : l) {
            	try {
            		mm = (Map) o;
            		sql = "UPDATE application_status SET rejected_by_user='N' WHERE user_type='"+mm.get("userType").toString()+"'";
            		m.db.save(sql);
				} catch (Exception e) {
					System.out.println("e" + e.getMessage());
				}
            }
            
            /*sql = "UPDATE building_permit_application SET rejected_a='N',rejected_b='N',rejected_c='N',rejected_d='N',rejected_da='N',rejected_e='N',rejected_f='N',rejected_g='N',"
            		+ "application_action='" + forwaToActionPage + "'," + forwaToAction + "='" + forwaToActionPage + "',"
            				+ "forward_to_" + forwardBy + "_date='" + data + "',forward_to_" + forwaTo.toLowerCase() + "_date='" + data + "'" + remark + ",forward_to_" + forwardBy + "_name='" + td.getUserName() + "',forward_to_"+forwardBy+"='O',forward_to_" + forwaTo.toLowerCase() + "='I' WHERE ID='" + applicationNo + "'";
            m.db.save(sql);*/
            sql = "SELECT A.ID id,applicant_name AS \"name\",applicant_address address,U.user_name designer,email FROM building_permit_application A,organization_user U WHERE A.enter_by=U.login_id  AND A.ID='" + applicationNo + "'";
            map = (Map) m.db.getRecord(sql).get(0);
            String email = map.get("email").toString();
            sql = "SELECT group_position  FROM form_group_master WHERE id='" + groupId + "'";
            map = (Map) m.db.getRecord(sql).get(0);
            String groupName = map.get("group_position").toString();
            /*String receiver = forwaToAction.replace("-", " ");
            receiver = receiver.replace("action", " ");
            receiver = receiver.replace("_", " ");
            receiver = receiver.replace(",", " ");*/
            String receiver = forwardToName;
            String subject = "application no " + applicationNo + " has been sent to " + receiver;
            String body = "<h2>Dear User</h2><br>"
                    + "The applicant no <b>" + applicationNo + "</b> of group " + groupName + " has been sent successfully to " + receiver + "  <br>";

            new Message().setEmailSending(applicationNo, email, DateConvert.now(), subject, body, "D");
            return m.respondWithMessage("Success");
        } else {
            return m.respondWithError(msg);
        }
    }

}
