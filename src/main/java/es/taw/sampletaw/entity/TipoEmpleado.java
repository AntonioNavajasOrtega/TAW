package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "tipo_empleado", schema = "bancotaw", catalog = "")
public class TipoEmpleado {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "tipo", nullable = true, length = 15)
    private String tipo;
    @OneToMany(mappedBy = "tipoEmpleadoByTipo")
    private Collection<Empleado> empleadosById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoEmpleado that = (TipoEmpleado) o;
        return Objects.equals(id, that.id) && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo);
    }

    public Collection<Empleado> getEmpleadosById() {
        return empleadosById;
    }

    public void setEmpleadosById(Collection<Empleado> empleadosById) {
        this.empleadosById = empleadosById;
    }
}
