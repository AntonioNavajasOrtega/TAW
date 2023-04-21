package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Solicitud {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;
    @ManyToOne
    @JoinColumn(name = "tipo", referencedColumnName = "id", nullable = false)
    private TipoSolicitud tipoSolicitudByTipo;
    @ManyToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id", nullable = false)
    private Empleado empleadoByEmpleadoId;
    @ManyToOne
    @JoinColumn(name = "cuenta_id", referencedColumnName = "id", nullable = false)
    private Cuenta cuentaByCuentaId;
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente clienteByClienteId;
    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresaByEmpresaId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solicitud solicitud = (Solicitud) o;
        return Objects.equals(id, solicitud.id) && Objects.equals(fecha, solicitud.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha);
    }

    public TipoSolicitud getTipoSolicitudByTipo() {
        return tipoSolicitudByTipo;
    }

    public void setTipoSolicitudByTipo(TipoSolicitud tipoSolicitudByTipo) {
        this.tipoSolicitudByTipo = tipoSolicitudByTipo;
    }

    public Empleado getEmpleadoByEmpleadoId() {
        return empleadoByEmpleadoId;
    }

    public void setEmpleadoByEmpleadoId(Empleado empleadoByEmpleadoId) {
        this.empleadoByEmpleadoId = empleadoByEmpleadoId;
    }

    public Cuenta getCuentaByCuentaId() {
        return cuentaByCuentaId;
    }

    public void setCuentaByCuentaId(Cuenta cuentaByCuentaId) {
        this.cuentaByCuentaId = cuentaByCuentaId;
    }

    public Cliente getClienteByClienteId() {
        return clienteByClienteId;
    }

    public void setClienteByClienteId(Cliente clienteByClienteId) {
        this.clienteByClienteId = clienteByClienteId;
    }

    public Empresa getEmpresaByEmpresaId() {
        return empresaByEmpresaId;
    }

    public void setEmpresaByEmpresaId(Empresa empresaByEmpresaId) {
        this.empresaByEmpresaId = empresaByEmpresaId;
    }
}
