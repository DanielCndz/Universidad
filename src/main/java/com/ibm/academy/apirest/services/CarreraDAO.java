package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Carrera;

import java.util.Optional;

public interface CarreraDAO
{
    public Optional<Carrera> buscarPorId(Integer id);
    public Carrera guardar(Carrera carrera);
    public Iterable<Carrera> buscarTodos();
    public void eliminarPorId(Integer id);
}
