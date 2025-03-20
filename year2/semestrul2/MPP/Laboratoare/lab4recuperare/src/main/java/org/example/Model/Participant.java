package org.example.Model;

import java.util.Objects;

public class Participant {
    private String id_participant;
    private String nume;
    private String prenume;
    private String varsta;
    private String email;

    public Participant(String id_participant, String nume, String prenume, String varsta, String email) {
        this.id_participant = id_participant;
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.email = email;
    }

    public String getId_participant() {
        return id_participant;
    }

    public void setId_participant(String id_participant) {
        this.id_participant = id_participant;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getVarsta() {
        return varsta;
    }

    public void setVarsta(String varsta) {
        this.varsta = varsta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant)) return false;
        Participant that = (Participant) o;
        return id_participant.equals(that.id_participant) &&
                nume.equals(that.nume) &&
                prenume.equals(that.prenume) &&
                varsta.equals(that.varsta) &&
                email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_participant, nume, prenume, varsta, email);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id_participant='" + id_participant + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", varsta='" + varsta + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}