package com.example.thymeleaf.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.thymeleaf.models.entity.Alumno;
import com.example.thymeleaf.models.entity.Reporte;
import com.example.thymeleaf.models.service.IAlumnoService;

@Controller
@RequestMapping("/alumno")
@SessionAttributes("reporte")
public class ReporteController {
	
	@Autowired
	IAlumnoService alumnoService;
	
	@GetMapping("/formReporte/{alumnoId}")
	public String crearReporte(@PathVariable(name="alumnoId") Long alumnoId, Model model) {
		if(alumnoId != null) {
			Alumno alumno = alumnoService.findOne(alumnoId);
			Reporte reporte = new Reporte();
			reporte.setAlumno(alumno);
			model.addAttribute("reporte", reporte);
		}
		
		return "reporte/formReporte";
	}
	
	@GetMapping("/detallesReporte/{id}")
	public String detalleReporte(@PathVariable(name="id")Long id, Model model) {
		if(id != null) {
			Reporte reporte = alumnoService.findReporte(id);
			model.addAttribute("reporte", reporte);
		}
		
		return "reporte/formReporte";
	}
	
	@PostMapping("/formReporte")
	public String guardarReporte(@Valid Reporte reporte, BindingResult result, SessionStatus status, RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			return "reporte/formReporte";
		}
		String mensaje = (reporte.getId() != null)? "Reporte editado correctamente": "Reporte asignado";
		
		alumnoService.save(reporte);
		flash.addFlashAttribute("info", mensaje + " al alumno: " + reporte.getAlumno().getId() + " Correctamente");
		status.setComplete();
		return "redirect:/home";
	}
	
	@GetMapping("/historial/{id}")
	public String historialReportes(@PathVariable(name="id") Long id, Model model) {
		if(id != null) {
			
			Alumno alumno = alumnoService.findOne(id);
			List<Reporte> reportes = alumnoService.findAllReportes(id);
			model.addAttribute("reportes", reportes);
			model.addAttribute("alumno", alumno);
		}
		
		return "reporte/historial";
		
	}
	
	
	

}
