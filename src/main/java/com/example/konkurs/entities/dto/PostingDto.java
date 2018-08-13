package com.example.konkurs.entities.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class PostingDto {
	
	@NotNull
	private String name;
	
	@NotNull
	private List<String> responsibilities;
	
	@NotNull
	private List<String> requirements;
	
	@NotNull
	private List<String> offering;

	public PostingDto() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getResponsibilities() {
		return responsibilities;
	}

	public void setResponsibilities(List<String> responsibilities) {
		this.responsibilities = responsibilities;
	}

	public List<String> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<String> requirements) {
		this.requirements = requirements;
	}

	public List<String> getOffering() {
		return offering;
	}

	public void setOffering(List<String> offering) {
		this.offering = offering;
	}
	
	

}
