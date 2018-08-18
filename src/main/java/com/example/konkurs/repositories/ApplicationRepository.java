package com.example.konkurs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.konkurs.entities.ApplicationEntity;
import com.example.konkurs.entities.PostingEntity;

public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Integer> {
	
	List<ApplicationEntity> findByPosting(PostingEntity posting);

}
