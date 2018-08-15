package com.example.konkurs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.konkurs.entities.OfferingEntity;
import com.example.konkurs.entities.PostingEntity;

public interface OfferingRepository extends CrudRepository<OfferingEntity, Integer> {
	
	void deleteByPosting(PostingEntity posting);
	
	List<OfferingEntity> findByPosting(PostingEntity posting);

}
