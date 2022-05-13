package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Pabellon;
import org.springframework.stereotype.Service;

@Service
public interface PabellonDAO extends GenericoDAO<Pabellon>
{
    public Iterable<Pabellon> buscarPabellonesPorLocalidad(String localidad);
    public Iterable<Pabellon> buscarPabellonesPorNombre(String nombre);

}
