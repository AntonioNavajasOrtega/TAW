package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Empresa {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Basic
    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;
    @Basic
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;
    @Basic
    @Column(name = "usuario", nullable = false, length = 20)
    private String usuario;
    @Basic
    @Column(name = "contrasena", nullable = false, length = 20)
    private String contrasena;
    @OneToMany(mappedBy = "empresaByEmpresaId")
    private Collection<Cliente> clientesById;
    @OneToMany(mappedBy = "empresaByEmpresaId")
    private Collection<Cuenta> cuentasById;
    @OneToMany(mappedBy = "empresaByEmpresaId")
    private Collection<Solicitud> solicitudsById;

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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
        Empresa empresa = (Empresa) o;
        return Objects.equals(id, empresa.id) && Objects.equals(nombre, empresa.nombre) && Objects.equals(direccion, empresa.direccion) && Objects.equals(telefono, empresa.telefono) && Objects.equals(usuario, empresa.usuario) && Objects.equals(contrasena, empresa.contrasena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, direccion, telefono, usuario, contrasena);
    }

    public Collection<Cliente> getClientesById() {
        return clientesById;
    }

    public void setClientesById(Collection<Cliente> clientesById) {
        this.clientesById = clientesById;
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
}
