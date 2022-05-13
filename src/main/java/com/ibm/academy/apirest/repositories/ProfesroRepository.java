package com.ibm.academy.apirest.repositories;

import com.ibm.academy.apirest.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioProfesores")
public interface ProfesroRepository extends PersonaRepository
{
    @Query("select p from Profesor p join fetch p.carreras c where c.nombre = ?1")
    public Iterable<Persona> findProferoresByCarrera(String carrera);
}
