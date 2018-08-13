package com.example.konkurs.entities;

import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.example.konkurs.entities.enums.ECitizenship;
import com.example.konkurs.entities.enums.EEducationLevel;
import com.example.konkurs.entities.enums.EGender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "applications")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ApplicationEntity {
	
	@Id
	@GeneratedValue
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
	
	@Enumerated(EnumType.STRING)
	private ECitizenship citizenship;
	
	@OneToMany(mappedBy = "application", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<LanguageEntity> language;
	
	@Column
	private EEducationLevel educationLevel;
	
	@Column
	private String school;
	
	@OneToMany(mappedBy = "application", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<CertificateEntity> certifications;
	
	@Column
	private String cv;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "posting")
	private PostingEntity posting;
	
	@Column
	private Boolean deleted;
	
	@Version
	private Integer version;

	public ApplicationEntity() {
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

	public List<LanguageEntity> getLanguage() {
		return language;
	}

	public void setLanguage(List<LanguageEntity> language) {
		this.language = language;
	}

	public ECitizenship getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(ECitizenship citizenship) {
		this.citizenship = citizenship;
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

	public List<CertificateEntity> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<CertificateEntity> certifications) {
		this.certifications = certifications;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public PostingEntity getPosting() {
		return posting;
	}

	public void setPosting(PostingEntity posting) {
		this.posting = posting;
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
