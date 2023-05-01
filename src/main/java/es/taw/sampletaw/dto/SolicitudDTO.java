package es.taw.sampletaw.dto;

import es.taw.sampletaw.entity.*;

import java.sql.Timestamp;

public class SolicitudDTO {
    private Integer id;
    private Timestamp fecha;
    private TipoSolicitudDTO tipoSolicitudByTipo;
    private EmpleadoDTO empleadoByEmpleadoId;
    private CuentaDTO cuentaByCuentaId;
    private ClienteDTO clienteByClienteId;
    private EmpresaDTO empresaByEmpresaId;

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

    public TipoSolicitudDTO getTipoSolicitudByTipo() {
        return tipoSolicitudByTipo;
    }

    public void setTipoSolicitudByTipo(TipoSolicitudDTO tipoSolicitudByTipo) {
        this.tipoSolicitudByTipo = tipoSolicitudByTipo;
    }

    public EmpleadoDTO getEmpleadoByEmpleadoId() {
        return empleadoByEmpleadoId;
    }

    public void setEmpleadoByEmpleadoId(EmpleadoDTO empleadoByEmpleadoId) {
        this.empleadoByEmpleadoId = empleadoByEmpleadoId;
    }

    public CuentaDTO getCuentaByCuentaId() {
        return cuentaByCuentaId;
    }

    public void setCuentaByCuentaId(CuentaDTO cuentaByCuentaId) {
        this.cuentaByCuentaId = cuentaByCuentaId;
    }

    public ClienteDTO getClienteByClienteId() {
        return clienteByClienteId;
    }

    public void setClienteByClienteId(ClienteDTO clienteByClienteId) {
        this.clienteByClienteId = clienteByClienteId;
    }

    public EmpresaDTO getEmpresaByEmpresaId() {
        return empresaByEmpresaId;
    }

    public void setEmpresaByEmpresaId(EmpresaDTO empresaByEmpresaId) {
        this.empresaByEmpresaId = empresaByEmpresaId;
    }


}
