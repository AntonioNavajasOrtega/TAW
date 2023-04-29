package es.taw.sampletaw.dto;


import java.io.Serializable;
import java.sql.Timestamp;


public class ConversacionDTO implements Serializable{

    private Integer id;

    private Byte abierta;

    private String asunto;

    private Timestamp fechaApertura;
 
    private Timestamp fechaCierre;
 
    private EmpleadoDTO empleado;
  
    private ClienteDTO cliente;

    private Integer numeroMensajes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getAbierta() {
        return abierta;
    }

    public void setAbierta(Byte abierta) {
        this.abierta = abierta;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Timestamp getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Timestamp fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Timestamp getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Timestamp fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public Integer getNumeroMensajes() {
        return numeroMensajes;
    }

    public void setNumeroMensajes(Integer numeroMensajes) {
        this.numeroMensajes = numeroMensajes;
    }
}
