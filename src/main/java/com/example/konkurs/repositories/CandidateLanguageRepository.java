package com.example.konkurs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.konkurs.entities.CandidateEntity;
import com.example.konkurs.entities.CandidateLanguageEntity;

public interface CandidateLanguageRepository extends CrudRepository<CandidateLanguageEntity, Integer> {
	
	List<CandidateLanguageEntity> findByCandidate(CandidateEntity candidate);

}
