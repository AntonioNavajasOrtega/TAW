package es.taw.sampletaw.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TipoclienterelacionadoEntityPK implements Serializable {
    @Column(name = "cliente_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clienteId;
    @Column(name = "cuenta_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        TipoclienterelacionadoEntityPK that = (TipoclienterelacionadoEntityPK) o;
        return clienteId == that.clienteId && cuentaId == that.cuentaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, cuentaId);
    }
}
