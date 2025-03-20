package org.example.Repository.Interfaces;

import org.example.Model.Participant;

import java.util.List;

public interface ParticipantRepository {
    List<Participant> findAll();

    List<Participant> findByNameandSurname(String name, String surname);

    List<Participant> findByEmail(String email);
}
