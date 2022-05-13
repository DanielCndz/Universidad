package com.ibm.academy.apirest.repositories;

import com.ibm.academy.apirest.entities.Carrera;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreraRepository extends CrudRepository<Carrera,Integer>
{

}
