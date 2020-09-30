package com.service.processing;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.DaoImpSthalgatNiirikshyanGariPesh;
import com.dao.processing.DaoSthalgatNiirikshyanGariPesh;
import com.model.application.ApplicationApproved;
import com.model.processing.SthalgatNiirikshyanGariPesh;
import com.model.processing.SthalgatNiirikshyanGariPeshDetails;
import com.model.processing.SthalgatNiirikshyanGariPeshDetailsPK;

import model.DB;

@Service
public class SthalgatNiirikshyanGariPeshServiceImp implements SthalgatNiirikshyanGariPeshService {

	DaoSthalgatNiirikshyanGariPesh da = new DaoImpSthalgatNiirikshyanGariPesh();
	model.Message message = new model.Message();
	String msg = "", sql, form = "88";
	int row;

	@Override
	public Object getindex(long applicationNo) {
		DB db = new DB();
		Map map;
		List list;
		sql = "SELECT R.ENTER_BY \"enterBy\",R.ENTER_DATE \"enterDate\",R.ER_DATE \"erDate\",R.ER_NAME \"erName\",R.ER_STATUS \"erStatus\",R.SER_DATE \"serDate\",R.SER_NAME \"serName\",R.SER_STATUS \"serStatus\",json_data \"jsonData\" FROM building_permit_application A LEFT JOIN  sthalgat_nirikshyan_gari_pesh R ON A.id=R.application_no WHERE A.id='"
				+ applicationNo + "'";
		list = db.getRecord(sql);
		if (list.isEmpty()) {
			return message.respondWithError("invalid application no");
		}
		map = (Map) list.get(0);
		sql = "SELECT M.id AS \"descriptionId\",M.building_description description,M.SN SN,M.type,D.application_no \"applicationNo\",coalesce(D.design_data,'') AS \"designData\",coalesce(D.remark,'') AS remark FROM map_check_report_master M ,sthalgat_nirikshyan_gari_pesh_details D WHERE M.ID=D.class_id AND D.application_no='"
				+ applicationNo + "' UNION SELECT M.id AS \"descriptionId\",M.building_description description,M.sn,M.type,'" + applicationNo
				+ "' \"applicationNo\",'' AS \"designData\",'' AS remark FROM map_check_report_master M WHERE M.ID NOT IN(SELECT D.class_id FROM sthalgat_nirikshyan_gari_pesh_details D WHERE D.application_no='"
				+ applicationNo + "') order by  \"descriptionId\"";
		list = db.getRecord(sql);
		map.put("details", list);
		map.put("comment", message.getComment("" + applicationNo, form));
		map.put("history", message.getHistory(applicationNo, form));
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
			if (!message.checkSaveStatus(userType, form, applicationNo)) {
				return message.respondWithError(message.getMsg());
			}
			SthalgatNiirikshyanGariPesh obj = new SthalgatNiirikshyanGariPesh();
			SthalgatNiirikshyanGariPeshDetails details = new SthalgatNiirikshyanGariPeshDetails();
			SthalgatNiirikshyanGariPeshDetailsPK pk = new SthalgatNiirikshyanGariPeshDetailsPK();
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
			obj.setJsonData(String.valueOf(map.get("jsonData")));

			row = da.save(obj);
			message.setHistory(applicationNo, td.getUserType(), form, "S", td.getUserName());
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
				message.db.save(message.getEnterByStatus(userType, Integer.parseInt(form), applicationNo));
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
		return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, form);
	}
	
}
