package com.ibm.academy.apirest.controllers;

import com.ibm.academy.apirest.entities.Aula;
import com.ibm.academy.apirest.entities.Pabellon;
import com.ibm.academy.apirest.entities.Persona;
import com.ibm.academy.apirest.exceptions.NotFoundException;
import com.ibm.academy.apirest.services.AulaDAOImp;
import com.ibm.academy.apirest.services.PabellonDAOImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/aula")
public class AulaControl
{
    @Autowired
    private AulaDAOImp aulaDAO;

    @Autowired
    private PabellonDAOImp pabellonDAO;

    /**
     * Endpoint guarda un objeto aula que se le proporciona
     * @param aula objeto a guardar de tipo aula
     * @return objeto guardado en la DB
     * @author DYMS 15/05/2022
     */
    @PostMapping()
    public ResponseEntity<?> crearAula(@RequestBody Aula aula)
    {
        Aula aulaGuardada = aulaDAO.guardar(aula);
        return new ResponseEntity<Aula>(aulaGuardada, HttpStatus.CREATED);
    }

    /**
     * Endpoint que busca un objeto aula con el ID proporcionado
     * @param id identificador del objeto aula a buscar
     * @return objeto encontrado con el ID proporcionado en la DB
     * @uathor DYMS 15/05/2022
     */
    @GetMapping("search/id/{id}")
    public  ResponseEntity<?> buscarAulaPorId(@PathVariable Integer id)
    {
        Optional<Aula> oAula = aulaDAO.buscarPorId(id);
        if (!oAula.isPresent())
            throw new NotFoundException(String.format("Aula con ID %i no existe",id));
        return new ResponseEntity<Aula>(oAula.get(),HttpStatus.OK);
    }

    /**
     * Endpoint que muestra todos los objetos aula en la DB
     * @return una lista de objetos aula existentes en la DB
     * @author DYMS 15/05/2022
     */
    @GetMapping("/listas/aulas")
    public ResponseEntity<?> mostrarAulas()
    {
        List<Aula> aulas = (List<Aula>) aulaDAO.buscarTodos();
        if (aulas.isEmpty())
            throw new NotFoundException("Lista vacia");
        return new ResponseEntity<List<Aula>>(aulas,HttpStatus.OK);
    }

    /**
     * Endpoint que borra un objeto aula de la DB con el ID proporcionado
     * @param id identificador del objeto aula a eliminar
     * @return objeto eliminado de la DB
     * @author DYMS 15/05/2022
     */
    @DeleteMapping("/del/id/{id}")
    public  ResponseEntity<?> borrarAulaPorId(@PathVariable Integer id)
    {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        Optional<Aula> oAula = aulaDAO.buscarPorId(id);

        if (!oAula.isPresent())
            throw new NotFoundException(String.format("Aula con ID %d no encontrado", id));

        aulaDAO.eliminarPorId(id);
        respuesta.put("OK", "aula con ID " + id + " eliminad@");
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
    }

    /**
     * Endpoint que actualiza la informacion de un objeto aula en la DB con el ID proporcionado, con la informacion de otro objeto tipo aula prporcionado
     * @param id identificador del objeto aula  a modificar
     * @param aula objeto tipo aula con la informacion que sustituira a la original
     * @return objeto actualizado
     * @author DYMS 15/05/2022
     */
    @PutMapping("/upd/id/{id}")
    public ResponseEntity<Aula> actualizarAula(@PathVariable Integer id, @RequestBody Aula aula)
    {
        Optional<Aula> oAula = aulaDAO.buscarPorId(id);
        if (!oAula.isPresent())
            throw new NotFoundException(String.format("Aula con Id %d no existe",id));
        Aula aulaNueva = aulaDAO.actualizarAula(oAula.get(),aula);
        return new ResponseEntity<Aula>(aulaNueva,HttpStatus.OK);
    }

    /**
     * Endpoint que asigna un objeto pabellon a un objeto aula con los ID proporcionados
     * @param idAula identificador del objeto aula en la DB
     * @param idPabellon identificador del objeto pabellon en la DB
     * @return objeto tipo aula con un objeto pabellon asignado
     * @author DYMS 15/05/2022
     */
    @PutMapping("/link/aulaid/{idAula}/pabellonid/{idPabellon}")
    public ResponseEntity<?> asignarPabellon(@PathVariable Integer idAula,@PathVariable Integer idPabellon)
    {
        Optional<Aula> oAula = aulaDAO.buscarPorId(idAula);
        if (!oAula.isPresent())
            throw new NotFoundException(String.format("Aula con Id %d no existe",idAula));
        Optional<Pabellon> oPabellon = pabellonDAO.buscarPorId(idPabellon);
        if (!oPabellon.isPresent())
            throw new NotFoundException(String.format("Pabellon con Id %d no existe",idPabellon));
        Aula aulaNueva = aulaDAO.asignarPabellon(oAula.get(),oPabellon.get());
        return new ResponseEntity<Aula>(aulaNueva,HttpStatus.OK);
    }
}
