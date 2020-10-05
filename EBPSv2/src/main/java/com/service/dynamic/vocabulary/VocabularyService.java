package com.service.dynamic.vocabulary;

import com.model.dynamic.vocabulary.Vocabulary;
import com.model.dynamic.vocabulary.VocabularyDetails;

public interface VocabularyService {

	public Object getAll(String Authorization);

	public Object save(Vocabulary obj, String Authorization);

	public Object delete(String id, String Authorization);

	public Object deleteDetails(String id, String Authorization);

}
