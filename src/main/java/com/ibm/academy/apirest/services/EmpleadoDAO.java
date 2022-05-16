package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Pabellon;
import com.ibm.academy.apirest.entities.Persona;
import com.ibm.academy.apirest.enums.TipoEmpleado;

import java.util.Optional;

public interface EmpleadoDAO extends PersonaDAO
{
    public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
    public Persona asignarPabellon(Persona empleado, Pabellon pabellon);
    public Persona actualizarEmpleado(Persona empleadoEncontrado, Persona empleado);
}
