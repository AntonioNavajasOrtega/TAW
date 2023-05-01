package es.taw.sampletaw.entity;

import es.taw.sampletaw.dto.MensajeDTO;
import es.taw.sampletaw.dto.SolicitudDTO;

import javax.persistence.*;
import java.sql.Timestamp;

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

        if (id != null ? !id.equals(solicitud.id) : solicitud.id != null) return false;
        if (fecha != null ? !fecha.equals(solicitud.fecha) : solicitud.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
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

    public SolicitudDTO toDTO(){
        SolicitudDTO dto = new SolicitudDTO();
        dto.setId(this.getId());
        dto.setFecha(this.getFecha());
        dto.setClienteByClienteId(this.getClienteByClienteId().toDTO());
        dto.setCuentaByCuentaId(this.getCuentaByCuentaId().toDTO());
        dto.setTipoSolicitudByTipo(this.getTipoSolicitudByTipo().toDTO());
        dto.setEmpresaByEmpresaId(this.getEmpresaByEmpresaId().toDTO());
        dto.setEmpleadoByEmpleadoId(this.getEmpleadoByEmpleadoId().toDTO());

        return dto;
    }
}
