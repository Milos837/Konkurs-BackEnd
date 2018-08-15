package com.example.konkurs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.konkurs.entities.PostingEntity;
import com.example.konkurs.entities.ResponsibilitiesEntity;

public interface ReponsibilitiesRepository extends CrudRepository<ResponsibilitiesEntity, Integer>{
	
	void deleteByPosting(PostingEntity posting);
	
	List<ResponsibilitiesEntity> findByPosting(PostingEntity posting); 

}
