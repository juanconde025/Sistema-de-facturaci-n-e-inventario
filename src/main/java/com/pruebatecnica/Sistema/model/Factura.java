package com.pruebatecnica.Sistema.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @Column(name = "FacNum", nullable = false, length = 45)
    private String facNum;

    @Column(name = "FacFecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate facFecha;

    @Column(name = "FacFVen", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate facFVen;

    @Column(name = "FacTVenta", nullable = false)
    private int facTVenta;

    @ManyToOne
    @JoinColumn(name = "NitDoc", nullable = false)
    private Nit nit;

    @ManyToOne
    @JoinColumn(name = "ArtCod", nullable = false)
    private Articulo articulo;

    public Factura() {}

    public Factura(String facNum, LocalDate facFecha, LocalDate facFVen, int facTVenta, Nit nit, Articulo articulo) {
        this.facNum = facNum;
        this.facFecha = facFecha;
        this.facFVen = facFVen;
        this.facTVenta = facTVenta;
        this.nit = nit;
        this.articulo = articulo;
    }

    public String getFacNum() {
        return facNum;
    }

    public void setFacNum(String facNum) {
        this.facNum = facNum;
    }

    public LocalDate getFacFecha() {
        return facFecha;
    }

    public void setFacFecha(LocalDate facFecha) {
        this.facFecha = facFecha;
    }

    public LocalDate getFacFVen() {
        return facFVen;
    }

    public void setFacFVen(LocalDate facFVen) {
        this.facFVen = facFVen;
    }

    public int getFacTVenta() {
        return facTVenta;
    }

    public void setFacTVenta(int facTVenta) {
        this.facTVenta = facTVenta;
    }

    public Nit getNit() {
        return nit;
    }

    public void setNit(Nit nit) {
        this.nit = nit;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}

