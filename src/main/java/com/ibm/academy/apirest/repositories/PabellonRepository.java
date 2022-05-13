package com.ibm.academy.apirest.repositories;

import com.ibm.academy.apirest.entities.Pabellon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PabellonRepository extends CrudRepository<Pabellon,Integer>
{
    @Query("select p from Pabellon p where upper(p.direccion.localidad) = upper(?1)")
    public Iterable<Pabellon> buscarPabellonesPorLocalidad(String localidad);

    @Query("select p from Pabellon  p where upper(p.nombre) = upper(?1) ")
    public Iterable<Pabellon> buscarPabellonesPorNombre(String nombre);
}
