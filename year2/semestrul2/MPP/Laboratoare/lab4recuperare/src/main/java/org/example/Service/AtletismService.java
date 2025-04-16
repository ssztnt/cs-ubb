package org.example.Service;

import org.example.Model.Concurs;
import org.example.Model.Inscriere;
import org.example.Model.Participant;
import java.util.List;

public interface AtletismService {
    List<Participant> getAllParticipants();
    List<Participant> getParticipantsByEmail(String email);
    void saveParticipant(Participant participant); // Add this method
    void saveInscriere(Inscriere inscriere);
    List<Concurs> getConcursList();
    List<Inscriere> getAllInscrieri();
    List<Concurs> getConcursByName(String name);


}