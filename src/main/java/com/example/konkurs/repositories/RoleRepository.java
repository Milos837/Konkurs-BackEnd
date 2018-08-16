package com.example.konkurs.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.konkurs.entities.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
	
	RoleEntity findByName(String name);

}
