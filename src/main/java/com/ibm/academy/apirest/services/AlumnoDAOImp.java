package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Alumno;
import com.ibm.academy.apirest.entities.Carrera;
import com.ibm.academy.apirest.entities.Persona;
import com.ibm.academy.apirest.repositories.AlumnoRepository;
import com.ibm.academy.apirest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AlumnoDAOImp extends PersonaDAOImp implements AlumnoDAO
{
    @Autowired
        public AlumnoDAOImp(@Qualifier("repositorioAlumnos")PersonaRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Carrera> findCarreraByCantidadAnios(Integer cantidadAnios) {
        return ((AlumnoDAO)repository).findCarreraByCantidadAnios(cantidadAnios);
    }

    @Override
    public Iterable<Persona> buscarAlumnoPorNombreCarrera(String nombre)
    {
        return ((AlumnoDAO)repository).buscarAlumnoPorNombreCarrera(nombre);
    }
}
