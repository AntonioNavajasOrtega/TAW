package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Empleado {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombreUsuario;
    @Basic
    @Column(name = "contrasena", nullable = false, length = 100)
    private String contrasena;
    @OneToMany(mappedBy = "empleadoByEmpleado")
    private Collection<Conversacion> conversacionsById;
    @OneToMany(mappedBy = "empleadoByEmpleadoId")
    private Collection<Cuenta> cuentasById;
    @ManyToOne
    @JoinColumn(name = "tipo", referencedColumnName = "id", nullable = false)
    private TipoEmpleado tipoEmpleadoByTipo;
    @OneToMany(mappedBy = "empleadoByEmisorEmpleado")
    private Collection<Mensaje> mensajesById;
    @OneToMany(mappedBy = "empleadoByReceptorEmpleado")
    private Collection<Mensaje> mensajesById_0;
    @OneToMany(mappedBy = "empleadoByEmpleadoId")
    private Collection<Solicitud> solicitudsById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empleado empleado = (Empleado) o;

        if (id != null ? !id.equals(empleado.id) : empleado.id != null) return false;
        if (nombreUsuario != null ? !nombreUsuario.equals(empleado.nombreUsuario) : empleado.nombreUsuario != null)
            return false;
        if (contrasena != null ? !contrasena.equals(empleado.contrasena) : empleado.contrasena != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombreUsuario != null ? nombreUsuario.hashCode() : 0);
        result = 31 * result + (contrasena != null ? contrasena.hashCode() : 0);
        return result;
    }

    public Collection<Conversacion> getConversacionsById() {
        return conversacionsById;
    }

    public void setConversacionsById(Collection<Conversacion> conversacionsById) {
        this.conversacionsById = conversacionsById;
    }

    public Collection<Cuenta> getCuentasById() {
        return cuentasById;
    }

    public void setCuentasById(Collection<Cuenta> cuentasById) {
        this.cuentasById = cuentasById;
    }

    public TipoEmpleado getTipoEmpleadoByTipo() {
        return tipoEmpleadoByTipo;
    }

    public void setTipoEmpleadoByTipo(TipoEmpleado tipoEmpleadoByTipo) {
        this.tipoEmpleadoByTipo = tipoEmpleadoByTipo;
    }

    public Collection<Mensaje> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(Collection<Mensaje> mensajesById) {
        this.mensajesById = mensajesById;
    }

    public Collection<Mensaje> getMensajesById_0() {
        return mensajesById_0;
    }

    public void setMensajesById_0(Collection<Mensaje> mensajesById_0) {
        this.mensajesById_0 = mensajesById_0;
    }

    public Collection<Solicitud> getSolicitudsById() {
        return solicitudsById;
    }

    public void setSolicitudsById(Collection<Solicitud> solicitudsById) {
        this.solicitudsById = solicitudsById;
    }
}
