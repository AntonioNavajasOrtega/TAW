package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tipo_cliente", schema = "bancotaw", catalog = "")
public class TipoClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "tipo", nullable = true, length = 15)
    private String tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        TipoClienteEntity that = (TipoClienteEntity) o;
        return id == that.id && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo);
    }
}
