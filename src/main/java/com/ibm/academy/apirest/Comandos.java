package com.ibm.academy.apirest;

import com.ibm.academy.apirest.entities.Carrera;
import com.ibm.academy.apirest.services.CarreraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Comandos implements CommandLineRunner
{
    @Autowired
    private CarreraDAO carreraDAO;
    @Override
    public void run(String... args) throws Exception
    {
        /*Carrera finanzas = new Carrera(null,"Ingenieria en finanzas",20,3);
        Carrera carreraGuardada = carreraDAO.guardar(finanzas);
        System.out.println(carreraGuardada.toString());*/
    }
}
