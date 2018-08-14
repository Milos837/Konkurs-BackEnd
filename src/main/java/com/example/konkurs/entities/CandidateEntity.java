package com.example.konkurs.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.example.konkurs.entities.enums.EEducationLevel;
import com.example.konkurs.entities.enums.EGender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "candidate")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CandidateEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private EGender gender;
	
	@Column
	private String email;
	
	@Column
	private String idNumber;
	
	@Column
	private String ssn;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "citizenship")
	private CitizenshipEntity citizenship;
	
	@OneToMany(mappedBy = "candidate", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<CandidateLanguageEntity> candidateLanguage;
	
	@Enumerated(EnumType.STRING)
	private EEducationLevel educationLevel;
	
	@OneToMany(mappedBy = "candidate", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<EducationEntity> education;
	
	@OneToMany(mappedBy = "candidate", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<ApplicationEntity> applications;
	
	@Column
	private String note;
	
	@Column
	private Boolean deleted;
	
	@Version
	private Integer version;

	public CandidateEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public CitizenshipEntity getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(CitizenshipEntity citizenship) {
		this.citizenship = citizenship;
	}

	public List<CandidateLanguageEntity> getCandidateLanguage() {
		return candidateLanguage;
	}

	public void setCandidateLanguage(List<CandidateLanguageEntity> candidateLanguage) {
		this.candidateLanguage = candidateLanguage;
	}

	public EEducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EEducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public List<EducationEntity> getEducation() {
		return education;
	}

	public void setEducation(List<EducationEntity> education) {
		this.education = education;
	}

	public List<ApplicationEntity> getApplications() {
		return applications;
	}

	public void setApplications(List<ApplicationEntity> applications) {
		this.applications = applications;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
