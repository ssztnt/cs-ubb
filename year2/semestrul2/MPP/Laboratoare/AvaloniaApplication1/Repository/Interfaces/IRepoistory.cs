using System.Collections.Generic;
using AvaloniaApplication1.Models;

namespace AvaloniaApplication1.Repositories.Interfaces
{
    public interface IRepository<ID, E> where E : Entity<ID>
    {
        E? FindOne(ID id);
        IEnumerable<E> FindAll();
        E? Save(E entity);
        E? Delete(ID id);
        E? Update(ID id, E entity);
    }
}