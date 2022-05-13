package com.ibm.academy.apirest.repositories;

import com.ibm.academy.apirest.entities.Persona;
import com.ibm.academy.apirest.enums.TipoEmpleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioEmpleados")
public interface EmpleadoRepository extends PersonaRepository
{
    @Query("select e from Empleado e where e.tipoEmpleado = ?1")
    public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
}
