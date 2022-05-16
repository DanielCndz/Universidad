package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Carrera;

import java.util.Optional;

public interface CarreraDAO extends GenericoDAO<Carrera>
{
    public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
    public Carrera actualizar(Carrera carreraEncontrada,Carrera carrera);
}
