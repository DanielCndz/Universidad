package com.ibm.academy.apirest.exceptions.handler;

import com.ibm.academy.apirest.exceptions.BadRequestException;
import com.ibm.academy.apirest.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UniversidadException
{
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> formatoInvalidoException(BadRequestException badRequestException)
    {
        Map<String,Object> respuesta =new HashMap<String,Object>();
        respuesta.put("error",badRequestException.getMessage());
        return  new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> formatoInvalidoException(NotFoundException notFoundException)
    {
        Map<String,Object> respuesta =new HashMap<String,Object>();
        respuesta.put("error",notFoundException.getMessage());
        return  new ResponseEntity<>(respuesta,HttpStatus.NOT_FOUND);
    }
}
