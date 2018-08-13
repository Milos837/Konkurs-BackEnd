package com.example.konkurs.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.konkurs.entities.ApplicationEntity;

public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Integer> {

}
