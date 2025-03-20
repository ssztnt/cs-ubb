package org.example.Repository.Interfaces;

import org.example.Model.Inscriere;
import org.example.Model.Participant;

import java.util.Optional;

public interface InscriereRepository extends iRepository<Long, Inscriere>{
    Iterable<Inscriere> findByid_inscriere(Inscriere inscriere);
    Iterable<Inscriere> findByid_participant(Participant participant);


    Optional<Inscriere> update(Inscriere entity);
}
