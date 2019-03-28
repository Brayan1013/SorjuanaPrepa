package com.example.thymeleaf.models.service;

import java.util.List;

import com.example.thymeleaf.models.entity.Grupo;

public interface IGrupoService {
	
	public Grupo findOne(Long id);
	public List<Grupo> findAll();
	public void crearGrupo(Grupo grupo);
	public void eliminar(Long id);

}
