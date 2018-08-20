package com.example.konkurs.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.konkurs.entities.ApplicationEntity;
import com.example.konkurs.entities.AttachmentEntity;

public interface AttachmentRepository extends CrudRepository<AttachmentEntity, Integer> {
	
	AttachmentEntity findByApplication(ApplicationEntity application);
	
	Boolean existsByApplication(ApplicationEntity application);

}
