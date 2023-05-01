package es.taw.sampletaw.entity;

import es.taw.sampletaw.dto.EstadoCuentaDTO;
import es.taw.sampletaw.dto.TipoClienteDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tipo_cliente", schema = "bancotaw", catalog = "")
public class TipoCliente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "tipo", nullable = true, length = 15)
    private String tipo;
    @OneToMany(mappedBy = "tipoClienteByTipo")
    private Collection<Tipoclienterelacionado> tipoclienterelacionadosById;

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

        TipoCliente that = (TipoCliente) o;

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

    public Collection<Tipoclienterelacionado> getTipoclienterelacionadosById() {
        return tipoclienterelacionadosById;
    }

    public void setTipoclienterelacionadosById(Collection<Tipoclienterelacionado> tipoclienterelacionadosById) {
        this.tipoclienterelacionadosById = tipoclienterelacionadosById;
    }

    public TipoClienteDTO toDTO(){
        TipoClienteDTO dto = new TipoClienteDTO();
        dto.setId(this.getId());
        dto.setTipo(this.getTipo());

        return dto;
    }
}
