package com.model.dynamic.vocabulary;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "vocabulary")
public class Vocabulary {

	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "vocabulary")
	@Fetch(FetchMode.SUBSELECT)
	List<VocabularyDetails> vocabularyDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VocabularyDetails> getVocabularyDetails() {
		return vocabularyDetails;
	}

	public void setVocabularyDetails(List<VocabularyDetails> vocabularyDetails) {
		this.vocabularyDetails = vocabularyDetails;
	}

}
