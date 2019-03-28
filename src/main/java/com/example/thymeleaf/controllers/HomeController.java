package com.example.thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	
	@GetMapping(value="/home")
	public String home(Model model) {
		model.addAttribute("titulo", "Pagina de inicio");
		return "home";
	}
	
	/*@PostMapping(value="/buscarAlumno")
	public String buscar(@RequestParam(name="termino") String termino, Model model) {
		Long matricula = Long.parseLong(termino);
		model.addAttribute("matricula", matricula);
		return "/home";
	}*/
}
