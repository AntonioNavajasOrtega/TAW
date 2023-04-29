package es.taw.sampletaw.entity;

import es.taw.sampletaw.dto.DTO;
import es.taw.sampletaw.dto.TipoEmpleadoDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tipo_empleado", schema = "bancotaw", catalog = "")
public class TipoEmpleado implements DTO<TipoEmpleadoDTO> {
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        return result;
    }

    public Collection<Empleado> getEmpleadosById() {
        return empleadosById;
    }

    public void setEmpleadosById(Collection<Empleado> empleadosById) {
        this.empleadosById = empleadosById;
    }

    public TipoEmpleadoDTO toDTO(){
        TipoEmpleadoDTO dto = new TipoEmpleadoDTO();
        dto.setId(this.id);
        dto.setTipo(this.tipo);
        return dto;
    }

}
