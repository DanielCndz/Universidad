package com.ibm.academy.apirest.repositories;

import com.ibm.academy.apirest.entities.Carrera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreraRepository extends CrudRepository<Carrera,Integer>
{
    public Iterable<Carrera> findCarreraByCantidadAnios(Integer cantidadAnios);
    @Query("select c from Carrera c join fetch c.profesores p where upper(p.nombre) =?1 and upper(p.apellido)=?2")
    public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
}
