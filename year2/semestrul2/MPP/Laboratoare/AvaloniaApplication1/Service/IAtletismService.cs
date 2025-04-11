using System.Collections.Generic;
using AvaloniaApplication1.Models;

namespace AvaloniaApplication1.Services
{
    public interface IAtletismService
    {
        List<Participant> GetAllParticipants();
        List<Participant> GetParticipantsByNameAndSurname(string name, string surname);
        List<Participant> GetParticipantsByEmail(string email);
        void SaveParticipant(Participant participant);
        void SaveInscriere(Inscriere inscriere);
        List<Concurs> GetConcursList();
        List<Inscriere> GetAllInscrieri();
        List<Concurs> GetConcursByName(string name);
        List<Participant> FindAllParticipants();
        List<Concurs> FindAllConcursuri();
        
        void AddInscriere(string idParticipant , string concursName);
        List<Inscriere> FindAllInscrieri();
    }
}