package com.pruebatecnica.Sistema.dto;

import java.util.Date;

public class MovimientoKardexDTO {
    private String artCod;
    private String facNum;
    private int karCantEnt;
    private int karCantSal;
    private int karCosUnit;
    private int karPreVen;
    private Date karFecVencProd;

    public String getArtCod() {
        return artCod;
    }

    public void setArtCod(String artCod) {
        this.artCod = artCod;
    }

    public String getFacNum() {
        return facNum;
    }

    public void setFacNum(String facNum) {
        this.facNum = facNum;
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