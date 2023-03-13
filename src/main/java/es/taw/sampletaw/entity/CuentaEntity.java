package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "cuenta", schema = "bancotaw", catalog = "")
public class CuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        CuentaEntity that = (CuentaEntity) o;
        return id == that.id && Objects.equals(iban, that.iban) && Objects.equals(saldo, that.saldo) && Objects.equals(swift, that.swift) && Objects.equals(pais, that.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iban, saldo, swift, pais);
    }
}
