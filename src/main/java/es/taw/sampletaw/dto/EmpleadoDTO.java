package es.taw.sampletaw.dto;

import es.taw.sampletaw.entity.Conversacion;
import es.taw.sampletaw.entity.Cuenta;
import es.taw.sampletaw.entity.Mensaje;
import es.taw.sampletaw.entity.Solicitud;

import java.io.Serializable;
import java.util.Collection;


public class EmpleadoDTO implements Serializable {


    private Integer id;

    private String nombreUsuario;

    private String contrasena;

    private TipoEmpleadoDTO tipoEmpleado;

    private Collection<ConversacionDTO> conversacionsById;

    private Collection<CuentaDTO> cuentasById;

    private Collection<MensajeDTO> mensajesById;

    private Collection<MensajeDTO> mensajesById_0;

    private Collection<SolicitudDTO> solicitudsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public TipoEmpleadoDTO getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleadoDTO tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public Collection<ConversacionDTO> getConversacionsById() {
        return conversacionsById;
    }

    public void setConversacionsById(Collection<ConversacionDTO> conversacionsById) {
        this.conversacionsById = conversacionsById;
    }

    public Collection<CuentaDTO> getCuentasById() {
        return cuentasById;
    }

    public void setCuentasById(Collection<CuentaDTO> cuentasById) {
        this.cuentasById = cuentasById;
    }

    public Collection<MensajeDTO> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(Collection<MensajeDTO> mensajesById) {
        this.mensajesById = mensajesById;
    }

    public Collection<MensajeDTO> getMensajesById_0() {
        return mensajesById_0;
    }

    public void setMensajesById_0(Collection<MensajeDTO> mensajesById_0) {
        this.mensajesById_0 = mensajesById_0;
    }

    public Collection<SolicitudDTO> getSolicitudsById() {
        return solicitudsById;
    }

    public void setSolicitudsById(Collection<SolicitudDTO> solicitudsById) {
        this.solicitudsById = solicitudsById;
    }
}
