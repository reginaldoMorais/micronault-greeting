package com.example.micronault.greetings.repositories;

import com.example.micronault.greetings.models.Greeting;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.List;
import java.util.Optional;

public interface GreetingRepository {

    Page<Greeting> findAll(Pageable pageable);

    Optional<Greeting> findbyId(String id);

    Greeting save(Greeting greeting);

    void delete(String id);
}
