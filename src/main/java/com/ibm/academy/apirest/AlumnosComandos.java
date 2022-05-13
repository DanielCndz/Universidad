package com.ibm.academy.apirest;

import com.ibm.academy.apirest.entities.Alumno;
import com.ibm.academy.apirest.entities.Carrera;
import com.ibm.academy.apirest.entities.Persona;
import com.ibm.academy.apirest.services.AlumnoDAO;
import com.ibm.academy.apirest.services.CarreraDAO;
import com.ibm.academy.apirest.services.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AlumnosComandos implements CommandLineRunner
{
    @Autowired
    private CarreraDAO carreraDAO;

    @Autowired
    @Qualifier("alumnoDAOImp")
    private PersonaDAO personaDAO;

    @Autowired
    private AlumnoDAO alumnoDAO;

    @Override
    public void run(String... args) throws Exception
    {
        /*Optional<Carrera> ingSistemas = carreraDAO.buscarPorId(1);

        Iterable<Persona> alumnos = personaDAO.buscarTodos();
        alumnos.forEach(alumno->((Alumno)alumno).setCarrera(ingSistemas.get()));
        alumnos.forEach(alumno->personaDAO.guardar(alumno));
        */
        Optional<Persona> alumno = alumnoDAO.buscarPorId(1);
        /*
        Optional<Persona> personaDni = personaDAO.buscarPorDni(alumno.get().getDni());
        System.out.println("Dni = " + personaDni.toString());
        */
        Iterable<Persona> personaApellido = personaDAO.buscarPorApellido(alumno.get().getApellido());
        personaApellido.forEach(System.out::println);

    }
}
