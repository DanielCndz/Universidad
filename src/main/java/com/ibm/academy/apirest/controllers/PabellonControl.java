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

    @PostMapping()
    public ResponseEntity<?> guardarPabellon(@RequestBody Pabellon pabellon)
    {
        Pabellon pabellonGuardado = pabellonDAO.guardar(pabellon);
        return new ResponseEntity<Pabellon>(pabellonGuardado, HttpStatus.CREATED);
    }

    @GetMapping("/search/id/{id}")
    public  ResponseEntity<?> buscarPorId(@PathVariable Integer id)
    {
        Optional<Pabellon> oPabellon = pabellonDAO.buscarPorId(id);
        if (!oPabellon.isPresent())
            throw new NotFoundException(String.format("Pabellon con ID %d no existe",id));
        return new ResponseEntity<Pabellon>(oPabellon.get(),HttpStatus.OK);
    }

    @GetMapping("listas/pabellones")
    public ResponseEntity<?> mostrarTodos()
    {
        List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.buscarTodos();
        if (pabellones.isEmpty())
            throw new NotFoundException("Lista vacia");
        return new ResponseEntity<List<Pabellon>>(pabellones,HttpStatus.OK);
    }

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
