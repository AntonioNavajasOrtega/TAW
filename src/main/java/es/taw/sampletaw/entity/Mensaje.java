package es.taw.sampletaw.entity;

import es.taw.sampletaw.dto.DTO;
import es.taw.sampletaw.dto.MensajeDTO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Mensaje implements DTO<MensajeDTO> {
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

        if (id != null ? !id.equals(mensaje.id) : mensaje.id != null) return false;
        if (fecha != null ? !fecha.equals(mensaje.fecha) : mensaje.fecha != null) return false;
        if (contenido != null ? !contenido.equals(mensaje.contenido) : mensaje.contenido != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (contenido != null ? contenido.hashCode() : 0);
        return result;
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

    public MensajeDTO toDTO(){
        MensajeDTO dto = new MensajeDTO();
        dto.setId(this.id);
        dto.setFecha(this.fecha);
        dto.setContenido(this.contenido);
        dto.setConversacionByConversacion(this.conversacionByConversacion.toDTO());
        dto.setClienteByEmisorCliente(this.clienteByEmisorCliente.toDTO());
        dto.setEmpleadoByEmisorEmpleado(this.empleadoByEmisorEmpleado.toDTO());
        dto.setClienteByReceptorCliente(this.clienteByReceptorCliente.toDTO());
        dto.setEmpleadoByReceptorEmpleado(this.empleadoByReceptorEmpleado.toDTO());

        return dto;
    }

}
