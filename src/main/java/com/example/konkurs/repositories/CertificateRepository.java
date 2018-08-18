package com.example.konkurs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.konkurs.entities.ApplicationEntity;
import com.example.konkurs.entities.CertificateEntity;

public interface CertificateRepository extends CrudRepository<CertificateEntity, Integer> {
	
	List<CertificateEntity> findByApplication(ApplicationEntity application);

}
