package com.ibm.academy.apirest.controllers;

import com.ibm.academy.apirest.entities.Pabellon;
import com.ibm.academy.apirest.exceptions.NotFoundException;
import com.ibm.academy.apirest.services.PabellonDAOImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pabellon")
public class PabellonControl
{
    @Autowired
    private PabellonDAOImp pabellonDAO;

    /**
     * Endpoint guarda un objeto pabellon el la DB
     * @param pabellon objeto a guardar en la DB
     * @return objeto guardado en la DB
     * @author DYMS 15/05/2022
     */
    @PostMapping()
    public ResponseEntity<?> guardarPabellon(@RequestBody Pabellon pabellon)
    {
        Pabellon pabellonGuardado = pabellonDAO.guardar(pabellon);
        return new ResponseEntity<Pabellon>(pabellonGuardado, HttpStatus.CREATED);
    }

    /**
     * Endpoint busca un objeto pabellon en la DB a partir de un ID proporcionado
     * @param id identificador del objeto a buscar
     * @return objeto encontrado
     * @author DYMS 15/05/2022
     */
    @GetMapping("/search/id/{id}")
    public  ResponseEntity<?> buscarPorId(@PathVariable Integer id)
    {
        Optional<Pabellon> oPabellon = pabellonDAO.buscarPorId(id);
        if (!oPabellon.isPresent())
            throw new NotFoundException(String.format("Pabellon con ID %d no existe",id));
        return new ResponseEntity<Pabellon>(oPabellon.get(),HttpStatus.OK);
    }

    /**
     * Enspoint muestra una lista con todos los objetos tipo pabellon en ls DB
     * @return una lista con todos los objetos pabellon en la DB
     * @author DYMS 15/05/2022
     */
    @GetMapping("listas/pabellones")
    public ResponseEntity<?> mostrarTodos()
    {
        List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.buscarTodos();
        if (pabellones.isEmpty())
            throw new NotFoundException("Lista vacia");
        return new ResponseEntity<List<Pabellon>>(pabellones,HttpStatus.OK);
    }

    /**
     * Endpoin busca y borra un objeto pabellon de la DB con el ID proporcionado
     * @param id identificador del objeto a borrar
     * @return objeto orrado
     * @author DYMS 15/05/2022
     */
    @DeleteMapping("/del/id/{id}")
    public ResponseEntity<?> borrarPabellonPorId(@PathVariable Integer id)
    {
        Map<String,Object> respuesta = new HashMap<String,Object>();
        Optional<Pabellon> oPabellon = pabellonDAO.buscarPorId(id);

        if (!oPabellon.isPresent())
            throw new NotFoundException(String.format("Pabellon con ID %d no existe",id));
        pabellonDAO.eliminarPorId(id);
        respuesta.put("OK","Aula con ID "+ id +" eliminada");
        return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);
    }

    /**
     * Endpoint actualiza un objeto pabellon que coincida con el ID proporcionado, utilizando la informacion de otro objeto pabellon
     * @param id identificador del objeto a modificar
     * @param pabellon objeto pabellon con la informacion que remplazara la del objeto original
     * @return el objeto pabellon con la informacion actualizada
     * @author DYMS 15/05/2022
     */
    @PutMapping("/upd/id/{id}")
    public ResponseEntity<?> actualizarPabellon(@PathVariable Integer id, @RequestBody Pabellon pabellon)
    {
        Optional<Pabellon> oPabellon = pabellonDAO.buscarPorId(id);
        if (!oPabellon.isPresent())
            throw new NotFoundException(String.format("Pabellon con ID %i no existe",id));
        Pabellon pabellonActualizado = pabellonDAO.actualizarPabellon(oPabellon.get(),pabellon);
        return new ResponseEntity<Pabellon>(pabellonActualizado,HttpStatus.OK);
    }

}
