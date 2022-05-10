package com.ibm.academy.apirest.entities;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class Direccion implements Serializable
{
    private String calle;
    private String numero;
    private String codigoPostal;
    private String departamento;
    private String piso;
    private String localidad;
    private static final long serialVersionUID = 456978875055151728L;
}
