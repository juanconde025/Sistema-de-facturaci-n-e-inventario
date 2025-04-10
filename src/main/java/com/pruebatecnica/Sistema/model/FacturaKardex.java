package com.pruebatecnica.Sistema.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "facturakardex")
public class FacturaKardex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "KarCantInit", nullable = false)
    private int karCantInit;

    @Column(name = "KarCantEnt", nullable = false)
    private int karCantEnt;

    @Column(name = "KarCantSal", nullable = false)
    private int karCantSal;

    @Column(name = "KarSaldo", nullable = false)
    private int karSaldo;

    @Temporal(TemporalType.DATE)
    @Column(name = "KarFecVencProd", nullable = true)
    private Date karFecVencProd;

    @ManyToOne
    @JoinColumn(name = "FacNum", nullable = false)
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "ArtCod", nullable = false)
    private Articulo articulo;

    public FacturaKardex() {}

    public FacturaKardex(int karCantInit, int karCantEnt, int karCantSal, int karSaldo, Date karFecVencProd, Factura factura, Articulo articulo) {
        this.karCantInit = karCantInit;
        this.karCantEnt = karCantEnt;
        this.karCantSal = karCantSal;
        this.karSaldo = karSaldo;
        this.karFecVencProd = karFecVencProd;
        this.factura = factura;
        this.articulo = articulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getKarCantInit() {
        return karCantInit;
    }

    public void setKarCantInit(int karCantInit) {
        this.karCantInit = karCantInit;
    }

    public int getKarCantEnt() {
        return karCantEnt;
    }

    public void setKarCantEnt(int karCantEnt) {
        this.karCantEnt = karCantEnt;
    }

    public int getKarCantSal() {
        return karCantSal;
    }

    public void setKarCantSal(int karCantSal) {
        this.karCantSal = karCantSal;
    }

    public int getKarSaldo() {
        return karSaldo;
    }

    public void setKarSaldo(int karSaldo) {
        this.karSaldo = karSaldo;
    }

    public Date getKarFecVencProd() {
        return karFecVencProd;
    }

    public void setKarFecVencProd(Date karFecVencProd) {
        this.karFecVencProd = karFecVencProd;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
