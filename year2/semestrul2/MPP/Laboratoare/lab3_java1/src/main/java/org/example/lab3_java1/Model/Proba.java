package org.example.lab3_java1.Model;

public class Proba {
    private String id_proba;
    private String nume_proba;
    private String distanta;
    private String id_concurs;

    public Proba(String id_proba, String nume_proba, String distanta, String id_concurs) {
        this.id_proba = id_proba;
        this.nume_proba = nume_proba;
        this.distanta = distanta;
        this.id_concurs = id_concurs;
    }

    public String getId_proba() {
        return id_proba;
    }

    public void setId_proba(String id_proba) {
        this.id_proba = id_proba;
    }

    public String getNume_proba() {
        return nume_proba;
    }

    public void setNume_proba(String nume_proba) {
        this.nume_proba = nume_proba;
    }

    public String getDistanta() {
        return distanta;
    }

    public void setDistanta(String distanta) {
        this.distanta = distanta;
    }

    public String getId_concurs() {
        return id_concurs;
    }

    public void setId_concurs(String id_concurs) {
        this.id_concurs = id_concurs;
    }

    @Override
    public String toString() {
        return "Proba{" +
                "id_proba='" + id_proba + '\'' +
                ", nume_proba='" + nume_proba + '\'' +
                ", distanta='" + distanta + '\'' +
                ", id_concurs='" + id_concurs + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proba proba = (Proba) o;
        return id_proba.equals(proba.id_proba);
    }

    @Override
    public int hashCode() {
        return id_proba.hashCode();
    }

}
