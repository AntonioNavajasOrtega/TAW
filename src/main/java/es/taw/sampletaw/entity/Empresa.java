package es.taw.sampletaw.entity;

import es.taw.sampletaw.dto.DTO;
import es.taw.sampletaw.dto.EmpleadoDTO;
import es.taw.sampletaw.dto.EmpresaDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Empresa implements DTO<EmpresaDTO> {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empresa empresa = (Empresa) o;

        if (id != null ? !id.equals(empresa.id) : empresa.id != null) return false;
        if (nombre != null ? !nombre.equals(empresa.nombre) : empresa.nombre != null) return false;
        if (direccion != null ? !direccion.equals(empresa.direccion) : empresa.direccion != null) return false;
        if (telefono != null ? !telefono.equals(empresa.telefono) : empresa.telefono != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        return result;
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

    public EmpresaDTO toDTO(){
        EmpresaDTO dto = new EmpresaDTO();
        dto.setId(this.id);
        dto.setNombre(this.nombre);
        dto.setDireccion(this.direccion);
        dto.setTelefono(this.telefono);

        return dto;
    }

}
