package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tipoclienterelacionado", schema = "bancotaw", catalog = "")
@IdClass(TipoclienterelacionadoEntityPK.class)
public class TipoclienterelacionadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cliente_id", nullable = false)
    private int clienteId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cuenta_id", nullable = false)
    private int cuentaId;

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(int cuentaId) {
        this.cuentaId = cuentaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoclienterelacionadoEntity that = (TipoclienterelacionadoEntity) o;
        return clienteId == that.clienteId && cuentaId == that.cuentaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, cuentaId);
    }
}
