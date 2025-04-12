package com.pruebatecnica.Sistema.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "articulos")
public class Articulo {

    @Id
    @Column(name = "ArtCod", nullable = false, length = 30)
    private String artCod;

    @Column(name = "ArtNom", nullable = false, length = 45)
    private String artNom;

    @Column(name = "ArtLab", nullable = false, length = 45)
    private String artLab;

    @Column(name = "ArtSal", nullable = false)
    private int artSal;

    @Column(name = "ArtCos", nullable = false)
    private int artCos;

    @Column(name = "ArtPrV", nullable = false)
    private int artPrV;

    @Column(name = "ArtFVen", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date artFVen;

    public Articulo() {}
    
    public Articulo(String artCod, String artNom, String artLab, int artSal, int artCos, int artPrV, Date artFVen) {
        this.artCod = artCod;
        this.artNom = artNom;
        this.artLab = artLab;
        this.artSal = artSal;
        this.artCos = artCos;
        this.artPrV = artPrV;
        this.artFVen = artFVen;
    }

    public String getArtCod() {
        return artCod;
    }

    public void setArtCod(String artCod) {
        this.artCod = artCod;
    }

    public String getArtNom() {
        return artNom;
    }

    public void setArtNom(String artNom) {
        this.artNom = artNom;
    }

    public String getArtLab() {
        return artLab;
    }

    public void setArtLab(String artLab) {
        this.artLab = artLab;
    }

    public int getArtSal() {
        return artSal;
    }

    public void setArtSal(int artSal) {
        this.artSal = artSal;
    }

    public int getArtCos() {
        return artCos;
    }

    public void setArtCos(int artCos) {
        this.artCos = artCos;
    }

    public int getArtPrV() {
        return artPrV;
    }

    public void setArtPrV(int artPrV) {
        this.artPrV = artPrV;
    }

    public Date getArtFVen() {
        return artFVen;
    }

    public void setArtFVen(Date artFVen) {
        this.artFVen = artFVen;
    }
}
