package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class TipoclienterelacionadoPK implements Serializable {

    private int clienteId;
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

        TipoclienterelacionadoPK that = (TipoclienterelacionadoPK) o;

        if (clienteId != that.clienteId) return false;
        if (cuentaId != that.cuentaId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clienteId;
        result = 31 * result + cuentaId;
        return result;
    }
}
