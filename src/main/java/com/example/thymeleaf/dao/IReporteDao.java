package com.example.thymeleaf.dao;




import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.thymeleaf.models.entity.Reporte;

public interface IReporteDao extends CrudRepository<Reporte, Long> {
	
	@Query(value="SELECT * FROM reporte r WHERE r.alumno_id = ?1 AND r.ciclo=?2 ORDER BY r.id DESC limit 10;", nativeQuery = true)
	public List<Reporte> findReporte(Long id, String ciclo);
	
	@Query(value="SELECT * FROM reporte r WHERE r.alumno_id = ?1", nativeQuery = true)
	public List<Reporte> findAllReportes(Long id);
	
	

}
