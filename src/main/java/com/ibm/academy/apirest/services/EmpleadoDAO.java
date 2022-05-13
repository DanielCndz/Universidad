package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Empleado;
import com.ibm.academy.apirest.entities.Persona;
import com.ibm.academy.apirest.enums.TipoEmpleado;

public interface EmpleadoDAO extends PersonaDAO
{
    public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);

}
