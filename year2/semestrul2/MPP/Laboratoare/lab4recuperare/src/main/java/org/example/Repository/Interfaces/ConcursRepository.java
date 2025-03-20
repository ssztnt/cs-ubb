package org.example.Repository.Interfaces;

import org.example.Model.Concurs;

public interface ConcursRepository {
    Iterable<Concurs> findByName(String name);

    Iterable<Concurs> findbyID(Long id);

    Iterable<Concurs> findbyLocation(String location);
}
