package com.example.konkurs.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.konkurs.entities.ApplicationEntity;
import com.example.konkurs.entities.CvEntity;

public interface CvRepository extends CrudRepository<CvEntity, Integer> {
	
	CvEntity findByApplication(ApplicationEntity application);
	
	Boolean existsByApplication(ApplicationEntity application);

}
