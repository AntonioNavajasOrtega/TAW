package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Cuenta {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "iban", nullable = false, length = 34)
    private String iban;
    @Basic
    @Column(name = "saldo", nullable = false, precision = 2)
    private BigDecimal saldo;
    @Basic
    @Column(name = "swift", nullable = false, length = 11)
    private String swift;
    @Basic
    @Column(name = "pais", nullable = false, length = 50)
    private String pais;
    @ManyToOne
    @JoinColumn(name = "estado", referencedColumnName = "id", nullable = false)
    private EstadoCuenta estadoCuentaByEstado;
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente clienteByClienteId;
    @ManyToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id", nullable = false)
    private Empleado empleadoByEmpleadoId;
    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresaByEmpresaId;
    @OneToMany(mappedBy = "cuentaByCuentaId")
    private Collection<Solicitud> solicitudsById;
    @OneToMany(mappedBy = "cuentaByCuentaId")
    private Collection<Tipoclienterelacionado> tipoclienterelacionadosById;
    @OneToMany(mappedBy = "cuentaByCuentaOrigenId")
    private Collection<Transaccion> transaccionsById;
    @OneToMany(mappedBy = "cuentaByCuentaDestinoId")
    private Collection<Transaccion> transaccionsById_0;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(id, cuenta.id) && Objects.equals(iban, cuenta.iban) && Objects.equals(saldo, cuenta.saldo) && Objects.equals(swift, cuenta.swift) && Objects.equals(pais, cuenta.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iban, saldo, swift, pais);
    }

    public EstadoCuenta getEstadoCuentaByEstado() {
        return estadoCuentaByEstado;
    }

    public void setEstadoCuentaByEstado(EstadoCuenta estadoCuentaByEstado) {
        this.estadoCuentaByEstado = estadoCuentaByEstado;
    }

    public Cliente getClienteByClienteId() {
        return clienteByClienteId;
    }

    public void setClienteByClienteId(Cliente clienteByClienteId) {
        this.clienteByClienteId = clienteByClienteId;
    }

    public Empleado getEmpleadoByEmpleadoId() {
        return empleadoByEmpleadoId;
    }

    public void setEmpleadoByEmpleadoId(Empleado empleadoByEmpleadoId) {
        this.empleadoByEmpleadoId = empleadoByEmpleadoId;
    }

    public Empresa getEmpresaByEmpresaId() {
        return empresaByEmpresaId;
    }

    public void setEmpresaByEmpresaId(Empresa empresaByEmpresaId) {
        this.empresaByEmpresaId = empresaByEmpresaId;
    }

    public Collection<Solicitud> getSolicitudsById() {
        return solicitudsById;
    }

    public void setSolicitudsById(Collection<Solicitud> solicitudsById) {
        this.solicitudsById = solicitudsById;
    }

    public Collection<Tipoclienterelacionado> getTipoclienterelacionadosById() {
        return tipoclienterelacionadosById;
    }

    public void setTipoclienterelacionadosById(Collection<Tipoclienterelacionado> tipoclienterelacionadosById) {
        this.tipoclienterelacionadosById = tipoclienterelacionadosById;
    }

    public Collection<Transaccion> getTransaccionsById() {
        return transaccionsById;
    }

    public void setTransaccionsById(Collection<Transaccion> transaccionsById) {
        this.transaccionsById = transaccionsById;
    }

    public Collection<Transaccion> getTransaccionsById_0() {
        return transaccionsById_0;
    }

    public void setTransaccionsById_0(Collection<Transaccion> transaccionsById_0) {
        this.transaccionsById_0 = transaccionsById_0;
    }
}
