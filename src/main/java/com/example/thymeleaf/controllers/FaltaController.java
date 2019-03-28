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
import com.example.thymeleaf.models.entity.Falta;
import com.example.thymeleaf.models.service.IAlumnoService;

@Controller()
@RequestMapping("/alumno")
@SessionAttributes("falta")
public class FaltaController {
	
	@Autowired
	IAlumnoService alumnoService;
	
	@GetMapping("/falta/{id}")
	public String crearFalta(@PathVariable(name="id") Long id, Model model) {
		
		if(id != null) {
			Alumno alumno = alumnoService.findOne(id);
			Falta falta = new Falta();
			falta.setAlumno(alumno);
			model.addAttribute("falta", falta);
		}
		return "falta/formFalta";
	}
	
	
	@GetMapping("/editar/{id}")
	public String editarFalta(@PathVariable(name="id") Long id, Model model, RedirectAttributes flash) {
		if(id != null) {
			Falta falta = alumnoService.findFalta(id);
			if(falta != null) {
				model.addAttribute("falta", falta);
			}else {
				flash.addFlashAttribute("info", "No se encontro nada =(");
			}
		}
		return "falta/formFalta";
	}
	
	@PostMapping("/falta")
	public String guardarFalta(@Valid Falta falta, BindingResult result,  RedirectAttributes flash, SessionStatus status) {
		
		if(result.hasErrors()) {
			return "falta/formFalta";
		}
		if(falta != null) {
			String mensaje = (falta.getId() != null)? "Se ha editado la falta/retardo al alumno con la matricula: " : "Se ha agregado una falta al alumno con la matricula: ";
			alumnoService.save(falta);
			status.setComplete();
			flash.addFlashAttribute("info", mensaje + falta.getAlumno().getId());
		}
		return "redirect:/home";
	}
	
	@GetMapping("/historialFaltas/{id}")
	public String historialFaltas(@PathVariable(name="id") Long id, Model model) {
		if(id != null) {
			Alumno alumno = alumnoService.findOne(id);
			List<Falta> faltasAndRetardos = alumnoService.findFaltasAndRetardos(id);
			model.addAttribute("faltas", faltasAndRetardos);
			model.addAttribute("alumno", alumno);
		}
		
		return "falta/historialFalta";
	}

}
