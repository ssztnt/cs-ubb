using AtletismFinal.Domain;
using System;
using System.Collections.Generic;
using AtletismFinal.Domain;

namespace AtletismFinal.repository
{
    public interface IRepository<ID, E> where E : Entity<ID>
    {
        /// <summary>
        /// Saves the given entity.
        /// </summary>
        /// <param name="entity">The entity to save. Must not be null.</param>
        /// <returns>
        /// - null if the entity was saved successfully.
        /// - The entity if an entity with the same ID already exists.
        /// </returns>
        /// <exception cref="ArgumentNullException">Thrown if the given entity is null.</exception>
        E? Save(E entity);

        /// <summary>
        /// Deletes the entity with the specified ID.
        /// </summary>
        /// <param name="id">The ID of the entity to delete. Must not be null.</param>
        /// <returns>
        /// - null if no entity with the given ID exists.
        /// - The removed entity, otherwise.
        /// </returns>
        /// <exception cref="ArgumentNullException">Thrown if the given ID is null.</exception>
        E? Delete(ID id);

        /// <summary>
        /// Updates the given entity.
        /// </summary>
        /// <param name="updatedEntity">The entity to update. Must not be null.</param>
        /// <returns>
        /// - null if the entity was updated successfully.
        /// - The entity if the update failed (e.g., the ID does not exist).
        /// </returns>
        /// <exception cref="ArgumentNullException">Thrown if the given entity is null.</exception>
        E? Update(E updatedEntity);

        /// <summary>
        /// Finds the entity with the specified ID.
        /// </summary>
        /// <param name="id">The ID of the entity to find. Must not be null.</param>
        /// <returns>The entity with the given ID, or null if no such entity exists.</returns>
        /// <exception cref="ArgumentNullException">Thrown if the given ID is null.</exception>
        E? FindOne(ID id);

        /// <summary>
        /// Retrieves all entities in the repository.
        /// </summary>
        /// <returns>A list of all entities.</returns>
        List<E> FindAll();
    }
}