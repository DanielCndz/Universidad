package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Carrera;
import com.ibm.academy.apirest.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class CarreraDAOImp extends  GenericoDAOImp<Carrera,CarreraRepository> implements CarreraDAO
{
    @Autowired
    public CarreraDAOImp(CarreraRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido) {
        return ((CarreraDAO)repository).buscarCarrerasPorProfesorNombreYApellido(nombre, apellido);
    }
}
