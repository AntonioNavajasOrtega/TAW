package es.taw.sampletaw.dto;

import es.taw.sampletaw.entity.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;


/**
 * @author Antonio Navajas Ortega 80 %
 * @author Javier Serrano Contreras 20 %
 */

public class CuentaDTO implements Serializable {

    private Integer id;
    private String iban;
    private BigDecimal saldo;
    private String swift;
    private String pais;
    private EstadoCuentaDTO estadoCuentaByEstado;
    private ClienteDTO clienteByClienteId;
    private EmpleadoDTO empleadoByEmpleadoId;
    private EmpresaDTO empresaByEmpresaId;

    private Collection<TransaccionDTO> transaccionsById;

    private Collection<TransaccionDTO> transaccionsById_0;

    public Collection<TransaccionDTO> getTransaccionsById() {
        return transaccionsById;
    }

    public void setTransaccionsById(Collection<TransaccionDTO> transaccionsById) {
        this.transaccionsById = transaccionsById;
    }

    public Collection<TransaccionDTO> getTransaccionsById_0() {
        return transaccionsById_0;
    }

    public void setTransaccionsById_0(Collection<TransaccionDTO> transaccionsById_0) {
        this.transaccionsById_0 = transaccionsById_0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public EstadoCuentaDTO getEstadoCuentaByEstado() {
        return estadoCuentaByEstado;
    }

    public void setEstadoCuentaByEstado(EstadoCuentaDTO estadoCuentaByEstado) {
        this.estadoCuentaByEstado = estadoCuentaByEstado;
    }

    public ClienteDTO getClienteByClienteId() {
        return clienteByClienteId;
    }

    public void setClienteByClienteId(ClienteDTO clienteByClienteId) {
        this.clienteByClienteId = clienteByClienteId;
    }

    public EmpleadoDTO getEmpleadoByEmpleadoId() {
        return empleadoByEmpleadoId;
    }

    public void setEmpleadoByEmpleadoId(EmpleadoDTO empleadoByEmpleadoId) {
        this.empleadoByEmpleadoId = empleadoByEmpleadoId;
    }

    public EmpresaDTO getEmpresaByEmpresaId() {
        return empresaByEmpresaId;
    }

    public void setEmpresaByEmpresaId(EmpresaDTO empresaByEmpresaId) {
        this.empresaByEmpresaId = empresaByEmpresaId;
    }


}