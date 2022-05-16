package com.ibm.academy.apirest.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "pabellones",schema = "universidad")
public class Pabellon implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Positive(message = "el tamanio debe ser positivo")
    @Column(name = "tamano_metros")
    private Double tamanoMetros;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "codigoPostal",column = @Column(name = "codigo_postal")),
            @AttributeOverride(name = "departamento",column = @Column(name = "depatamento"))
    })
    private Direccion direccion;

    @ToString.Exclude
    @OneToMany(mappedBy  = "pabellon", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "pabellon"})
    private Set<Aula> aulas;

    public Pabellon(Integer id, Double tamanoMetros, String nombre, Direccion direccion) {
        this.id = id;
        this.tamanoMetros = tamanoMetros;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pabellon pabellon = (Pabellon) o;
        return Objects.equals(id, pabellon.id) && Objects.equals(nombre, pabellon.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @PrePersist
    private void antesPersistir()
    {
        this.fechaCreacion=new Date();
    }

    @PreUpdate
    private void antesActualizar()
    {
        this.fechaModificacion=new Date();
    }

    private static final long serialVersionUID = -4164163425597336518L;
}
