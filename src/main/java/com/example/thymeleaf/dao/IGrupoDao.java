package com.example.thymeleaf.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.thymeleaf.models.entity.Grupo;

public interface IGrupoDao extends CrudRepository<Grupo, Long> {

}
