package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Pabellon;
import com.ibm.academy.apirest.repositories.PabellonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PabellonDAOImp extends GenericoDAOImp<Pabellon, PabellonRepository> implements PabellonDAO
{
    @Autowired
    public PabellonDAOImp(PabellonRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Iterable<Pabellon> buscarPabellonesPorLocalidad(String localidad) {
        return ((PabellonDAO)repository).buscarPabellonesPorLocalidad(localidad);
    }

    @Override
    @Transactional
    public Iterable<Pabellon> buscarPabellonesPorNombre(String nombre) {
        return ((PabellonDAO)repository).buscarPabellonesPorNombre(nombre);
    }
}
