package org.example.Service;

import org.example.Model.Participant;
import java.util.List;

public interface AtletismService {
    List<Participant> getAllParticipants();
    List<Participant> getParticipantsByNameAndSurname(String name, String surname);
    List<Participant> getParticipantsByEmail(String email);
    void saveParticipant(Participant participant); // Add this method
}