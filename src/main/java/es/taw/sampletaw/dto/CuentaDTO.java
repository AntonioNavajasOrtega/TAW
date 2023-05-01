package es.taw.sampletaw.dto;

import es.taw.sampletaw.entity.*;

import java.io.Serializable;
import java.math.BigDecimal;

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