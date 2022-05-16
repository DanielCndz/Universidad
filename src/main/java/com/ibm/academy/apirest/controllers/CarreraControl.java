package com.ibm.academy.apirest.controllers;

import com.ibm.academy.apirest.entities.Carrera;
import com.ibm.academy.apirest.exceptions.BadRequestException;
import com.ibm.academy.apirest.exceptions.NotFoundException;
import com.ibm.academy.apirest.services.CarreraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carrera")
public class CarreraControl
{
    @Autowired
    private CarreraDAO carreraDAO;

    /**
     * Endpoint muestra un listado de  todas las carreras guardadas
     * @return  lista de las carreras guardadas
     * @BadRequestException En caso de que la DB este vacia
     * @author DYMS 15/05/2022
     */
    @GetMapping("/lista/carreras")
    public List<Carrera> buscarTodas()
    {
        List<Carrera> carreras = (List<Carrera>)carreraDAO.buscarTodos();
        if (carreras.isEmpty())
            throw new BadRequestException("No existen Carreras");

        return carreras;
    }

    /**
     * Endpoint que busca una carrera por su ID
     * @param id identificador de la carrera a buscar
     * @return Carrera con el identificador señalado
     * @NotFoundException En casode no encontrar la carrera con ese ID
     * @author DYMS 15/05/2022
     */
    @GetMapping("/id/{id}")
    public Carrera buscarCarreraPorId(@PathVariable Integer id)
    {
        Optional<Carrera> oCarrera= carreraDAO.buscarPorId(id);
        if (!oCarrera.isPresent())
            throw new NotFoundException(String.format("Carrera con ID %d encontrada",id));
        return oCarrera.get();
    }

    /**
     * Endpoint que guarda una nueva carrera
     * @param carrera Objeto carrera a guardar
     * @param result Objeto carrera guardado
     * @return La carrera guardada en la DB
     * @author DYMS15/05/2022
     */
    @PostMapping()
    public ResponseEntity<?> guardarCarrera(@Valid @RequestBody Carrera carrera, BindingResult result)
    {
        Map<String,Object> validaciones = new HashMap<String,Object>();
        if(result.hasErrors())
        {
            List<String> listaErrores = result.getFieldErrors().stream().map(errores->"Campo: "+errores.getField()+" "+errores.getDefaultMessage()).collect(Collectors.toList());
            validaciones.put("Lista Errores",listaErrores);
            return new ResponseEntity<Map<String,Object>>(validaciones,HttpStatus.BAD_REQUEST);
        }
        Carrera carreraGuardada = carreraDAO.guardar(carrera);

        return new ResponseEntity<Carrera>(carreraGuardada, HttpStatus.CREATED);
    }

    /**
     * Endpoint para actualizar un objeto de tipo carrera
     * @param carreraId : id de la  carrera a modificar
     * @param carrera : Objeto carrera con informacion a modificar
     * @return carrera actualizada
     * @NotFoundException En caso de que la carrera no sea encontrada
     * @author DYMS 15/05/2022
     */
    @PutMapping("/upd/carreraid/{carreraId}")
    public ResponseEntity<?> actualizarCarrera(@PathVariable Integer carreraId , @RequestBody Carrera carrera)
    {
        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);

        if (!oCarrera.isPresent())
            throw new NotFoundException(String.format("La carrera con ID %d no se encontro",carreraId));

        Carrera  carreraActualizada = carreraDAO.actualizar(oCarrera.get(),carrera);

        return new ResponseEntity<Carrera>(carreraActualizada,HttpStatus.OK);
    }

    /**
     * Enpoint borra un Objeto carrera por si ID
     * @param id identificador de la carrera borrar
     * @return
     */
    @DeleteMapping("/carreraId/{id}")
    public ResponseEntity<?> eliminarCarrera(@PathVariable Integer id)
    {
        Map<String,Object> respuesta = new HashMap<String,Object>();
        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(id);

        if (!oCarrera.isPresent())
            throw new NotFoundException(String.format("La carrera con ID %d no encontrada",id));

        carreraDAO.eliminarPorId(id);
        respuesta.put("OK","carrera con ID "+ id +" eliminada");
        return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.ACCEPTED);
    }
}
