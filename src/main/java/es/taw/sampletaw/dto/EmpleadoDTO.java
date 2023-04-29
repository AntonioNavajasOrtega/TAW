package es.taw.sampletaw.dto;

import java.io.Serializable;


public class EmpleadoDTO implements Serializable {


    private Integer id;

    private String nombreUsuario;

    private String contrasena;

    private TipoEmpleadoDTO tipoEmpleado;

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
}
