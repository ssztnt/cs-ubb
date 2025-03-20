using AtletismFinal.Domain;
using System.Collections.Generic;

namespace AtletismFinal.Repository
{
    public interface ParticipantRepository
    {
        List<Participant> FindAll();

        List<Participant> FindByNameAndSurname(string name, string surname);

        List<Participant> FindByEmail(string email);
    }
}