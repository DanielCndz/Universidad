package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Aula;
import com.ibm.academy.apirest.enums.Pizarron;
import com.ibm.academy.apirest.repositories.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class AulaDAOImp extends GenericoDAOImp<Aula, AulaRepository> implements AulaDAO
{
    @Autowired
    public AulaDAOImp(AulaRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Aula> buscarAulasPorPizarron(Pizarron pizarron) {
        return ((AulaDAO)repository).buscarAulasPorPizarron(pizarron);
    }

    @Override
    public Iterable<Aula> buscarAulasPorNombrePabellon(String nombrePabellon) {
        return ((AulaDAO)repository).buscarAulasPorNombrePabellon(nombrePabellon);
    }

    @Override
    public Optional<Aula> buscarAulaPorNumero(Integer numero) {
        return ((AulaDAO)repository).buscarAulaPorNumero(numero);
    }

}
