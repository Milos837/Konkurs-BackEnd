package com.example.konkurs.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="attachment")
public class AttachmentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "application")
	public ApplicationEntity application;
	
	@Lob
	@Column(name = "cv", columnDefinition="MEDIUMBLOB")
	public byte[] cv;
	
	@Lob
	@Column(name = "motivation", columnDefinition="MEDIUMBLOB")
	public byte[] motivation;
	
	@Lob
	@Column(name = "coverLetter", columnDefinition="MEDIUMBLOB")
	public byte[] coverLetter;
	
	@Column
	private Boolean deleted;
	
	@Version
	private Integer version;

	public AttachmentEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ApplicationEntity getApplication() {
		return application;
	}

	public void setApplication(ApplicationEntity application) {
		this.application = application;
	}

	public byte[] getCv() {
		return cv;
	}

	public void setCv(byte[] file) {
		this.cv = file;
	}

	public byte[] getMotivation() {
		return motivation;
	}

	public void setMotivation(byte[] motivation) {
		this.motivation = motivation;
	}

	public byte[] getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(byte[] coverLetter) {
		this.coverLetter = coverLetter;
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
