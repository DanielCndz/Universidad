package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Carrera;
import com.ibm.academy.apirest.entities.Persona;

import java.util.Optional;

public interface AlumnoDAO extends PersonaDAO
{
    public Iterable<Carrera> findCarreraByCantidadAnios(Integer cantidadAnios);
    public Iterable<Persona> buscarAlumnoPorNombreCarrera(String nombre);
}
