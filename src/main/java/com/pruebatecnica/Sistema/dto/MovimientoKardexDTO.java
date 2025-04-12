package com.pruebatecnica.Sistema.dto;

import java.util.Date;

import com.pruebatecnica.Sistema.model.Articulo;
import com.pruebatecnica.Sistema.model.Factura;

public class MovimientoKardexDTO {
    private Articulo articulo;  
    private Factura factura; 
    private int karCantEnt;
    private int karCantSal;
    private int karCosUnit;
    private int karPreVen;
    private Date karFecVencProd;

    // Getters y Setters corregidos
    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
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

    public int getKarCosUnit() {
        return karCosUnit;
    }

    public void setKarCosUnit(int karCosUnit) {
        this.karCosUnit = karCosUnit;
    }

    public int getKarPreVen() {
        return karPreVen;
    }

    public void setKarPreVen(int karPreVen) {
        this.karPreVen = karPreVen;
    }

    public Date getKarFecVencProd() {
        return karFecVencProd;
    }

    public void setKarFecVencProd(Date karFecVencProd) {
        this.karFecVencProd = karFecVencProd;
    }
}