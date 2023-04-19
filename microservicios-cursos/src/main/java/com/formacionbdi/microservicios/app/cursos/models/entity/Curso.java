package com.formacionbdi.microservicios.app.cursos.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;

@Entity
@Table(name="cursos")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = {"cursoAlumnos"}, allowSetters = true)
	private List<CursoAlumno> cursoAlumnos;
	
	//@OneToMany(fetch = FetchType.LAZY) Versión con alumnos en la misma ddbb
	@Transient
	private List<Alumno> alumnos;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Examen> examenes;

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	public Curso() {
		this.alumnos = new ArrayList<>();
		this.examenes = new ArrayList<>();
		this.cursoAlumnos = new ArrayList<>();
	}

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

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	public void addAlumno(Alumno alumno) {
		this.alumnos.add(alumno);
	}

	public void removeAlumno(Alumno alumno) {
		this.alumnos.remove(alumno);
	}

	public List<Examen> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<Examen> examenes) {
		this.examenes = examenes;
	}

	public void addExamen(Examen examen) {
		this.examenes.add(examen);
	}
	
	public void removeExamen(Examen examen) {
		this.examenes.remove(examen);
	}

	public List<CursoAlumno> getCursoAlumnos() {
		return cursoAlumnos;
	}

	public void setCursoAlumnos(List<CursoAlumno> cursoAlumnos) {
		this.cursoAlumnos = cursoAlumnos;
	}

	public void addCursoAlumnos(CursoAlumno cursoAlumno) {
		this.cursoAlumnos.add(cursoAlumno);
	}

	public void removeCursoAlumnos(CursoAlumno cursoAlumno) {
		this.cursoAlumnos.remove(cursoAlumno);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}

		if(!(obj instanceof Curso)) {
			return false;
		}

		Curso a = (Curso) obj;

		return this.id != null && this.id.equals(a.getId());
	}
}
