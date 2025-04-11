using System.Collections.Generic;
using AvaloniaApplication1.Models;

namespace AvaloniaApplication1.Repositories.Interfaces
{
    public interface IInscriereRepository : IRepository<long, Inscriere>
    {
        IEnumerable<Inscriere> FindByParticipant(Participant participant);
        Inscriere? Update(Inscriere entity);
        Inscriere? Save(Inscriere entity);

    }
}