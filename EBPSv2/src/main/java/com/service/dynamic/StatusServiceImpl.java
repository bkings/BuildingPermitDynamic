package com.service.dynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.dynamic.DaoStatus;
import com.model.dynamic.Status;
import com.model.dynamic.StatusPK;

import model.Message;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	DaoStatus dao;

	Message message = new Message();
	String msg = "";
	int row = 1;

	@Override
	public Object save(Status status) {
		try {
			row = dao.save(status);
			msg = dao.getMsg();
			if (row > 0) {
				return message.respondWithMessage("Success");
			}
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return message.respondWithError(msg);
	}

}
