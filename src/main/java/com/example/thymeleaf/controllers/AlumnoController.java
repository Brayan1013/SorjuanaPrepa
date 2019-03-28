package com.example.thymeleaf.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.example.thymeleaf.models.entity.Grupo;
import com.example.thymeleaf.models.entity.Reporte;
import com.example.thymeleaf.models.service.IAlumnoService;
import com.example.thymeleaf.models.service.IGrupoService;


@Controller
@SessionAttributes("alumno")
public class AlumnoController {

	@Autowired
	IGrupoService grupoService;

	@Autowired
	IAlumnoService alumnoService;

	@GetMapping(value = "/detalleAlumno")
	public String detalleAlumno(Model model, RedirectAttributes flash, HttpServletRequest httpServletRequest) {

		String matricula = (httpServletRequest.getParameter("matricula"));

		if (matricula != null) {
			Long id = Long.parseLong(matricula);
			Alumno alumno = alumnoService.findOne(id);
			if (alumno != null) {
				//Obtenemos los reportes de cada alumno por su Id
				List<Reporte> reportes = alumnoService.findReportes(id, alumno.getGrupo().getCiclo());
				//obtener numero de faltas de alumnos por id
				Integer numeroFaltas = obtenerNumeroFaltas(id, alumno.getGrupo().getCiclo());
				//obtener numero de retardos de alumnos por id
				Integer numeroRetardos = obtenerNumeroRetardos(id, alumno.getGrupo().getCiclo());
				model.addAttribute("alumno", alumno);
				model.addAttribute("reportes", reportes);
				model.addAttribute("faltas", numeroFaltas);
				model.addAttribute("retardos", numeroRetardos);
			} else {
				flash.addFlashAttribute("danger", "No se ha encontrado a ningun alumno con la matricula: " + id);
				return "redirect:/home";
			}
		} else {
			flash.addFlashAttribute("danger", "Proporciona una matricula");
			return "redirect:/home";
		}

		return "/detalleAlumno";
	}

	@GetMapping(value = "/grupo/{id}")
	public String crearAlumno(@PathVariable(name = "id") Long id, Model model, RedirectAttributes flash,
			HttpServletRequest request) {
		Grupo grupo = grupoService.findOne(id);
		if (grupo != null) {
			Alumno alumno = new Alumno();

			// dar de alta el grupo en el alumno
			alumno.setGrupo(grupo);
			model.addAttribute("alumno", alumno);
			model.addAttribute("titulo", "Crear un nuevo alumno");
		} else {
			flash.addFlashAttribute("danger", "El grupo no existe");
			return "redirect:/home";
		}

		return "/crearAlumno";
	}

	@RequestMapping(value = "/eliminarAlumno/{id}")
	public String eliminarAlumno(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		alumnoService.delete(id);
		flash.addFlashAttribute("info", "Se ha eliminado el alumno con éxito");
		return "redirect:/home";
	}

	@GetMapping(value = "/editarAlumno/{id}")
	public String editarAlumno(@PathVariable(name = "id") Long id, Model model) {
		if (id != null) {
			Alumno alumno = alumnoService.findOne(id);
			model.addAttribute("alumno", alumno);
			model.addAttribute("titulo", "Editar alumno");

		}

		return "/crearAlumno";
	}

	@PostMapping(value = "/guardarAlumno")
	public String guardarAlumno(@Valid Alumno alumno, BindingResult result, SessionStatus status, Model model,
			RedirectAttributes flash, HttpServletRequest request) {

		Long idGrupo = Long.parseLong(request.getParameter("grupo"));
		Grupo grupo = grupoService.findOne(idGrupo);
		if (grupo == null) {
			flash.addFlashAttribute("danger", "No existe el grupo con el id " + idGrupo);
			return "redirect:/listarGrupo";
		}

		if (result.hasErrors()) {
			System.out.print(result.getErrorCount());
			model.addAttribute("titulo", "Crear un nuevo alumno");
			return "/crearAlumno";

		}

		alumnoService.save(alumno);
		status.setComplete();
		flash.addFlashAttribute("success", "Alumno creado/modificado correctamente");
		return "redirect:/home";
	}
	
	
	//obtenemos las faltas de los alumnos
	private Integer obtenerNumeroFaltas(Long id, String ciclo){
		List<Falta> faltas = alumnoService.findAllFaltas(id, ciclo);
		return faltas.size();
	}
	
	private Integer obtenerNumeroRetardos(Long id, String ciclo){
		List<Falta> retardos = alumnoService.findAllRetardos(id, ciclo);
		return retardos.size();
	}

}
