package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "empleado", schema = "bancotaw", catalog = "")
public class EmpleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombreUsuario;
    @Basic
    @Column(name = "contrasena", nullable = false, length = 100)
    private String contrasena;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
        EmpleadoEntity that = (EmpleadoEntity) o;
        return id == that.id && Objects.equals(nombreUsuario, that.nombreUsuario) && Objects.equals(contrasena, that.contrasena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreUsuario, contrasena);
    }
}
