package com.service.processing;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApplicationApprove;
import com.config.JWTToken;
import com.dao.processing.EBPSDao;
import com.model.application.ApplicationApproved;
import com.model.processing.MultiStoreyForensic;

@Service
public class MultiStoreyForensicServiceImp implements MultiStoreyForensicService {

	@Autowired
	EBPSDao da;
	model.Message message = new model.Message();
	String sql;
	String form = "86";

	@Override
	public Object getAll(long applicationNo) {
		message.list = da.getAll("from MultiStoreyForensic where applicationNo=" + applicationNo);
		if (message.list.isEmpty()) {
			return message.respondWithError("Record not found");
		}

		message.map = new HashMap();
		message.map.put("data", message.list.get(0));
		message.map.put("comment", message.getComment("" + applicationNo, form));
		message.map.put("history", message.getHistory(applicationNo, form));
		return message.map;
	}

	@Override
	public Object save(MultiStoreyForensic obj, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Token");
		}

		String userType = td.getUserType();
		long applicationNo = obj.getApplicationNo();
		if (!message.checkSaveStatus(userType, form, applicationNo)) {
			return message.respondWithError(message.getMsg());
		}

		int row = 0;
		obj.setEnterBy(td.getUserId());
		obj.setEnterDate(new Date());
		try {
			row = da.save(obj);
			if (row == 1) {
				message.db.save(message.getEnterByStatus(userType, Integer.parseInt(form), applicationNo));
				message.setHistory(applicationNo, td.getUserType(), form, "S", td.getUserName());
				return message.respondWithMessage("Success");
			}
			return message.respondWithError(da.getMsg());
		} catch (Exception e) {
			return message.respondWithError(e.getMessage());
		}
	}

	@Override
	public Object doApprove(long applicationNo, ApplicationApproved obj, String Authorization) {
		return new ApplicationApprove().doApprove(applicationNo, obj, Authorization, form);
	}

	
}
