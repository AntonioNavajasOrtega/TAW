package es.taw.sampletaw.dto;

import ch.qos.logback.core.net.server.Client;
import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Cuenta;
import es.taw.sampletaw.entity.TipoCliente;

import java.io.Serializable;
/**
 * @author Antonio Navajas Ortega
 */
public class TipoclienterelacionadoDTO implements Serializable {

    private ClienteDTO cliente;

    private CuentaDTO cuenta;

    private TipoClienteDTO tipo;

    private Byte bloqueado;

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }

    public TipoClienteDTO getTipo() {
        return tipo;
    }

    public void setTipo(TipoClienteDTO tipo) {
        this.tipo = tipo;
    }

    public Byte getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Byte bloqueado) {
        this.bloqueado = bloqueado;
    }
}
