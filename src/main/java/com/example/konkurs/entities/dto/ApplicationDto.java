package com.example.konkurs.entities.dto;

import java.util.List;

import com.example.konkurs.entities.enums.ECitizenship;
import com.example.konkurs.entities.enums.EEducationLevel;
import com.example.konkurs.entities.enums.EGender;

public class ApplicationDto {
	
	private String firstName;
	
	private String lastName;
	
	private EGender gender;
	
	private String email;
	
	private String idNumber;
	
	private String ssn;
	
	private ECitizenship citizenship;
	
	private List<LanguageDto> language;
	
	private EEducationLevel educationLevel;
	
	private String school;
	
	private List<String> certifications;

	public ApplicationDto() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public EGender getGender() {
		return gender;
	}

	public void setGender(EGender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public ECitizenship getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(ECitizenship citizenship) {
		this.citizenship = citizenship;
	}

	public List<LanguageDto> getLanguage() {
		return language;
	}

	public void setLanguage(List<LanguageDto> language) {
		this.language = language;
	}

	public EEducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EEducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public List<String> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<String> certifications) {
		this.certifications = certifications;
	}
	
	

}
