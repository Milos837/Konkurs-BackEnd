package com.example.konkurs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.konkurs.entities.CandidateEntity;
import com.example.konkurs.entities.EducationEntity;

public interface EducationRepository extends CrudRepository<EducationEntity, Integer> {
	
	List<EducationEntity> findByCandidate(CandidateEntity candidate);

}
