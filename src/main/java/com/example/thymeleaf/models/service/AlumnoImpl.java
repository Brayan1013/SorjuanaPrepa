package com.example.thymeleaf.models.service;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thymeleaf.dao.IAlumnoDao;
import com.example.thymeleaf.dao.IFaltaDao;
import com.example.thymeleaf.dao.IReporteDao;
import com.example.thymeleaf.models.entity.Alumno;
import com.example.thymeleaf.models.entity.Falta;
import com.example.thymeleaf.models.entity.Reporte;
@Service
public class AlumnoImpl implements IAlumnoService {
	
	@Autowired
	IAlumnoDao alumnoDao;
	
	@Autowired
	IReporteDao reporteDao;
	
	@Autowired
	IFaltaDao faltaDao;
	
	@Override
	public void save(Alumno alumno) {
		alumnoDao.save(alumno);
	}
	@Override
	public Alumno findOne(Long id) {
		return alumnoDao.findById(id).orElse(null);
	}
	@Override
	public void delete(Long id) {
		Alumno alumno = findOne(id);
		alumnoDao.delete(alumno);
		
	}
	@Override
	public void save(Reporte reporte) {
		reporteDao.save(reporte);
	}
	
	
	@Override
	public Reporte findReporte(Long id) {
		// TODO Auto-generated method stub
		return reporteDao.findById(id).orElse(null);
	}
	
	@Override
	public List<Reporte> findReportes(Long id, String ciclo) {
		// TODO Auto-generated method stub
		return reporteDao.findReporte(id, ciclo);
	}
	@Override
	public List<Reporte> findAllReportes(Long id) {
		// TODO Auto-generated method stub
		return reporteDao.findAllReportes(id);
	}
	@Override
	public void save(Falta falta) {
		faltaDao.save(falta);
	}
	@Override
	public List<Falta> findAllFaltas(Long id, String ciclo) {
		// TODO Auto-generated method stub
		return faltaDao.findAllFaltas(id, ciclo);
	}
	@Override
	public List<Falta> findAllRetardos(Long id, String ciclo) {
		// TODO Auto-generated method stub
		return faltaDao.findAllRetados(id, ciclo);
	}
	@Override
	public List<Falta> findFaltasAndRetardos(Long id) {
		// TODO Auto-generated method stub
		return faltaDao.findFaltasAndRetardos(id);
	}
	@Override
	public Falta findFalta(Long id) {
		// TODO Auto-generated method stub
		return faltaDao.findById(id).orElse(null);
	}
	

}
