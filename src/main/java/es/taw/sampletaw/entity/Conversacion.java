package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

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
        return Objects.equals(id, that.id) && Objects.equals(abierta, that.abierta) && Objects.equals(asunto, that.asunto) && Objects.equals(fechaApertura, that.fechaApertura) && Objects.equals(fechaCierre, that.fechaCierre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, abierta, asunto, fechaApertura, fechaCierre);
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
