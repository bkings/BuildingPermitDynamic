package com.service.dynamic.vocabulary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.dynamic.vocabulary.DaoVocabulary;
import com.model.dynamic.EbpsColumns;
import com.model.dynamic.EbpsTables;
import com.model.dynamic.vocabulary.Vocabulary;
import com.model.dynamic.vocabulary.VocabularyDetails;

import model.Message;

@Service
public class VocabularyServiceImp implements VocabularyService {

	@Autowired
	DaoVocabulary dao;

	String msg = "", sql;
	Message message = new Message();
	int row;

	List list = new ArrayList<>();
	Map map = new HashMap();

	@Override
	public Object getAll(String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Authorization");
		}
		list = dao.getAll("FROM Vocabulary");
		return list;
	}

	@Override
	public Object save(Vocabulary obj, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Authorization");
		}

		List<VocabularyDetails> vocabDetails = obj.getVocabularyDetails();

		try {
			sql = "SELECT coalesce(MAX(ID),0)+1 as id FROM vocabulary_details";
			map = (Map) dao.getRecord(sql).get(0);
			long vocabId = Long.parseLong(map.get("id").toString());
			for (VocabularyDetails v : vocabDetails) {
				if (String.valueOf(v.getId()).equalsIgnoreCase("") || String.valueOf(v.getId()).length() == 0 || v.getId() == null) {
					v.setId(vocabId);
					vocabId++;
				}
			}
		} catch (Exception e) {
			msg = e.getMessage();
		}

		try {
			if (String.valueOf(obj.getId()).equalsIgnoreCase("") || String.valueOf(obj.getId()).length() == 0 || obj.getId() == null) {
				sql = "SELECT coalesce(MAX(ID),0)+1 as id FROM vocabulary";
				map = (Map) dao.getRecord(sql).get(0);
				obj.setId(Long.parseLong(map.get("id").toString()));
			}

			obj.setVocabularyDetails(vocabDetails);
			row = dao.save(obj);
			msg = dao.getMsg();
		} catch (Exception e) {
			msg = message.exceptionMsg(e);
		}

		if (row > 0) {
			return message.respondWithMessage("Success");
		}
		return message.respondWithError(msg);
	}

	@Override
	public Object delete(String id, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Authorization");
		}
		List<Vocabulary> l = new ArrayList<Vocabulary>();
		Vocabulary obj = new Vocabulary();
		String[] ids = id.split(",");
		for (String i : ids) {
			try {
				l = dao.getAll("FROM Vocabulary WHERE id=" + i);
				obj = l.get(0);
				row = dao.delete(obj);
				msg = dao.getMsg();
				if (row != 0) {
					row++;
				}
			} catch (Exception e) {
				msg = e.getMessage();
			}
		}

		if (row > 0) {
			return message.respondWithMessage("Success");
		} else if (msg.contains("foreign key")) {
			msg = "Current records are being referenced from other tables.Could not delete.";
		}
		return message.respondWithError(msg);
	}

	@Override
	public Object deleteDetails(String id, String Authorization) {
		JWTToken td = new JWTToken(Authorization);
		if (!td.isStatus()) {
			return message.respondWithError("Invalid Authorization");
		}

		List<VocabularyDetails> l = new ArrayList<VocabularyDetails>();
		VocabularyDetails obj = new VocabularyDetails();
		String[] ids = id.split(",");
		for (String i : ids) {
			try {
				l = dao.getAllDetails("FROM VocabularyDetails WHERE id=" + i);
				obj = l.get(0);
				row = dao.deleteDetaisl(obj);
				msg = dao.getMsg();
				if (row != 0) {
					row++;
				}
			} catch (Exception e) {
				msg = e.getMessage();
			}
		}
		if (row > 0) {
			return message.respondWithMessage("Success");
		} else if (msg.contains("foreign key")) {
			msg = "Current records are being referenced from other tables.Could not delete.";
		}
		return message.respondWithError(msg);
	}

}
