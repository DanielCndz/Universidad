package com.ibm.academy.apirest.repositories;

import com.ibm.academy.apirest.entities.Aula;
import com.ibm.academy.apirest.enums.Pizarron;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AulaRepository extends CrudRepository<Aula,Integer>
{
    @Query("select a from Aula a where upper(a.pizarron) = upper(?1) ")
    public Iterable<Aula> buscarAulasPorPizarron(Pizarron pizarron);

    @Query("select a from Aula a join fetch a.pabellon p where upper(p.nombre) = upper(?1)")
    public Iterable<Aula> buscarAulasPorNombrePabellon(String nombrePabellon);

    @Query("select a from Aula a where a.numeroAula = ?1")
    public Optional<Aula> buscarAulaPorNumero(Integer numero);
}
