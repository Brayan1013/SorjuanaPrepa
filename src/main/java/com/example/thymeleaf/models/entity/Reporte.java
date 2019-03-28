package com.example.thymeleaf.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Reporte implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Ingresa el nombre del catedratico")
	private String catedratico;
	
	@NotBlank(message="Ingresa el nombre del la materia")
	private String materia;

	@Column(length = 80)
	@NotBlank(message="Ingresa la incidencia")
	private String incidencia;
	
	@NotBlank(message="Ingresa la hora")
	private String hora;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message="Ingresa una fecha")
	private Date fecha;
	
	@NotBlank(message="Ingresa el semestre actual")
	private String semestre;
	
	@NotBlank(message="Ingresa el ciclo escolar actual")
	private String ciclo;
	
	@NotBlank(message="Ingresa un grupo")
	private String grupo;

	@ManyToOne(fetch = FetchType.LAZY)
	private Alumno alumno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCatedratico() {
		return catedratico;
	}

	public void setCatedratico(String catedratico) {
		this.catedratico = catedratico;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(String incidencia) {
		this.incidencia = incidencia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}





	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
