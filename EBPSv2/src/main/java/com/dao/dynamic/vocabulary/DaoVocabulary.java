package com.dao.dynamic.vocabulary;

import java.util.List;

import com.model.dynamic.vocabulary.Vocabulary;
import com.model.dynamic.vocabulary.VocabularyDetails;

public interface DaoVocabulary {

	public int execute(String sql);

	public List getRecord(String sql);

	public List<Vocabulary> getAll(String hql);

	public List<VocabularyDetails> getAllDetails(String hql);

	public int save(Vocabulary obj);

	public int delete(Vocabulary obj);

	public int deleteDetaisl(VocabularyDetails obj);

	public String getMsg();

}
