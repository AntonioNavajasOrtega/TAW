package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class Transaccion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "cantidad", nullable = false, precision = 2)
    private BigDecimal cantidad;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;
    @ManyToOne
    @JoinColumn(name = "tipo", referencedColumnName = "id", nullable = false)
    private TipoTransaccion tipoTransaccionByTipo;
    @ManyToOne
    @JoinColumn(name = "cuenta_origen_id", referencedColumnName = "id")
    private Cuenta cuentaByCuentaOrigenId;
    @ManyToOne
    @JoinColumn(name = "cuenta_destino_id", referencedColumnName = "id")
    private Cuenta cuentaByCuentaDestinoId;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaccion that = (Transaccion) o;

        if (id != that.id) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    public TipoTransaccion getTipoTransaccionByTipo() {
        return tipoTransaccionByTipo;
    }

    public void setTipoTransaccionByTipo(TipoTransaccion tipoTransaccionByTipo) {
        this.tipoTransaccionByTipo = tipoTransaccionByTipo;
    }

    public Cuenta getCuentaByCuentaOrigenId() {
        return cuentaByCuentaOrigenId;
    }

    public void setCuentaByCuentaOrigenId(Cuenta cuentaByCuentaOrigenId) {
        this.cuentaByCuentaOrigenId = cuentaByCuentaOrigenId;
    }

    public Cuenta getCuentaByCuentaDestinoId() {
        return cuentaByCuentaDestinoId;
    }

    public void setCuentaByCuentaDestinoId(Cuenta cuentaByCuentaDestinoId) {
        this.cuentaByCuentaDestinoId = cuentaByCuentaDestinoId;
    }
}
