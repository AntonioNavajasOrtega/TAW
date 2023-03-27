package es.taw.sampletaw.entity;

import javax.persistence.*;

@Entity
public class Tipoclienterelacionado {

    @EmbeddedId
    private TipoclienterelacionadoPK tipoclienterelacionadoPK;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Cliente clienteByClienteId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "cuenta_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Cuenta cuentaByCuentaId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo", referencedColumnName = "id", nullable = false)
    private TipoCliente tipoClienteByTipo;

    public TipoclienterelacionadoPK getTipoclienterelacionadoPK() {
        return tipoclienterelacionadoPK;
    }

    public void setTipoclienterelacionadoPK(TipoclienterelacionadoPK tipoclienterelacionadoPK) {
        this.tipoclienterelacionadoPK = tipoclienterelacionadoPK;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tipoclienterelacionado that = (Tipoclienterelacionado) o;

        if (this.tipoclienterelacionadoPK != that.tipoclienterelacionadoPK) return false;
        if (tipoclienterelacionadoPK != that.tipoclienterelacionadoPK) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tipoclienterelacionadoPK.getCuentaId();
        result = 31 * result + tipoclienterelacionadoPK.getCuentaId();;
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
