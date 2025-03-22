package org.example.Model;

public class Inscriere extends Entity<Long> {
    private String id_inscriere;
    private String id_participant;
    private String data_inscriere;

    public Inscriere(String id_inscriere, String id_participant, String data_inscriere) {
        this.id_inscriere = id_inscriere;
        this.id_participant = id_participant;
        this.data_inscriere = data_inscriere;
    }

    public String getId_inscriere() {
        return id_inscriere;
    }

    public void setId_inscriere(String id_inscriere) {
        this.id_inscriere = id_inscriere;
    }

    public String getId_participant() {
        return id_participant;
    }

    public void setId_participant(String id_participant) {
        this.id_participant = id_participant;
    }

    public String getData_inscriere() {
        return data_inscriere;
    }

    public void setData_inscriere(String data_inscriere) {
        this.data_inscriere = data_inscriere;
    }

    @Override
    public String toString() {
        return "Inscriere{" +
                "id_inscriere='" + id_inscriere + '\'' +
                ", id_participant='" + id_participant + '\'' +
                ", data_inscriere='" + data_inscriere + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inscriere inscriere = (Inscriere) o;
        return id_inscriere.equals(inscriere.id_inscriere);
    }

    @Override
    public int hashCode() {
        return id_inscriere.hashCode();
    }
}