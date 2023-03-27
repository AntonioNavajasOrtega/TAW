package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Cliente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Basic
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;
    @Basic
    @Column(name = "direccion", nullable = true, length = 100)
    private String direccion;
    @Basic
    @Column(name = "telefono", nullable = true, length = 12)
    private String telefono;
    @Basic
    @Column(name = "email", nullable = true, length = 50)
    private String email;
    @Basic
    @Column(name = "contrasena", nullable = true, length = 50)
    private String contrasena;
    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresaByEmpresaId;
    @OneToMany(mappedBy = "clienteByCliente")
    private Collection<Conversacion> conversacionsById;
    @OneToMany(mappedBy = "clienteByClienteId")
    private Collection<Cuenta> cuentasById;
    @OneToMany(mappedBy = "clienteByClienteId")
    private Collection<Solicitud> solicitudsById;
    @OneToMany(mappedBy = "clienteByClienteId")
    private Collection<Tipoclienterelacionado> tipoclienterelacionadosById;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (id != cliente.id) return false;
        if (nombre != null ? !nombre.equals(cliente.nombre) : cliente.nombre != null) return false;
        if (apellido != null ? !apellido.equals(cliente.apellido) : cliente.apellido != null) return false;
        if (direccion != null ? !direccion.equals(cliente.direccion) : cliente.direccion != null) return false;
        if (telefono != null ? !telefono.equals(cliente.telefono) : cliente.telefono != null) return false;
        if (email != null ? !email.equals(cliente.email) : cliente.email != null) return false;
        if (contrasena != null ? !contrasena.equals(cliente.contrasena) : cliente.contrasena != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellido != null ? apellido.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (contrasena != null ? contrasena.hashCode() : 0);
        return result;
    }

    public Empresa getEmpresaByEmpresaId() {
        return empresaByEmpresaId;
    }

    public void setEmpresaByEmpresaId(Empresa empresaByEmpresaId) {
        this.empresaByEmpresaId = empresaByEmpresaId;
    }

    public Collection<Conversacion> getConversacionsById() {
        return conversacionsById;
    }

    public void setConversacionsById(Collection<Conversacion> conversacionsById) {
        this.conversacionsById = conversacionsById;
    }

    public Collection<Cuenta> getCuentasById() {
        return cuentasById;
    }

    public void setCuentasById(Collection<Cuenta> cuentasById) {
        this.cuentasById = cuentasById;
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
}
