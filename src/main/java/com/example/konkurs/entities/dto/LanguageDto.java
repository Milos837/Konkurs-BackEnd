package com.example.konkurs.entities.dto;

import com.example.konkurs.entities.enums.ELanguage;
import com.example.konkurs.entities.enums.ELanguageLevel;

public class LanguageDto {
	
	private ELanguage language;
	
	private ELanguageLevel level;

	public LanguageDto() {
		super();
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
	
	

}
