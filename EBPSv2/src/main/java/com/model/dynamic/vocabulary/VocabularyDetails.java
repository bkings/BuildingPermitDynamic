package com.model.dynamic.vocabulary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vocabulary_details")
public class VocabularyDetails {

	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "VALUE")
	private String value;
	@Column(name = "LABEL")
	private String label;
	@Column(name = "VOCABULARY_ID")
	private Long vocabularyId;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "VOCABULARY_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private Vocabulary vocabulary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getVocabularyId() {
		return vocabularyId;
	}

	public void setVocabularyId(Long vocabularyId) {
		this.vocabularyId = vocabularyId;
	}

}
