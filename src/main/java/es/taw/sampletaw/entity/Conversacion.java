package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Conversacion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "abierta", nullable = false)
    private Byte abierta;
    @Basic
    @Column(name = "asunto", nullable = true, length = 50)
    private String asunto;
    @Basic
    @Column(name = "fecha_apertura", nullable = true)
    private Timestamp fechaApertura;
    @Basic
    @Column(name = "fecha_cierre", nullable = true)
    private Timestamp fechaCierre;
    @ManyToOne
    @JoinColumn(name = "empleado", referencedColumnName = "id", nullable = false)
    private Empleado empleadoByEmpleado;
    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id", nullable = false)
    private Cliente clienteByCliente;
    @OneToMany(mappedBy = "conversacionByConversacion")
    private Collection<Mensaje> mensajesById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conversacion that = (Conversacion) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (abierta != null ? !abierta.equals(that.abierta) : that.abierta != null) return false;
        if (asunto != null ? !asunto.equals(that.asunto) : that.asunto != null) return false;
        if (fechaApertura != null ? !fechaApertura.equals(that.fechaApertura) : that.fechaApertura != null)
            return false;
        if (fechaCierre != null ? !fechaCierre.equals(that.fechaCierre) : that.fechaCierre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (abierta != null ? abierta.hashCode() : 0);
        result = 31 * result + (asunto != null ? asunto.hashCode() : 0);
        result = 31 * result + (fechaApertura != null ? fechaApertura.hashCode() : 0);
        result = 31 * result + (fechaCierre != null ? fechaCierre.hashCode() : 0);
        return result;
    }

    public Empleado getEmpleadoByEmpleado() {
        return empleadoByEmpleado;
    }

    public void setEmpleadoByEmpleado(Empleado empleadoByEmpleado) {
        this.empleadoByEmpleado = empleadoByEmpleado;
    }

    public Cliente getClienteByCliente() {
        return clienteByCliente;
    }

    public void setClienteByCliente(Cliente clienteByCliente) {
        this.clienteByCliente = clienteByCliente;
    }

    public Collection<Mensaje> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(Collection<Mensaje> mensajesById) {
        this.mensajesById = mensajesById;
    }
}
