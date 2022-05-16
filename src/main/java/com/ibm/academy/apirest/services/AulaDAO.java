package com.ibm.academy.apirest.services;

import com.ibm.academy.apirest.entities.Aula;
import com.ibm.academy.apirest.entities.Persona;
import com.ibm.academy.apirest.enums.Pizarron;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AulaDAO extends GenericoDAO<Aula>
{
    public Iterable<Aula> buscarAulasPorPizarron(Pizarron pizarron);
    public Iterable<Aula> buscarAulasPorNombrePabellon(String nombrePabellon);
    public Optional<Aula> buscarAulaPorNumero(Integer numero);
}
