package es.taw.sampletaw.dto;

import es.taw.sampletaw.entity.Cuenta;
import es.taw.sampletaw.entity.TipoTransaccion;

import java.math.BigDecimal;
import java.sql.Timestamp;
/**
 * @author Antonio Navajas Ortega
 */
public class TransaccionDTO {
    private Integer id;
    private BigDecimal cantidad;
    private Timestamp fecha;
    private String moneda;
    private TipoTransaccionDTO tipoTransaccionByTipo;
    private CuentaDTO cuentaByCuentaOrigenId;
    private CuentaDTO cuentaByCuentaDestinoId;

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

    public TipoTransaccionDTO getTipoTransaccionByTipo() {
        return tipoTransaccionByTipo;
    }

    public void setTipoTransaccionByTipo(TipoTransaccionDTO tipoTransaccionByTipo) {
        this.tipoTransaccionByTipo = tipoTransaccionByTipo;
    }

    public CuentaDTO getCuentaByCuentaOrigenId() {
        return cuentaByCuentaOrigenId;
    }

    public void setCuentaByCuentaOrigenId(CuentaDTO cuentaByCuentaOrigenId) {
        this.cuentaByCuentaOrigenId = cuentaByCuentaOrigenId;
    }

    public CuentaDTO getCuentaByCuentaDestinoId() {
        return cuentaByCuentaDestinoId;
    }

    public void setCuentaByCuentaDestinoId(CuentaDTO cuentaByCuentaDestinoId) {
        this.cuentaByCuentaDestinoId = cuentaByCuentaDestinoId;
    }
}
