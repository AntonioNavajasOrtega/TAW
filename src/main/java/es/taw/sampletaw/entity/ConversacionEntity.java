package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "conversacion", schema = "bancotaw", catalog = "")
public class ConversacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "abierta", nullable = false)
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
        ConversacionEntity that = (ConversacionEntity) o;
        return id == that.id && abierta == that.abierta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, abierta);
    }
}
