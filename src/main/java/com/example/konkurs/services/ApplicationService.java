package com.example.konkurs.services;

import com.example.konkurs.entities.ApplicationEntity;
import com.example.konkurs.entities.dto.ApplicationDto;

public interface ApplicationService {
	
	public ApplicationEntity save(Integer postingId, ApplicationDto newApplication);
	
	public ApplicationEntity delete(Integer applicationId);

}
