using System.Collections.Generic;
using AvaloniaApplication1.Models;

namespace AvaloniaApplication1.Repositories.Interfaces
{
    public interface IParticipantRepository
    {
        List<Participant> FindAll();
        List<Participant> FindByNameAndSurname(string name, string surname);
        List<Participant> FindByEmail(string email);
        Participant? SaveParticipant(Participant participant);
    }
}