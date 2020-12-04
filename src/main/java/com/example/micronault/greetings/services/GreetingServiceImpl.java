package com.example.micronault.greetings.services;

import com.example.micronault.greetings.exceptions.GreetingNotFoundException;
import com.example.micronault.greetings.models.Greeting;
import com.example.micronault.greetings.repositories.GreetingRepository;
import com.example.micronault.greetings.representations.GreetingRequest;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;

@Singleton
public class GreetingServiceImpl implements GreetingService {

    @Inject
    private GreetingRepository greetingRepository;

    public List<Greeting> list() {
        return greetingRepository.findAll();
    }

    @Override
    public Greeting get(final String id) {
        return greetingRepository.findbyId(id).orElseThrow(GreetingNotFoundException::new);
    }

    @Override
    public Greeting create(GreetingRequest greetingRequest) {
        Greeting greeting = Greeting.builder()
            .withId(UUID.randomUUID().toString())
            .withGreeting(greetingRequest.getGreeting())
            .build();
        return greetingRepository.save(greeting);
    }

    @Override
    public void delete(final String id) {
        greetingRepository.delete(id);
    }
}
