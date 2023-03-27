package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Mensaje {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;
    @Basic
    @Column(name = "contenido", nullable = false, length = 400)
    private String contenido;
    @ManyToOne
    @JoinColumn(name = "conversacion", referencedColumnName = "id", nullable = false)
    private Conversacion conversacionByConversacion;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mensaje mensaje = (Mensaje) o;

        if (id != mensaje.id) return false;
        if (fecha != null ? !fecha.equals(mensaje.fecha) : mensaje.fecha != null) return false;
        if (contenido != null ? !contenido.equals(mensaje.contenido) : mensaje.contenido != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (contenido != null ? contenido.hashCode() : 0);
        return result;
    }

    public Conversacion getConversacionByConversacion() {
        return conversacionByConversacion;
    }

    public void setConversacionByConversacion(Conversacion conversacionByConversacion) {
        this.conversacionByConversacion = conversacionByConversacion;
    }
}
