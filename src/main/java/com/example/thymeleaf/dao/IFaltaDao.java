package com.example.thymeleaf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.thymeleaf.models.entity.Falta;


public interface IFaltaDao extends CrudRepository<Falta, Long> {
	
	//obtener las faltas
	@Query(value="SELECT * FROM faltas f WHERE f.alumno_id = ?1 AND f.ciclo = ?2 AND f.tipo = 'Falta'", nativeQuery = true)
	public List<Falta> findAllFaltas(Long id, String ciclo);
	
	//obtenr los retardos
	@Query(value="SELECT * FROM faltas f WHERE f.alumno_id = ?1 AND f.ciclo = ?2 AND f.tipo = 'Retardo'", nativeQuery = true)
	public List<Falta> findAllRetados(Long id, String ciclo);
	
	//obtenemos faltas y retardos
	@Query(value="SELECT * FROM faltas f WHERE f.alumno_id = ?1", nativeQuery = true)
	public List<Falta> findFaltasAndRetardos(Long id);
	
	
	
}
