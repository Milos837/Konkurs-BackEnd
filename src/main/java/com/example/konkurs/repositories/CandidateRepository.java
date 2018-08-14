package com.example.konkurs.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.konkurs.entities.CandidateEntity;

public interface CandidateRepository extends CrudRepository<CandidateEntity, Integer> {

}
