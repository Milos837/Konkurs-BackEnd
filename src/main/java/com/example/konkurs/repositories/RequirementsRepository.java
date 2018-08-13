package com.example.konkurs.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.konkurs.entities.PostingEntity;
import com.example.konkurs.entities.RequirementsEntity;

public interface RequirementsRepository extends CrudRepository<RequirementsEntity, Integer> {
	
	void deleteByPosting(PostingEntity posting);

}
