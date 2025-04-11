using System.Collections.Generic;
using AvaloniaApplication1.Models;

namespace AvaloniaApplication1.Repositories.Interfaces
{
    public interface IConcursRepository
    {
        IEnumerable<Concurs> FindByName(string name);
        IEnumerable<Concurs> FindById(string id);
        IEnumerable<Concurs> FindByLocation(string location);
        IEnumerable<Concurs> FindAll();
    }
}