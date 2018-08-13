package com.example.konkurs.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.example.konkurs.entities.enums.ELanguage;
import com.example.konkurs.entities.enums.ELanguageLevel;

@Entity
@Table(name = "languages")
public class LanguageEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private ELanguage language;
	
	@Enumerated(EnumType.STRING)
	private ELanguageLevel level;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "application")
	private ApplicationEntity application;
	
	@Column
	private Boolean deleted;
	
	@Version
	private Integer version;

	public LanguageEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ELanguage getLanguage() {
		return language;
	}

	public void setLanguage(ELanguage language) {
		this.language = language;
	}

	public ELanguageLevel getLevel() {
		return level;
	}

	public void setLevel(ELanguageLevel level) {
		this.level = level;
	}

	public ApplicationEntity getApplication() {
		return application;
	}

	public void setApplication(ApplicationEntity application) {
		this.application = application;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	

}
