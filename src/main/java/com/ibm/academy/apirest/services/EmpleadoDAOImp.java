package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Persona;
import com.ibm.academy.apirest.enums.TipoEmpleado;
import com.ibm.academy.apirest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Service;

@Service
public class EmpleadoDAOImp extends PersonaDAOImp implements EmpleadoDAO
{
    @Autowired
    public EmpleadoDAOImp(@Qualifier("repositorioEmpleados") PersonaRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado) {
        return ((EmpleadoDAO)repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
    }
}
