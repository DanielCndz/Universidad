package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Carrera;
import com.ibm.academy.apirest.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class CarreraDAOImp implements CarreraDAO
{
    @Autowired
    private CarreraRepository  carreraRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Carrera> buscarPorId(Integer id) {
        return carreraRepository.findById(id);
    }

    @Override
    @Transactional
    public Carrera guardar(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrera> buscarTodos() {
        return carreraRepository.findAll();
    }

    @Override
    public void eliminarPorId(Integer id) {
        carreraRepository.deleteById(id);
    }
}
