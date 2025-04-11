package com.example.project.service;

import com.example.project.domain.Bug;
import com.example.project.domain.TypeEnum;
import com.example.project.domain.User;
import com.example.project.observer.Observable;
import com.example.project.observer.Observer;
import com.example.project.repo.BugsRepository;
import com.example.project.repo.UserRepository;
import com.example.project.utils.BugTaskChangeEvent;

import java.util.ArrayList;
import java.util.List;

public class Service  implements Observable<BugTaskChangeEvent> {
    private UserRepository userRepository;
    private BugsRepository bugsRepository;

    private List<Observer<BugTaskChangeEvent>> observers=new ArrayList<>();


    public Service(UserRepository userRepository, BugsRepository bugsRepository) {
        this.userRepository = userRepository;
        this.bugsRepository = bugsRepository;
    }

    public void add_user(User user)
    {
        userRepository.save(user);
    }
    public User find_user_by_data(String usernameField, String passwordField, TypeEnum type) {
        return userRepository.find_by_data(usernameField,passwordField,type);
    }

    @Override
    public void addObserver(Observer<BugTaskChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<BugTaskChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(BugTaskChangeEvent t) {
        observers.forEach(x->x.update(t));
    }


    public Iterable<Bug> findAllBugs() {
        return bugsRepository.findAll();
    }

    public void addBug(Bug bug) {
        bugsRepository.save(bug);
    }

    public void resolveBug(Bug bug, User user) {
        bugsRepository.resolve(bug,user);
    }

    public User find_Programmer(Long idProgrammer) {
        return userRepository.findOne(idProgrammer);
    }
}
