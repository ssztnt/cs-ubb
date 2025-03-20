package org.example.Repository.Interfaces;
import org.example.Model.Entity;
import org.example.Model.Inscriere;

import java.util.Optional;

/**
 * CRUD operations repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> - type of entities saved in repository
 */
public interface iRepository<ID,E extends Entity<ID>> {
    Optional<Inscriere> findOne(ID id);
    /**
     *
     * @return all entities
     */
    Iterable<E> findAll();
    Optional<Inscriere> save(E entity);
    Optional<Inscriere> delete(ID id);
    E update(ID id,E entity);
}