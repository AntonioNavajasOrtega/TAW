package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Transaccion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "cantidad", nullable = false, precision = 2)
    private BigDecimal cantidad;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;
    @Basic
    @Column(name = "moneda", nullable = true, length = 10)
    private String moneda;
    @ManyToOne
    @JoinColumn(name = "tipo", referencedColumnName = "id", nullable = false)
    private TipoTransaccion tipoTransaccionByTipo;
    @ManyToOne
    @JoinColumn(name = "cuenta_origen_id", referencedColumnName = "id")
    private Cuenta cuentaByCuentaOrigenId;
    @ManyToOne
    @JoinColumn(name = "cuenta_destino_id", referencedColumnName = "id")
    private Cuenta cuentaByCuentaDestinoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaccion that = (Transaccion) o;
        return Objects.equals(id, that.id) && Objects.equals(cantidad, that.cantidad) && Objects.equals(fecha, that.fecha) && Objects.equals(moneda, that.moneda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cantidad, fecha, moneda);
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
