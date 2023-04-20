package com.formacionbdi.microservicios.app.usuarios.services;

import java.util.List;

import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commons.services.CommonService;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.web.bind.annotation.PathVariable;

public interface AlumnoService extends CommonService<Alumno>{
	
	public List<Alumno> findByNombreOrApellido(String term);

	public Iterable<Alumno> findAllById(List<Long> ids);

	public void eliminarCursoAlumnoPorId(Long id);
}
