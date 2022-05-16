package com.ibm.academy.apirest.controllers;

import com.ibm.academy.apirest.entities.Carrera;
import com.ibm.academy.apirest.entities.Persona;
import com.ibm.academy.apirest.entities.Profesor;
import com.ibm.academy.apirest.exceptions.NotFoundException;
import com.ibm.academy.apirest.services.PersonaDAO;
import com.ibm.academy.apirest.services.ProfesoresDAOImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/profesor")
public class ProfesorControl
{
    @Autowired
    @Qualifier("profesoresDAOImp")
    private PersonaDAO profesorDAO;

    /**
     * Enpoint guarda un objeto personna y tipo profesor en la DB
     * @param profesor objeto a guardar en la DB
     * @return objeto guardado
     * @author DYMS 15/05/2022
     */
    @PostMapping()
    private ResponseEntity<?> crearProfesor(@RequestBody Persona profesor)
    {
        Persona profesorGuardado = profesorDAO.guardar(profesor);
        return new ResponseEntity<Persona>(profesorGuardado,HttpStatus.CREATED);
    }

    /**
     * Enpoint busca en la DB el objeto persona y tipo profesor con el ID proporcionado
     * @param id identificador del objeto a buscar
     * @return objeto con el identificador proporcionado
     * @author DYMS 15/05/2022
     */
    @GetMapping("/search/id/{id}")
    private ResponseEntity<?> buscarPorID(@PathVariable Integer id)
    {
        Optional<Persona> oProfesor = profesorDAO.buscarPorId(id);
        if (!oProfesor.isPresent())
            throw new NotFoundException(String.format("Profesor conID %d no existe",id));
        return new ResponseEntity<Persona>(oProfesor.get(),HttpStatus.OK);
    }

    /**
     * Endpoint que muestra una lista de todos los objetos persona de tipo profesor en la DB
     * @return lista de objetos persona de tipo profesor en la DB
     * @author DYMS 15/05/2022
     */
    @GetMapping("listas/profesores")
    private ResponseEntity<?> buscarTodos()
    {
        List<Persona> profesores = (List<Persona>) profesorDAO.buscarTodos();
        if (profesores.isEmpty())
            throw new NotFoundException("Lista vacia");
        return new ResponseEntity<List<Persona>>(profesores,HttpStatus.OK);
    }

    /**
     * Endpoint que borra de la DB el objeto persona y tipo profesor con el ID proporcionado
     * @param id identificador del objeto a borrar
     * @return
     * @author DYMS 15/05/2022
     */
    @DeleteMapping("/del/id/{id}")
    private ResponseEntity<?> borrarProfesorPorId(@PathVariable Integer id)
    {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        Optional<Persona> oProfesor = profesorDAO.buscarPorId(id);

        if (!oProfesor.isPresent())
            throw new NotFoundException(String.format("Preofesor con ID %d no encontrado", id));

        profesorDAO.eliminarPorId(id);
        respuesta.put("OK", "profesor con ID " + id + " eliminad@");
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
    }

    /**
     * Endpoint que actualiza la informacion del objeto persona y tipo profesor con el ID proporcionado, con la informacion de otro objeto persona tipo profesor
     * @param id identificadpr del objeto a modificar
     * @param profesor objeto con la nueva informacion
     * @return objeto actualizado
     * @author DYMS 15/05/2022
     */
    @PutMapping("/upd/id/{id}")
    public ResponseEntity<?> actualizarPersona(@PathVariable Integer id, @RequestBody Persona profesor)
    {
        Optional<Persona> oProfesor = profesorDAO.buscarPorId(id);
        if (!oProfesor.isPresent())
            throw new NotFoundException(String.format("Profesor con ID %i no existe",id));
        Persona profesorNuevo = ((ProfesoresDAOImp)profesorDAO).actualizarProfesor(oProfesor.get(),profesor);
        return new ResponseEntity<Persona>(profesorNuevo,HttpStatus.OK);
    }

}
