package com.example.thymeleaf.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.thymeleaf.models.entity.Grupo;
import com.example.thymeleaf.models.service.IGrupoService;

@Controller
@SessionAttributes("grupo")
public class GrupoController {

	@Autowired
	IGrupoService grupoService;
	
	@GetMapping(value="listarGrupo")
	public String listarGrupo(Model model) {
		List<Grupo> grupos = grupoService.findAll();
		model.addAttribute("titulo", "Listado de todos los grupos actuales");
		model.addAttribute("grupos", grupos);
		return "listarGrupo";
	}

	@GetMapping(value = "crearGrupo")
	public String crearGrupo(Map<String, Object> model) {
		Grupo grupo = new Grupo();
		model.put("grupo", grupo);
		model.put("titulo", "formulario para crear un grupo");
		return "/crearGrupo";
	}
	
	@GetMapping(value="editar/{id}")
	public String editarGrupo(@PathVariable(name="id") Long id, Model model, RedirectAttributes flash) {
		
		Grupo grupo = null;
		grupo = grupoService.findOne(id);
		
		
		if(grupo != null) {
			model.addAttribute("titulo", "formulario para editar grupo");
			model.addAttribute("grupo", grupo);
		}else {
			model.addAttribute("titulo", "formulario para crear un grupo");
			return "redirect:/crearGrupo";
		}
		return "/crearGrupo";
	}
	
	
	@RequestMapping(value="eliminar/{id}")
	public String eliminar(@PathVariable(name="id") Long id, Model model, RedirectAttributes flash) {
		if(id > 0) {
			grupoService.eliminar(id);
			flash.addFlashAttribute("success", "Se ha eliminado correctamente");
		}
		return "redirect:/listarGrupo";
	}

	@RequestMapping(value ="crearGrupo", method=RequestMethod.POST)
	public String guardarGrupo(@Valid Grupo grupo, BindingResult result, Model model, SessionStatus sesion, RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "formulario para crear un grupo");
			return "/crearGrupo";
		}
		
		String mensaje = (grupo.getId() != null) ? "Grupo editado exitosamente": "Se ha creado correctamente el grupo";
		grupoService.crearGrupo(grupo);
		sesion.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/listarGrupo";
	}

}
