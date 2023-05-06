package es.taw.sampletaw.dto;


import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Juan José Torres
 */


public class MensajeDTO implements Serializable {

    private Integer id;

    private Timestamp fecha;

    private String contenido;

    private ConversacionDTO conversacionByConversacion;

    private ClienteDTO clienteByEmisorCliente;

    private EmpleadoDTO empleadoByEmisorEmpleado;

    private ClienteDTO clienteByReceptorCliente;

    private EmpleadoDTO empleadoByReceptorEmpleado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public ConversacionDTO getConversacionByConversacion() {
        return conversacionByConversacion;
    }

    public void setConversacionByConversacion(ConversacionDTO conversacionByConversacion) {
        this.conversacionByConversacion = conversacionByConversacion;
    }

    public ClienteDTO getClienteByEmisorCliente() {
        return clienteByEmisorCliente;
    }

    public void setClienteByEmisorCliente(ClienteDTO clienteByEmisorCliente) {
        this.clienteByEmisorCliente = clienteByEmisorCliente;
    }

    public EmpleadoDTO getEmpleadoByEmisorEmpleado() {
        return empleadoByEmisorEmpleado;
    }

    public void setEmpleadoByEmisorEmpleado(EmpleadoDTO empleadoByEmisorEmpleado) {
        this.empleadoByEmisorEmpleado = empleadoByEmisorEmpleado;
    }

    public ClienteDTO getClienteByReceptorCliente() {
        return clienteByReceptorCliente;
    }

    public void setClienteByReceptorCliente(ClienteDTO clienteByReceptorCliente) {
        this.clienteByReceptorCliente = clienteByReceptorCliente;
    }

    public EmpleadoDTO getEmpleadoByReceptorEmpleado() {
        return empleadoByReceptorEmpleado;
    }

    public void setEmpleadoByReceptorEmpleado(EmpleadoDTO empleadoByReceptorEmpleado) {
        this.empleadoByReceptorEmpleado = empleadoByReceptorEmpleado;
    }
}
