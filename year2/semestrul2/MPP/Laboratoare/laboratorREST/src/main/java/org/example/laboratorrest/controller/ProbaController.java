package org.example.laboratorrest.controller;

import org.example.laboratorrest.ProbaWebSocketController;
import org.example.laboratorrest.model.Proba;
import org.example.laboratorrest.repository.ProbaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/probe")
public class ProbaController {

    @Autowired
    private ProbaRepository repository;

    @Autowired
    private ProbaWebSocketController webSocketController;

    @GetMapping
    public List<Proba> getAllProbes() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proba> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Proba> addProba(@RequestBody Proba proba) {
        Proba saved = repository.save(proba);
        sendLiveUpdate();
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        sendLiveUpdate();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proba> update(@PathVariable Long id, @RequestBody Proba newProba) {
        return repository.findById(id)
                .map(proba -> {
                    proba.setNume(newProba.getNume());
                    proba.setCategorie(newProba.getCategorie());
                    proba.setDurata(newProba.getDurata());
                    Proba updated = repository.save(proba);
                    sendLiveUpdate();
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private void sendLiveUpdate() {
        List<Proba> allProbes = repository.findAll();
        webSocketController.sendUpdate(allProbes); // 🔴 Trimite lista actualizată
    }
}