package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Carrera;
import com.ibm.academy.apirest.entities.Persona;

import java.util.Set;

public interface ProfesoresDAO extends PersonaDAO
{
    public Iterable<Persona> findProferoresByCarrera(String carrera);
    public Persona actualizarProfesor(Persona profesorEncontrado,Persona profesor);
}
