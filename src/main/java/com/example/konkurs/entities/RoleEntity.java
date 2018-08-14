package com.example.konkurs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity
//@Table(name = "role")
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RoleEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer id;
	
	@Column(name = "role_name")
	private String name;

}
