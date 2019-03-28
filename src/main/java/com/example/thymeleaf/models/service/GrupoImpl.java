package com.example.thymeleaf.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thymeleaf.dao.IGrupoDao;
import com.example.thymeleaf.models.entity.Grupo;


@Service
public class GrupoImpl implements IGrupoService {
	
	@Autowired
	IGrupoDao grupoDao;

	@Override
	public void crearGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		grupoDao.save(grupo);

	}

	@Override
	public List<Grupo> findAll() {
		return (List<Grupo>) grupoDao.findAll();
	}

	@Override
	public Grupo findOne(Long id) {
		return grupoDao.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		Grupo grupo = findOne(id);
		grupoDao.delete(grupo);
		
	}

	

	

}
