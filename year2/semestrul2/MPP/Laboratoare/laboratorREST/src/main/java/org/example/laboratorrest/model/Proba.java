package org.example.laboratorrest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "proba")
public class Proba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nume;
    private String categorie;
    private Integer durata;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Integer getDurata() {
        return durata;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }
}