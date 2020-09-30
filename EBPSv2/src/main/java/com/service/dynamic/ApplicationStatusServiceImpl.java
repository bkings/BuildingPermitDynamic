package com.service.dynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.dynamic.DaoApplicationStatus;
import com.model.dynamic.ApplicationStatus;

import model.Message;

@Service
public class ApplicationStatusServiceImpl implements ApplicationStatusService {

	@Autowired
	DaoApplicationStatus dao;

	String msg = "";
	int row = 1;

	Message message = new Message();

	@Override
	public Object save(ApplicationStatus obj) {
		try {
			row = dao.save(obj);
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
