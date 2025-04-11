package com.example.project.observer;


import com.example.project.utils.Event;

public interface Observer<E extends Event> {

    void update(E e);



}
