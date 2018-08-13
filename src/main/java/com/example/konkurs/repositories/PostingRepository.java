package com.example.konkurs.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.konkurs.entities.PostingEntity;

public interface PostingRepository extends CrudRepository<PostingEntity, Integer> {

}
