package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Mensaje {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;
    @Basic
    @Column(name = "contenido", nullable = false, length = 400)
    private String contenido;
    @ManyToOne
    @JoinColumn(name = "conversacion", referencedColumnName = "id", nullable = false)
    private Conversacion conversacionByConversacion;
    @ManyToOne
    @JoinColumn(name = "emisor_cliente", referencedColumnName = "id")
    private Cliente clienteByEmisorCliente;
    @ManyToOne
    @JoinColumn(name = "emisor_empleado", referencedColumnName = "id")
    private Empleado empleadoByEmisorEmpleado;
    @ManyToOne
    @JoinColumn(name = "receptor_cliente", referencedColumnName = "id")
    private Cliente clienteByReceptorCliente;
    @ManyToOne
    @JoinColumn(name = "receptor_empleado", referencedColumnName = "id")
    private Empleado empleadoByReceptorEmpleado;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensaje mensaje = (Mensaje) o;
        return Objects.equals(id, mensaje.id) && Objects.equals(fecha, mensaje.fecha) && Objects.equals(contenido, mensaje.contenido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, contenido);
    }

    public Conversacion getConversacionByConversacion() {
        return conversacionByConversacion;
    }

    public void setConversacionByConversacion(Conversacion conversacionByConversacion) {
        this.conversacionByConversacion = conversacionByConversacion;
    }

    public Cliente getClienteByEmisorCliente() {
        return clienteByEmisorCliente;
    }

    public void setClienteByEmisorCliente(Cliente clienteByEmisorCliente) {
        this.clienteByEmisorCliente = clienteByEmisorCliente;
    }

    public Empleado getEmpleadoByEmisorEmpleado() {
        return empleadoByEmisorEmpleado;
    }

    public void setEmpleadoByEmisorEmpleado(Empleado empleadoByEmisorEmpleado) {
        this.empleadoByEmisorEmpleado = empleadoByEmisorEmpleado;
    }

    public Cliente getClienteByReceptorCliente() {
        return clienteByReceptorCliente;
    }

    public void setClienteByReceptorCliente(Cliente clienteByReceptorCliente) {
        this.clienteByReceptorCliente = clienteByReceptorCliente;
    }

    public Empleado getEmpleadoByReceptorEmpleado() {
        return empleadoByReceptorEmpleado;
    }

    public void setEmpleadoByReceptorEmpleado(Empleado empleadoByReceptorEmpleado) {
        this.empleadoByReceptorEmpleado = empleadoByReceptorEmpleado;
    }
}
