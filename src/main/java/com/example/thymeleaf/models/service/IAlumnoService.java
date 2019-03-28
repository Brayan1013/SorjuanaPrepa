package com.example.thymeleaf.models.service;




import java.util.List;



import com.example.thymeleaf.models.entity.Alumno;
import com.example.thymeleaf.models.entity.Falta;
import com.example.thymeleaf.models.entity.Reporte;

public interface IAlumnoService {
	
	public void save(Alumno alumno);
	public Alumno findOne(Long id);
	public void delete(Long id);
	
	
	//REPORTES
	//Guardando un reporte
	public void save(Reporte reporte);
	//listar Reporte 
	public List<Reporte> findReportes(Long id, String ciclo);
	public List<Reporte> findAllReportes(Long id);
	//encontrar un reporte
	public Reporte findReporte(Long id);
	
	//FALTAS
	//guardadndo una falta
	public void save(Falta falta);
	//Listado de faltas de alumno
	public List<Falta> findAllFaltas(Long id, String ciclo);
	//Listado de retardos de alumno
	public List<Falta> findAllRetardos(Long id, String ciclo);
	//Historial completo de alumno
	public List<Falta> findFaltasAndRetardos(Long id);
	//encontra un reporte
	public Falta findFalta(Long id);
}
