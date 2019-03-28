package com.example.thymeleaf.dao;


import org.springframework.data.repository.CrudRepository;

import com.example.thymeleaf.models.entity.Alumno;

public interface IAlumnoDao extends CrudRepository<Alumno, Long> {
	
	

}
