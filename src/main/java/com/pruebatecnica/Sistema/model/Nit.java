package com.pruebatecnica.Sistema.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nit")
public class Nit {

    @Id
    @Column(name = "NitDoc", nullable = false, length = 15)
    private String nitDoc;

    @Column(name = "NitNom", nullable = false, length = 50)
    private String nitNom;

    @Column(name = "NitCup", nullable = false, length = 45)
    private String nitCup;

    @Column(name = "NitPla", nullable = false, length = 45)
    private String nitPla;

    @Column(name = "NitCar", nullable = false)
    private int nitCar;

    @Column(name = "NitDis", nullable = false)
    private int nitDis;

    public Nit() {}

    public Nit(String nitDoc, String nitNom, String nitCup, String nitPla, int nitCar, int nitDis) {
        this.nitDoc = nitDoc;
        this.nitNom = nitNom;
        this.nitCup = nitCup;
        this.nitPla = nitPla;
        this.nitCar = nitCar;
        this.nitDis = nitDis;
    }

    public String getNitDoc() {
        return nitDoc;
    }

    public void setNitDoc(String nitDoc) {
        this.nitDoc = nitDoc;
    }

    public String getNitNom() {
        return nitNom;
    }

    public void setNitNom(String nitNom) {
        this.nitNom = nitNom;
    }

    public String getNitCup() {
        return nitCup;
    }

    public void setNitCup(String nitCup) {
        this.nitCup = nitCup;
    }

    public String getNitPla() {
        return nitPla;
    }

    public void setNitPla(String nitPla) {
        this.nitPla = nitPla;
    }

    public int getNitCar() {
        return nitCar;
    }

    public void setNitCar(int nitCar) {
        this.nitCar = nitCar;
    }

    public int getNitDis() {
        return nitDis;
    }

    public void setNitDis(int nitDis) {
        this.nitDis = nitDis;
    }
}

