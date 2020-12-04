package com.service.dynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.dynamic.DaoHasRevisedFormSetup;
import com.model.dynamic.HasRevisedFormSetup;

import model.Message;

@Service
public class HasRevisedFormSetupServiceImpl implements HasRevisedFormSetupService {

	@Autowired
	DaoHasRevisedFormSetup dao;

	String msg = "";
	int row = 1;

	Message message = new Message();

	@Override
	public Object save(HasRevisedFormSetup obj) {
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
