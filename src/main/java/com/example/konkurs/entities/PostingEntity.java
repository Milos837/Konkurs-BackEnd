package com.example.konkurs.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "posting")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PostingEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private LocalDate date;
	
	@OneToMany(mappedBy = "posting", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ResponsibilitiesEntity> responsibilities;
	
	@OneToMany(mappedBy = "posting", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<RequirementsEntity> requirements;
	
	@OneToMany(mappedBy = "posting", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<OfferingEntity> offering;
	
	@OneToMany(mappedBy = "posting", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ApplicationEntity> applications;
	
	@Column
	private Boolean deleted;
	
	@Version
	private Integer version;

	public PostingEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<ResponsibilitiesEntity> getResponsibilities() {
		return responsibilities;
	}

	public void setResponsibilities(List<ResponsibilitiesEntity> responsibilities) {
		this.responsibilities = responsibilities;
	}

	public List<RequirementsEntity> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<RequirementsEntity> requirements) {
		this.requirements = requirements;
	}

	public List<OfferingEntity> getOffering() {
		return offering;
	}

	public void setOffering(List<OfferingEntity> offering) {
		this.offering = offering;
	}

	public List<ApplicationEntity> getApplications() {
		return applications;
	}

	public void setApplications(List<ApplicationEntity> applications) {
		this.applications = applications;
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
