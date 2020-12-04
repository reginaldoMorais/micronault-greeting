package com.example.micronault.greetings.repositories;

import com.example.micronault.greetings.models.Greeting;

import java.util.List;
import java.util.Optional;

public interface GreetingRepository {

    List<Greeting> findAll();

    Optional<Greeting> findbyId(String id);

    Greeting save(Greeting greeting);

    void delete(String id);
}
