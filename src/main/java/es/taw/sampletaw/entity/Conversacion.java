package es.taw.sampletaw.entity;

import javax.persistence.*;

@Entity
public class Conversacion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "abierta")
    private byte abierta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getAbierta() {
        return abierta;
    }

    public void setAbierta(byte abierta) {
        this.abierta = abierta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conversacion that = (Conversacion) o;

        if (id != that.id) return false;
        if (abierta != that.abierta) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) abierta;
        return result;
    }
}
