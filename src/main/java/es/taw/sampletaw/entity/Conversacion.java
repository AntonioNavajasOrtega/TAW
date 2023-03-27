package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Conversacion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "abierta", nullable = false)
    private byte abierta;
    @ManyToOne
    @JoinColumn(name = "empleado", referencedColumnName = "id", nullable = false)
    private Empleado empleadoByEmpleado;
    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id", nullable = false)
    private Cliente clienteByCliente;
    @OneToMany(mappedBy = "conversacionByConversacion")
    private Collection<Mensaje> mensajesById;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getAbierta() {
        return abierta;
    }

    public void setAbierta(Byte abierta) {
        this.abierta = abierta;
    }

    public void setAbierta(byte abierta) {
        this.abierta = abierta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conversacion that = (Conversacion) o;

        if (id != that.id) return false;
        if (abierta != that.abierta) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) abierta;
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
