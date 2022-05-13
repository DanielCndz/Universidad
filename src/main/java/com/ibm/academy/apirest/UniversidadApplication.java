package com.ibm.academy.apirest;

import com.ibm.academy.apirest.entities.Alumno;
import com.ibm.academy.apirest.entities.Direccion;
import com.ibm.academy.apirest.entities.Persona;
import com.ibm.academy.apirest.services.AlumnoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniversidadApplication {
    public static void main(String[] args) {
        SpringApplication.run(UniversidadApplication.class, args);
        /*
        Optional<Carrera> ingSistemas = carreraDAO.buscarPorId(1);

        Iterable<Persona> alumnos = personaDAO.buscarTodos();
        alumnos.forEach(alumno->((Alumno)alumno).setCarrera(ingSistemas.get()));
        alumnos.forEach(alumno->personaDAO.guardar(alumno));
        */
    }
}
