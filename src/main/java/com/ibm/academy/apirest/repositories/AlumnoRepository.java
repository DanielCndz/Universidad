package com.ibm.academy.apirest.repositories;

import com.ibm.academy.apirest.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioAlumnos")
public interface AlumnoRepository extends PersonaRepository
{
    @Query("select a from Alumno a where a.carrera.nombre=?1")
    public Iterable<Persona> buscarAlumnoPorNombreCarrera(String nombre);
}
