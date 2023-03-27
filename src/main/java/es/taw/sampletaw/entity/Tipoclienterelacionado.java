package es.taw.sampletaw.entity;

import javax.persistence.*;

@Entity
@IdClass(TipoclienterelacionadoPK.class)
public class Tipoclienterelacionado {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cliente_id", nullable = false)
    private int clienteId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cuenta_id", nullable = false)
    private int cuentaId;
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente clienteByClienteId;
    @ManyToOne
    @JoinColumn(name = "cuenta_id", referencedColumnName = "id", nullable = false)
    private Cuenta cuentaByCuentaId;
    @ManyToOne
    @JoinColumn(name = "tipo", referencedColumnName = "id", nullable = false)
    private TipoCliente tipoClienteByTipo;

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Integer cuentaId) {
        this.cuentaId = cuentaId;
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

    public Cliente getClienteByClienteId() {
        return clienteByClienteId;
    }

    public void setClienteByClienteId(Cliente clienteByClienteId) {
        this.clienteByClienteId = clienteByClienteId;
    }

    public Cuenta getCuentaByCuentaId() {
        return cuentaByCuentaId;
    }

    public void setCuentaByCuentaId(Cuenta cuentaByCuentaId) {
        this.cuentaByCuentaId = cuentaByCuentaId;
    }

    public TipoCliente getTipoClienteByTipo() {
        return tipoClienteByTipo;
    }

    public void setTipoClienteByTipo(TipoCliente tipoClienteByTipo) {
        this.tipoClienteByTipo = tipoClienteByTipo;
    }
}
