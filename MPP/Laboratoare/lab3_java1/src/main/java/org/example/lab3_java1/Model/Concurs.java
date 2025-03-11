package org.example.lab3_java1.Model;

public class Concurs {
    private String id_concurs;
    private String nume;
    private String data;
    private String locatie;

    public Concurs(String id_concurs, String nume, String data, String locatie) {
        this.id_concurs = id_concurs;
        this.nume = nume;
        this.data = data;
        this.locatie = locatie;
    }

    public String getId_concurs() {
        return id_concurs;
    }

    public void setId_concurs(String id_concurs) {
        this.id_concurs = id_concurs;
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
                "id_concurs='" + id_concurs + '\'' +
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
        return id_concurs.equals(concurs.id_concurs);
    }

    @Override
    public int hashCode() {
        return id_concurs.hashCode();
    }
}
