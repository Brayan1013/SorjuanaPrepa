package com.example.thymeleaf.models.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ALUMNOS")
public class Alumno {
	
	@Id
	@NotNull(message="Necesitas proporcionar la matricula del alumno")
	private Long id;
	
	@NotEmpty(message="Necesitas proporcionar el nombre del alumno")
	private String nombre;
	@NotEmpty(message="Necesitas proporcionar los apellidos del alumno")
	private String apellido;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Grupo grupo;
	
	//falta inicializar 
	@OneToMany(mappedBy="alumno", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Reporte> reportes;
	
	//falta inicializar
	@OneToMany(mappedBy="alumno", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Falta> falta;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Reporte> getReportes() {
		return reportes;
	}

	public void setReportes(List<Reporte> reportes) {
		this.reportes = reportes;
	}

	public List<Falta> getFalta() {
		return falta;
	}

	public void setFalta(List<Falta> falta) {
		this.falta = falta;
	}
	
	
	
	
	
	
	
}
