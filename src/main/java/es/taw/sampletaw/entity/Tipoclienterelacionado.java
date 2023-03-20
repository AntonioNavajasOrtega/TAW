package es.taw.sampletaw.entity;

import javax.persistence.*;

@Entity
@IdClass(TipoclienterelacionadoPK.class)
public class Tipoclienterelacionado {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cliente_id")
    private int clienteId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cuenta_id")
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

        Tipoclienterelacionado that = (Tipoclienterelacionado) o;

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
