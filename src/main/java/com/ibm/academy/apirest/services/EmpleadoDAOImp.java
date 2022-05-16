package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Empleado;
import com.ibm.academy.apirest.entities.Pabellon;
import com.ibm.academy.apirest.entities.Persona;
import com.ibm.academy.apirest.enums.TipoEmpleado;
import com.ibm.academy.apirest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmpleadoDAOImp extends PersonaDAOImp implements EmpleadoDAO {
    @Autowired
    public EmpleadoDAOImp(@Qualifier("repositorioEmpleados") PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado) {
        return ((EmpleadoDAO) repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
    }

    @Override
    public Persona asignarPabellon(Persona empleado, Pabellon pabellon) {
        ((Empleado) empleado).setPabellon(pabellon);
        return repository.save(empleado);
    }

    @Override
    public Persona actualizarEmpleado(Persona empleadoEncontrado, Persona empleado) {
        empleadoEncontrado.setNombre(empleado.getNombre());
        empleadoEncontrado.setApellido(empleado.getApellido());
        empleadoEncontrado.setDni(empleado.getDni());
        empleadoEncontrado.setDireccion(empleado.getDireccion());
        return empleadoEncontrado;
    }

}
