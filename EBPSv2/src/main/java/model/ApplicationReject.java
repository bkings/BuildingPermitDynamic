/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationReject {

    public void doAction(long applicationNo, String commentBy, String userType, String formId, String comment, String remark, String forwardByName, String forwardToName) {
        Message message = new Message();
        DB db = new DB();
        List list;
        Map m = new HashMap();
        
        //added for namsari case
        List lp = db.getRecord("SELECT name_transafer_id AS \"nameTransferId\" FROM building_permit_application WHERE id=" + applicationNo);
        Long nameTransferId = 0l;
        if(!lp.isEmpty() ) {
        	m = (Map) lp.get(0);
        	try {
        		nameTransferId = Long.parseLong(m.get("nameTransferId").toString());
			} catch (Exception e) {
				nameTransferId = 0l;
			}
        }
        //add's end
        
        if (comment.length() > 1) {
            String sql = "";
            String[] multiSql = new String[3];
            sql = "SELECT enter_by \"enterBy\",(SELECT table_name FROM ebps_tables WHERE id=table_id) \"tableName\" FROM form_name_master WHERE id=" + formId;
            list = db.getRecord(sql);
            Map map = (Map) list.get(0);
            String forwaTo = map.get("enterBy").toString();
            String tableName = map.get("tableName").toString();         
            /*String applicationRemark = "";
            String tableStatus = "";
            if (forwaTo.equalsIgnoreCase("A")) {
                applicationRemark = ",engineer_status='R',forward_to_a='I',forward_to_b='O',forward_to_c='O',forward_to_d='O',forward_to_ad='O',forward_to_r='O',forward_to_e='0',forward_to_f='0',forward_to_g='0',forward_to_a_remark='यो फाईल " + forwardByName + " बाट पठाईएको छ ।',forward_to_e_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_f_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_g_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_b_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_r_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_d_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_c_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_ad_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।'";
                tableStatus = ",er_status='R'";
            } else if (forwaTo.equalsIgnoreCase("B")) {
                applicationRemark = ",sub_engineer_status='R',forward_to_b='I',forward_to_a='O',forward_to_c='O',forward_to_d='O',forward_to_ad='O',forward_to_r='O',forward_to_e='0',forward_to_f='0',forward_to_g='0',forward_to_b_remark='यो फाईल " + forwardByName + " बाट पठाईएको छ ।',forward_to_e_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_f_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_g_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_a_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_r_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_d_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_c_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_ad_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।'";
                tableStatus = ",ser_status='R'";
            } else if (forwaTo.equalsIgnoreCase("C")) {
                applicationRemark = ",chief_status='R',forward_to_c='I',forward_to_a='O',forward_to_b='O',forward_to_d='O',forward_to_ad='O',forward_to_r='O',forward_to_e='0',forward_to_f='0',forward_to_g='0',forward_to_c_remark='यो फाईल " + forwardByName + " बाट पठाईएको छ ।',forward_to_e_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_f_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_g_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_a_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_r_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_d_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_b_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_ad_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।'";
                tableStatus = ",chief_status='R'";
            } else if (forwaTo.equalsIgnoreCase("D")) {
                applicationRemark = ",designer_status='R',forward_to_d='I',forward_to_a='O',forward_to_b='O',forward_to_c='O',forward_to_ad='O',forward_to_r='O',forward_to_e='0',forward_to_f='0',forward_to_g='0',forward_to_d_remark='यो फाईल " + forwardByName + " बाट पठाईएको छ ।',forward_to_e_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_f_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_g_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_a_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_r_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_c_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_b_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_ad_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।'";
            } else if (forwaTo.equalsIgnoreCase("R")) {
                applicationRemark = ",rajasow_status='R',forward_to_r='I',forward_to_a='O',forward_to_b='O',forward_to_c='O',forward_to_d='O',forward_to_ad='O',forward_to_e='0',forward_to_f='0',forward_to_g='0',forward_to_r_remark='यो फाईल " + forwardByName + " बाट पठाईएको छ ।',forward_to_e_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_f_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_g_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_a_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_c_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_d_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_b_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_ad_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।'";
                tableStatus = ",rw_status='R'";
            } else if (forwaTo.equalsIgnoreCase("AD")) {
                applicationRemark = ",amin_status='R',forward_to_ad='I',forward_to_a='O',forward_to_b='O',forward_to_c='O',forward_to_d='O',forward_to_r='O',forward_to_e='0',forward_to_f='0',forward_to_g='0',forward_to_ad_remark='यो फाईल " + forwardByName + " बाट पठाईएको छ ।',forward_to_e_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_f_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_g_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_a_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_r_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_d_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_b_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_c_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।'";
                tableStatus = ",amini_status='R'";
            } else if (forwaTo.equalsIgnoreCase("E")) {
            	applicationRemark = ",poste_status='R',forward_to_e='I',forward_to_a='O',forward_to_b='O',forward_to_c='O',forward_to_d='O',forward_to_r='O',forward_to_ad='0',forward_to_f='0',forward_to_g='0',forward_to_e_remark='यो फाईल " + forwardByName + " बाट पठाईएको छ ।',forward_to_f_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_g_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_a_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_r_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_d_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_b_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_ad_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_c_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।'";
                tableStatus = ",e_status='R'";
            } else if (forwaTo.equalsIgnoreCase("F")) {
            	applicationRemark = ",postf_status='R',forward_to_f='I',forward_to_a='O',forward_to_b='O',forward_to_c='O',forward_to_d='O',forward_to_r='O',forward_to_ad='0',forward_to_e='0',forward_to_g='0',forward_to_f_remark='यो फाईल " + forwardByName + " बाट पठाईएको छ ।',forward_to_e_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_g_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_a_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_r_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_d_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_b_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_ad_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_c_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।'";
                tableStatus = ",f_status='R'";
            } else if (forwaTo.equalsIgnoreCase("G")) {
            	applicationRemark = ",postg_status='R',forward_to_g='I',forward_to_a='O',forward_to_b='O',forward_to_c='O',forward_to_d='O',forward_to_r='O',forward_to_ad='0',forward_to_f='0',forward_to_e='0',forward_to_g_remark='यो फाईल " + forwardByName + " बाट पठाईएको छ ।',forward_to_f_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_e_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_a_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_r_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_d_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_b_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_ad_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।',forward_to_c_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।'";
                tableStatus = ",g_status='R'";
            }
            String rejectAction = "";

            if (userType.equalsIgnoreCase("A")) {
                rejectAction = ",engineer_action='" + formId + "',engineer_status='R',rejected_a='Y'";
                tableStatus = tableStatus + ",er_status='R'";
            } else if (userType.equalsIgnoreCase("B")) {
                rejectAction = ",sub_engineer_action='" + formId + "',sub_engineer_status='R',rejected_b='Y'";
                tableStatus = tableStatus + ",ser_status='R'";
            } else if (userType.equalsIgnoreCase("C")) {
                rejectAction = ",chief_action='" + formId + "',chief_status='R',rejected_c='Y'";
                tableStatus = tableStatus + ",chief_status='R'";
            } else if (userType.equalsIgnoreCase("AD")) {
                rejectAction = ",amin_action='" + formId + "',amin_status='R',rejected_da='Y'";
                tableStatus = tableStatus + ",amini_status='R'";
            } else if (userType.equalsIgnoreCase("R")) {
                rejectAction = ",rajasow_action='" + formId + "',rajasow_status='R',rejected_r='Y'";
                tableStatus = tableStatus + ",rw_status='R'";
            } else if (userType.equalsIgnoreCase("E")) {
            	rejectAction = ",poste_action='" + formId + "',e_status='R',rejected_e='Y'";
                tableStatus = tableStatus + ",e_status='R'";
            } else if (userType.equalsIgnoreCase("F")) {
            	rejectAction = ",postf_action='" + formId + "',f_status='R',rejected_f='Y'";
                tableStatus = tableStatus + ",f_status='R'";
            } else if (userType.equalsIgnoreCase("G")) {
            	rejectAction = ",postg_action='" + formId + "',g_status='R',rejected_g='Y'";
                tableStatus = tableStatus + ",g_status='R'";
            }*/

            sql = "SELECT id as \"userType\" FROM user_type_master WHERE id NOT IN ('ADM','TADM')";
			List userTypeList = db.getRecord(sql);
			Map mm = new HashMap<>();
			String uType;
			for(Object o:userTypeList) {
				mm = (Map) o;
				uType=mm.get("userType").toString();
				if(uType.equalsIgnoreCase(forwaTo)) {
					multiSql[0] = "UPDATE application_status SET user_full_status='R',forward_to_user='I',forward_to_user_remark='यो फाईल "+forwardByName+" बाट पठाईएको छ ।'  WHERE application_no= "+applicationNo+" AND user_type='"+uType+"'";
					multiSql[1] = "UPDATE application_status SET user_full_status='R',user_action='"+formId+"',rejected_by_user='Y' WHERE application_no="+applicationNo+" AND user_type='"+userType+"'";
					multiSql[2] = "UPDATE building_permit_application SET application_action='"+formId+"',application_action_by='"+userType+"',application_status='R' WHERE ID=" + applicationNo;
					db.saveMultiple(multiSql);
				} else {
					sql = "UPDATE application_status SET forward_to_user='O',forward_to_user_remark='यो फाईल " + forwardToName+ " लाई पठाईएको छ ।'  WHERE application_no="+applicationNo+" AND user_type='"+uType+"'";
					db.save(sql);
				}
				
			}
			
			try {
				multiSql[0] = "UPDATE status SET status='R' WHERE application_no="+applicationNo+" AND form_id='"+formId+"' AND user_type='"+forwaTo+"'";
	            multiSql[1] = "UPDATE status SET status='R' WHERE application_no="+applicationNo+" AND form_id='"+formId+"' AND user_type='"+userType+"'";
	            multiSql[2] = "UPDATE "+tableName+" SET enter_date=null WHERE application_no="+applicationNo+"";
	            db.saveMultiple(multiSql);
			} catch (Exception e) {
				System.out.println("error from multi " + e.getMessage());
			}
			
            /*sql = "UPDATE building_permit_application SET application_action='" + formId + "',application_action_by='" + userType + "',application_status='R' " + rejectAction + applicationRemark + " WHERE ID=" + applicationNo;
            db.save(sql);
            sql = "UPDATE " + tableName + " SET enter_date=null" + tableStatus + " WHERE application_no='" + applicationNo + "'";
            db.save(sql);*/
            sql = "SELECT construction_type \"constructionType\" FROM building_permit_application WHERE application_no='" + applicationNo + "'";
            map = (Map) db.getRecord(sql).get(0);
            String constructionType = map.get("constructionType").toString();
//            String groupType = new Message().getGroupType(constructionType);
            String groupType = new Message().getGroupType1(constructionType,nameTransferId);
            sql = "SELECT group_id \"groupId\" FROM form_group WHERE form_id=" + formId + " AND user_type='" + userType + "' AND group_type='" + groupType + "'";
            list = db.getRecord(sql);
            map = (Map) list.get(0);
            String groupId = map.get("groupId").toString();
            sql = "UPDATE forward_status SET status=null WHERE group_id='" + groupId + "' AND applicatiion_no='" + applicationNo + "'";
            db.save(sql);
            sql = "INSERT INTO applications_comments (APPLICATION_NO,USER_TYPE,COMMENT_BY,COMMENT,COMMENT_DATE,PAGE,REMARK) VALUES('" + applicationNo + "','" + userType + "','" + commentBy + "',?,'" + DateConvert.now() + "','" + formId + "',?)";
            db.save(sql, comment, remark);
            sql = "SELECT A.ID id,applicant_name AS \"name\",applicant_address address,U.user_name designer,email FROM building_permit_application A,organization_user U WHERE A.enter_by=U.login_id  AND A.ID='" + applicationNo + "'";
            list = db.getRecord(sql);
            map = (Map) list.get(0);
            String email = map.get("email").toString();
            String subject = "Application no <b>" + applicationNo + "</b> has been rejected";
            String body = "<h2>Dear User</h2><br>"
                    + "The applicant no <b>" + applicationNo + "</b> form name has been rejected by " + commentBy + " <br>";
            try {
                message.setEmailSending(applicationNo, email, DateConvert.now(), subject, body, userType);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
