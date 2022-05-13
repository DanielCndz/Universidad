package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Persona;
import com.ibm.academy.apirest.repositories.PersonaRepository;

import java.util.Optional;

public class PersonaDAOImp extends GenericoDAOImp<Persona, PersonaRepository> implements PersonaDAO
{
    public PersonaDAOImp(PersonaRepository repository)
    {
        super(repository);
    }

    @Override
    public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido) {
        return repository.buscarPorNombreYApellido(nombre,apellido);
    }

    @Override
    public Optional<Persona> buscarPorDni(String dni) {
        return repository.buscarPorDni(dni);
    }

    @Override
    public Iterable<Persona> buscarPorApellido(String apellido) {
        return repository.buscarPorApellido(apellido);
    }
}
