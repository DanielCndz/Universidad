package com.ibm.academy.apirest.controllers;



import com.ibm.academy.apirest.entities.Pabellon;
import com.ibm.academy.apirest.entities.Persona;
import com.ibm.academy.apirest.exceptions.NotFoundException;
import com.ibm.academy.apirest.services.EmpleadoDAO;
import com.ibm.academy.apirest.services.PabellonDAOImp;
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
@RequestMapping("/empleado")
public class EmpleadoControl
{
    @Autowired
    @Qualifier("empleadoDAOImp")
    private PersonaDAO empleadoDAO;

    @Autowired
    private PabellonDAOImp pabellonDAO;

    /**
     * Endpoint guarda un objeto persona de tipo empleado en la DB
     * @param empleado contiene el objeto persona tipo empleado que se guardara
     * @return el empleadp guardado
     * @author DYMS 15/05/2022
     */
    @PostMapping()
    public ResponseEntity<?> crearEmpleado(@RequestBody Persona empleado)
    {
        Persona nuevoEmpleado = empleadoDAO.guardar(empleado);
        return new ResponseEntity<Persona>(nuevoEmpleado, HttpStatus.CREATED);
    }

    /**
     * Endpoint muestra todos los empleados en una lista
     * @return lista de objetos tipo persona
     * @author DYMS 15/05/2022
     */
    @GetMapping("/listas/empleados")
    public ResponseEntity<?> mostrarEmpleados()
    {
        List<Persona> empleados = (List<Persona>) empleadoDAO.buscarTodos();
        if (empleados.isEmpty())
            throw new NotFoundException("Lista vacia");
        return new ResponseEntity<List<Persona>>(empleados,HttpStatus.OK);
    }

    /**
     * Endpoint muestra el empleado con el id proporcionado
     * @param id identificador del empleado a buscar
     * @return
     * @author DYMS 15/05/2022
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<?> mostrarEmpleadoPorId(@PathVariable Integer id)
    {
        Optional<Persona> oEmpleado = empleadoDAO.buscarPorId(id);
        if (!oEmpleado.isPresent())
            throw new NotFoundException(String.format("El empleado con ID %d no existe",id));
        return new ResponseEntity<Persona>(oEmpleado.get(),HttpStatus.OK);
    }


    /**
     * Endpoint borra el objeto persona tipo empleado de la DB
     * @param id identificador del empleado a borrar
     * @return empleado borrado
     * @author DYMS 15/05/2022
     */
    @DeleteMapping("/del/id/{id}")
    public ResponseEntity<?> borrarEmpleadoPorId(@PathVariable Integer id)
    {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        Optional<Persona> oEmpleado = empleadoDAO.buscarPorId(id);

        if (!oEmpleado.isPresent())
            throw new NotFoundException(String.format("El empleado con ID %d no encontrada", id));

        empleadoDAO.eliminarPorId(id);
        respuesta.put("OK", "Empleado con ID " + id + " eliminad@");
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
    }

    /**
     * Endpoint asigna un pabellon a un empleado por sus ID
     * @param idEmpleado identificador del objeto persona y tipo empleado al que se asignara un pabellon
     * @param idPabellon identificador del objeto pabellon
     * @return empleado modificado con pabellon asignado
     * @Author DYMS 15/05/2022
     */
    @PutMapping("/upd/empleadoid/{idEmpleado}/pabellonid/{idPabellon}")
    public ResponseEntity<?> asignarPabellon(@PathVariable Integer idEmpleado,@PathVariable Integer idPabellon)
    {
        Optional<Persona> oEmpleado = empleadoDAO.buscarPorId(idEmpleado);
        if (!oEmpleado.isPresent())
            throw new NotFoundException(String.format("Empleado con ID %d no existe",idEmpleado));
        Optional<Pabellon> oPabellon = pabellonDAO.buscarPorId(idPabellon);
        if (!oPabellon.isPresent())
            throw new NotFoundException(String.format("Pabellon con ID %d no existe",idPabellon));
        Persona empleado = ((EmpleadoDAO)empleadoDAO).asignarPabellon(oEmpleado.get(),oPabellon.get());
        return new ResponseEntity<Persona>(empleado,HttpStatus.OK);
    }

    /**
     * Endpoint modifica el empleado del ID proporcionado con informacion de un objeto de tipo empleado
     * @param id identificador del empleado a modificar
     * @param empleado informacion que remplazara al empleado del ID proporcionado
     * @return empleado actualizado
     * @author DYMS 15/05/2022
     */
    @PutMapping("/upd/id/{id}")
    public ResponseEntity<Persona> actualizarEmpleado(@PathVariable Integer id,@RequestBody Persona empleado)
    {
        Optional<Persona> oEmpleado = empleadoDAO.buscarPorId(id);
        if (!oEmpleado.isPresent())
            throw new NotFoundException(String.format("Empleado con ID %d no existe",id));

        Persona empleadoActualizado = ((EmpleadoDAO)empleadoDAO).actualizarEmpleado(oEmpleado.get(),empleado);

        return new ResponseEntity<Persona>(empleadoActualizado,HttpStatus.OK);
    }



}
