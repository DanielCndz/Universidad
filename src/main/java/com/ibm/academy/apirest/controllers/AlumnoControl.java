package com.ibm.academy.apirest.controllers;

import com.ibm.academy.apirest.entities.Alumno;
import com.ibm.academy.apirest.entities.Carrera;
import com.ibm.academy.apirest.entities.Persona;
import com.ibm.academy.apirest.exceptions.NotFoundException;
import com.ibm.academy.apirest.services.AlumnoDAO;
import com.ibm.academy.apirest.services.CarreraDAO;
import com.ibm.academy.apirest.services.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/alumno")
public class AlumnoControl
{
    @Autowired
    @Qualifier("alumnoDAOImp")
    private PersonaDAO alumnoDAO;

    @Autowired
    private CarreraDAO carreraDAO;

    /**
     * Endpoint guarda un Objeto persona de tipo alumno
     * @param alumno
     * @return
     * @author DYMS 15/05/2022
     */
    @PostMapping()
    public ResponseEntity<Persona> crearAlumno(@RequestBody Persona alumno)
    {
        Persona alumnoGuardado = alumnoDAO.guardar(alumno);
        return new ResponseEntity<Persona>(alumnoGuardado,HttpStatus.CREATED);
    }

    /**
     * Enpoint lista alumnos en la DB
     * @return  lista de alumnos
     * @author DYMS15/05/2022
     */
    @GetMapping("/lista/alumnos")
    public ResponseEntity<?> buscarAlumnos()
    {
        List<Persona> alumnos = (List<Persona>) alumnoDAO.buscarTodos();
        if (alumnos.isEmpty())
            throw new NotFoundException("No existen alumnos registrados");
        return new ResponseEntity<List<Persona>>(alumnos, HttpStatus.OK);
    }

    /**
     * Endpoint busca un objeto persona de tipo alumnos con el ID proporcionado
     * @param id identificador del objeto en la DB
     * @return Objeto persona de tipo alumno con el ID proporcionado
     * @author DYMS15/05/2022
     */
    @GetMapping("/id/{id}")
    public Persona buscarAlumnosPorId(@PathVariable Integer id)
    {
        Optional<Persona> oAlumno = alumnoDAO.buscarPorId(id);
        if (!oAlumno.isPresent())
            throw new NotFoundException(String.format("El alumno con ID %d no encontrado",id));
        return oAlumno.get();
    }

    /**
     * Endpoint actualiza el objeto persona de tipo alumno con el ID proporcionado, con la informacion del alumno proporcionado
     * @param id identificador del objeto persona y tipo alumno que se va a modificar
     * @param alumno objeto persona y tipo alumno que contiene la nueva informacion
     * @return
     * @author DYMS15/05/2022
     */
    @PutMapping("/upd/alumnoid/{id}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Integer id,@RequestBody Persona alumno)
    {
        Optional<Persona> oAlumno = alumnoDAO.buscarPorId(id);

        if (!oAlumno.isPresent())
            throw new NotFoundException(String.format("El alumno con ID %d no existe",id));

        Persona alumnoActualizado = ((AlumnoDAO)alumnoDAO).actualizar(oAlumno.get(),alumno);
        return new ResponseEntity<Persona>(alumnoActualizado,HttpStatus.OK);
    }

    /**
     * Endpoint borra un ubjeto persona de tipo alumno por su ID
     * @param id identificador del elemento a borrar
     * @return
     * @author DYMS 15/05/2022
     */
    @DeleteMapping("/alumnoId/{id}")
    public ResponseEntity<?> borrarAlumno(@PathVariable Integer id)
    {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        Optional<Persona> oAlumno = alumnoDAO.buscarPorId(id);

        if (!oAlumno.isPresent())
            throw new NotFoundException(String.format("La carrera con ID %d no encontrada", id));

        alumnoDAO.eliminarPorId(id);
        respuesta.put("OK", "alumno con ID " + id + " eliminad@");
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
    }

    /**
     * Endpoint asigna una carrera existente a un Objeto persona de tipo alumno
     * @param alumnoId identificador del alumno al que se asignara la carrera
     * @param carreraId  identificador de la carrera a asignar
     * @return
     * @author DYMS 15/05/2022
     */
    @PutMapping("/alumnoid/{alumnoId}/carreraid/{carreraId}")
    public ResponseEntity<?> asignarCarrera(@PathVariable Integer alumnoId,@PathVariable Integer carreraId)
    {
        Optional<Persona> oAlumno = alumnoDAO.buscarPorId(alumnoId);
        if (!oAlumno.isPresent())
            throw new NotFoundException(String.format("El alumno  con ID %d no existe",alumnoId));

        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
        if (!oCarrera.isPresent())
            throw new NotFoundException(String.format("La carrera con ID %d no existe",carreraId));

        Persona alumno = ((AlumnoDAO)alumnoDAO).asociarCarrera(oAlumno.get(),oCarrera.get());
        return new ResponseEntity<Persona>(alumno,HttpStatus.OK);
    }

}
