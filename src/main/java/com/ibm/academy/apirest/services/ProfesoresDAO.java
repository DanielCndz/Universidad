package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Persona;

public interface ProfesoresDAO extends PersonaDAO
{
    public Iterable<Persona> findProferoresByCarrera(String carrera);
}
