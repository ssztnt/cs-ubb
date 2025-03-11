package org.example.lab3_java1.Model;

public class Oficiu {
    private String id_oficiu;
    private String nume_oficiu;
    private String adresa;

    public Oficiu(String id_oficiu, String nume_oficiu, String adresa) {
        this.id_oficiu = id_oficiu;
        this.nume_oficiu = nume_oficiu;
        this.adresa = adresa;
    }

    public String getId_oficiu() {
        return id_oficiu;
    }

    public void setId_oficiu(String id_oficiu) {
        this.id_oficiu = id_oficiu;
    }

    public String getNume_oficiu() {
        return nume_oficiu;
    }

    public void setNume_oficiu(String nume_oficiu) {
        this.nume_oficiu = nume_oficiu;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Oficiu{" +
                "id_oficiu='" + id_oficiu + '\'' +
                ", nume_oficiu='" + nume_oficiu + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oficiu oficiu = (Oficiu) o;
        return id_oficiu.equals(oficiu.id_oficiu);

    }

    @Override
    public int hashCode() {
        return id_oficiu.hashCode();
    }
}
