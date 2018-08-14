package com.example.konkurs.entities.dto;

import java.util.List;

import com.example.konkurs.entities.CertificateEntity;
import com.example.konkurs.entities.EducationEntity;
import com.example.konkurs.entities.enums.EEducationLevel;
import com.example.konkurs.entities.enums.EGender;

public class ApplicationDto {
	
	private String firstName;
	
	private String lastName;
	
	private EGender gender;
	
	private String email;
	
	private String idNumber;
	
	private String ssn;
	
	private Integer citizenshipId;
	
	private List<LanguageDto> language;
	
	private EEducationLevel educationLevel;
	
	private List<EducationEntity> education;
	
	private String candidateNote;
	
	private List<CertificateEntity> certifications;
	
	private String applicationNote;

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

	public Integer getCitizenshipId() {
		return citizenshipId;
	}

	public void setCitizenshipId(Integer citizenshipId) {
		this.citizenshipId = citizenshipId;
	}

	public List<LanguageDto> getLanguage() {
		return language;
	}

	public void setLanguage(List<LanguageDto> language) {
		this.language = language;
	}

	public List<EducationEntity> getEducation() {
		return education;
	}

	public void setEducation(List<EducationEntity> education) {
		this.education = education;
	}

	public String getCandidateNote() {
		return candidateNote;
	}

	public void setCandidateNote(String candidateNote) {
		this.candidateNote = candidateNote;
	}

	public String getApplicationNote() {
		return applicationNote;
	}

	public void setApplicationNote(String applicationNote) {
		this.applicationNote = applicationNote;
	}

	public EEducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EEducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}


	public List<CertificateEntity> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<CertificateEntity> certifications) {
		this.certifications = certifications;
	}
	
	

}
