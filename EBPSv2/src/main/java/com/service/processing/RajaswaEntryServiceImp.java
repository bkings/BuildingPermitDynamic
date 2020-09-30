/*    map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonData, new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {});
 */
package com.service.processing;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.DaoRajaswaEntry;
import com.model.application.ApplicationApproved;
import com.model.processing.RajaswaEntry;

@Service
public class RajaswaEntryServiceImp implements RajaswaEntryService {

	@Autowired
	DaoRajaswaEntry da;
	model.Message message = new model.Message();
	String msg = "", sql, pageId = "RJB";
	int row;

	@Override
	public Object getAll(long applicationNo) {
		message.list = da.getAll("from RajaswaEntry where applicationNo=" + applicationNo);
		if (message.list.size() == 0) {
			return message.respondWithError("Record not found");
		}
		message.map = new HashMap();
		message.map.put("data", message.list.get(0));
		message.map.put("comment", message.getComment("" + applicationNo, "12"));
		message.map.put("history", message.getHistory(applicationNo, "12"));
		return message.map;
	}

	@Override
	public Object save(RajaswaEntry obj, String Authorization) {

		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("invalid token");
		}
		String userType = td.getUserType();
		long applicationNo = obj.getApplicationNo();
		if (!message.checkSaveStatus(userType, "12", applicationNo)) {
//            return message.respondWithError(message.getMsg());
			return message.getMsg();
		}
		int row = 0;

		obj.setEnterBy(td.getUserId());
		obj.setEnterDate(new Date());

		try {
			row = da.save(obj);

			if (row == 1) {

				message.db.save(message.getEnterByStatus(userType, 12, applicationNo));
				message.setHistory(applicationNo, td.getUserType(), "12", "A", td.getUserName());

				return message.respondWithMessage("Success");
			}
			return message.respondWithError(da.getMsg());
		} catch (Exception e) {
			return message.respondWithError(e.getMessage());

		}
	}

	@Override
	public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization) {
		return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, "12");
	}
}
