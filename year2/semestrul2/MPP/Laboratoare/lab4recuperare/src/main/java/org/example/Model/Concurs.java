package org.example.Model;

import java.util.Objects;

public class Concurs {
    private String idConcurs;
    private String nume;
    private String data;
    private String locatie;

    public Concurs(String idConcurs, String nume, String data, String locatie) {
        this.idConcurs = idConcurs;
        this.nume = nume;
        this.data = data;
        this.locatie = locatie;
    }

    public String getIdConcurs() {
        return idConcurs;
    }

    public void setIdConcurs(String idConcurs) {
        this.idConcurs = idConcurs;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    @Override
    public String toString() {
        return "Concurs{" +
                "idConcurs='" + idConcurs + '\'' +
                ", nume='" + nume + '\'' +
                ", data='" + data + '\'' +
                ", locatie='" + locatie + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concurs concurs = (Concurs) o;
        return idConcurs.equals(concurs.idConcurs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConcurs);
    }
}