package org.example.laboratorrest.service;

import org.example.laboratorrest.model.Proba;
import org.example.laboratorrest.repository.ProbaRepository;
import org.example.laboratorrest.ProbaWebSocketController;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProbaService {
    private final ProbaRepository repo;
    private final ProbaWebSocketController wsController;

    public ProbaService(ProbaRepository repo, ProbaWebSocketController wsController) {
        this.repo = repo;
        this.wsController = wsController;
    }

    public List<Proba> getAll() {
        return repo.findAll();
    }

    public Proba getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Proba save(Proba proba) {
        Proba saved = repo.save(proba);
        wsController.sendUpdate(repo.findAll());  // 🔁 trimite lista actualizată
        return saved;
    }

    public void delete(Long id) {
        repo.deleteById(id);
        wsController.sendUpdate(repo.findAll());  // 🔁 trimite lista actualizată
    }
}