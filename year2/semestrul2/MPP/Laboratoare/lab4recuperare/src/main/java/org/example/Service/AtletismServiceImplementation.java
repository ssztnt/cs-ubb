package org.example.Service;

import org.example.Model.Concurs;
import org.example.Model.Inscriere;
import org.example.Model.Participant;
import org.example.Repository.DBConcursRepository;
import org.example.Repository.DBInscriereRepository;
import org.example.Repository.DBParticipantRepository;
import org.example.Repository.Interfaces.ParticipantRepository;

import java.util.ArrayList;
import java.util.List;

public class AtletismServiceImplementation implements AtletismService {
    private DBParticipantRepository participantRepository;
    private DBInscriereRepository inscriereRepository;
    private DBConcursRepository concursRepository;

    public AtletismServiceImplementation(ParticipantRepository participantRepository, DBInscriereRepository inscriereRepository, DBConcursRepository concursRepository) {
        this.participantRepository = (DBParticipantRepository) participantRepository;
        this.inscriereRepository = inscriereRepository;
        this.concursRepository = concursRepository;
    }


    @Override
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public List<Participant> getParticipantsByNameAndSurname(String name, String surname) {
        return participantRepository.findByNameandSurname(name, surname);
    }

    @Override
    public List<Participant> getParticipantsByEmail(String email) {
        return participantRepository.findByEmail(email);
    }

    @Override
    public void saveParticipant(Participant participant) {
        participantRepository.saveParticipant(participant);
    }

    @Override
    public void saveInscriere(Inscriere inscriere) {
        inscriereRepository.save(inscriere);
    }

    @Override
    public List<Concurs> getConcursList() {
        List<Concurs> list = new ArrayList<>();
        concursRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public List<Inscriere> getAllInscrieri() {
        List<Inscriere> list = new ArrayList<>();
        inscriereRepository.findAll().forEach(list::add);
        return list;
    }


    @Override
    public List<Concurs> getConcursByName(String name) {
        List<Concurs> list = new ArrayList<>();
        concursRepository.findByName(name).forEach(list::add);
        return list;
    }



}