package com.ibm.academy.apirest.entities;

import com.ibm.academy.apirest.enums.TipoEmpleado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "empleado",schema = "universidad")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Empleado extends Persona
{
    @Column(name = "sueldo")
    private BigDecimal sueldo;

    @Column(name = "tipo_empleado")
    @Enumerated(EnumType.STRING)
    private TipoEmpleado tipoEmpleado;

    @OneToOne(optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "pabellon_id")
    private Pabellon pabellon;

    public Empleado(Integer id, String nombre, String apellido, String dni, Direccion direccion,BigDecimal sueldo,TipoEmpleado tipoEmpleado) {
        super(id, nombre, apellido, dni, direccion);
        this.sueldo=sueldo;
        this.tipoEmpleado=tipoEmpleado;
    }

    @Override
    public String toString() {
        return super.toString()+"Empleado{" +
                "sueldo=" + sueldo +
                ", tipoEmpleado=" + tipoEmpleado +
                '}';
    }

    private static final long serialVersionUID = 6218451848345636848L;


}
