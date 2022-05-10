package com.ibm.academy.apirest.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "profesor",schema = "universidad")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Profesor extends Persona
{
    @Column(name = "sueldo")
    private BigDecimal sueldo;

    public Profesor(Integer id, String nombre, String apellido, String dni, Direccion direccion, BigDecimal sueldo) {
        super(id, nombre, apellido, dni, direccion);
        this.sueldo=sueldo;
    }

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "profesor_carrera",schema = "universidad",
            joinColumns = @JoinColumn(name = "profesor_id"),
            inverseJoinColumns = @JoinColumn(name = "carrera_id"))
    private Set<Carrera> carreras;

    @Override
    public String toString() {
        return super.toString()+"Profesor{" +
                "sueldo=" + sueldo +
                '}';
    }

    private static final long serialVersionUID = -6998701090831536454L;

}
