package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Persona;
import com.ibm.academy.apirest.entities.Profesor;
import com.ibm.academy.apirest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProfesoresDAOImp extends PersonaDAOImp implements ProfesoresDAO
{
    @Autowired
    public ProfesoresDAOImp(@Qualifier("repositorioProfesores") PersonaRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Persona> findProferoresByCarrera(String carrera) {
        return ((ProfesoresDAO)repository).findProferoresByCarrera(carrera);
    }
}
