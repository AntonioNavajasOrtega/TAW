package es.taw.sampletaw.entity;

import es.taw.sampletaw.dto.TipoClienteDTO;
import es.taw.sampletaw.dto.TipoTransaccionDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tipo_transaccion", schema = "bancotaw", catalog = "")
public class TipoTransaccion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "tipo", nullable = true, length = 15)
    private String tipo;
    @OneToMany(mappedBy = "tipoTransaccionByTipo")
    private Collection<Transaccion> transaccionsById;

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

        TipoTransaccion that = (TipoTransaccion) o;

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

    public Collection<Transaccion> getTransaccionsById() {
        return transaccionsById;
    }

    public void setTransaccionsById(Collection<Transaccion> transaccionsById) {
        this.transaccionsById = transaccionsById;
    }

    public TipoTransaccionDTO toDTO(){
        TipoTransaccionDTO dto = new TipoTransaccionDTO();
        dto.setId(this.getId());
        dto.setTipo(this.getTipo());


        return dto;
    }
}
