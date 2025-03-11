using System.Collections.Generic;
using lab3_cscs.Model;

namespace lab3_cscs.Repository
{
    /// <summary>
    /// CRUD operations repository interface
    /// </summary>
    /// <typeparam name="ID">Type E must have an attribute of type ID</typeparam>
    /// <typeparam name="E">Type of entities saved in repository</typeparam>
    public interface IRepository<ID, E> where E : Entity<ID>
    {
        E? FindOne(ID id);
        /// <summary>
        /// @return all entities
        /// </summary>
        IEnumerable<E> FindAll();
        E? Save(E entity);
        E? Delete(ID id);
        E? Update(E entity);
    }
}