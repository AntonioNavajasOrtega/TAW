package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Cliente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
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
    @OneToMany(mappedBy = "clienteByEmisorCliente")
    private Collection<Mensaje> mensajesById;
    @OneToMany(mappedBy = "clienteByReceptorCliente")
    private Collection<Mensaje> mensajesById_0;
    @OneToMany(mappedBy = "clienteByClienteId")
    private Collection<Solicitud> solicitudsById;
    @OneToMany(mappedBy = "clienteByClienteId")
    private Collection<Tipoclienterelacionado> tipoclienterelacionadosById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return Objects.equals(id, cliente.id) && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido, cliente.apellido) && Objects.equals(direccion, cliente.direccion) && Objects.equals(telefono, cliente.telefono) && Objects.equals(email, cliente.email) && Objects.equals(contrasena, cliente.contrasena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, direccion, telefono, email, contrasena);
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

    public Collection<Mensaje> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(Collection<Mensaje> mensajesById) {
        this.mensajesById = mensajesById;
    }

    public Collection<Mensaje> getMensajesById_0() {
        return mensajesById_0;
    }

    public void setMensajesById_0(Collection<Mensaje> mensajesById_0) {
        this.mensajesById_0 = mensajesById_0;
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
