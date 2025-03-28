package com.example.project.repo;

import com.example.project.domain.Entity;

public interface RepositoryInterface <ID, E extends Entity<ID>>{

    E findOne(ID id);

    Iterable<E> findAll();

    void save(E entity);

    void delete(ID id);

    void update(E entity);

}
