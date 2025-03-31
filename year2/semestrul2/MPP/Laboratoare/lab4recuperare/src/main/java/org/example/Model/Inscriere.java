package org.example.Model;

public class Inscriere extends Entity<Long> {
    private String id_inscriere;
    private String id_participant;
    private String concurs_name;
    private String timestamp;

    public Inscriere(String id_inscriere, String id_participant, String concurs_name, String timestamp) {
        this.id_inscriere = id_inscriere;
        this.id_participant = id_participant;
        this.concurs_name = concurs_name;
        this.timestamp = timestamp;
    }

    public String getId_inscriere() {
        return id_inscriere;
    }

    public String getId_participant() {
        return id_participant;
    }

    public String getConcurs_name() {
        return concurs_name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inscriere)) return false;
        Inscriere i = (Inscriere) o;
        return java.util.Objects.equals(id_inscriere, i.id_inscriere);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id_inscriere);
    }

    @Override
    public String toString() {
        return "Inscriere{" +
                "id='" + id_inscriere + '\'' +
                ", participant='" + id_participant + '\'' +
                ", concurs='" + concurs_name + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
