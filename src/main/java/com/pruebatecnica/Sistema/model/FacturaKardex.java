package com.pruebatecnica.Sistema.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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

    @Column(name = "KarCosUnit", nullable = true)
    private Integer karCosUnit;

    @Column(name = "KarPreVen", nullable = true)
    private Integer karPreVen;

    @Temporal(TemporalType.DATE)
    @Column(name = "KarFecVencProd", nullable = true)
    private Date karFecVencProd;

    @ManyToOne
    @JoinColumn(name = "FacNum", nullable = false)
    private Factura factura;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ArtCod", referencedColumnName = "ArtCod", insertable = false, updatable = false)
    private Articulo articulo;

    public FacturaKardex() {}

    public FacturaKardex(int karCantInit, int karCantEnt, int karCantSal, int karSaldo, 
                        Integer karCosUnit, Integer karPreVen, Date karFecVencProd, 
                        Factura factura, Articulo articulo) {
        this.karCantInit = karCantInit;
        this.karCantEnt = karCantEnt;
        this.karCantSal = karCantSal;
        this.karSaldo = karSaldo;
        this.karCosUnit = karCosUnit;
        this.karPreVen = karPreVen;
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

    public Integer getKarCosUnit() {
        return karCosUnit;
    }

    public void setKarCosUnit(Integer karCosUnit) {
        this.karCosUnit = karCosUnit;
    }

    public Integer getKarPreVen() {
        return karPreVen;
    }

    public void setKarPreVen(Integer karPreVen) {
        this.karPreVen = karPreVen;
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
