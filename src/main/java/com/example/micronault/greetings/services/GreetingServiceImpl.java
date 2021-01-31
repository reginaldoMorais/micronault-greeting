package com.example.micronault.greetings.services;

import com.example.micronault.greetings.exceptions.GreetingNotFoundException;
import com.example.micronault.greetings.models.Greeting;
import com.example.micronault.greetings.repositories.GreetingRepository;
import com.example.micronault.greetings.representations.GreetingRequest;
import com.example.micronault.greetings.representations.GreetingResponse;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
public class GreetingServiceImpl implements GreetingService {

    @Inject
    private GreetingRepository greetingRepository;

    public List<GreetingResponse> list() {
        List<Greeting> greetings = greetingRepository.findAll();

        return greetings.stream()
            .map(greeting -> GreetingResponse.builder()
                .withId(UUID.fromString(greeting.getId()))
                .withGreeting(greeting.getGreeting())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public GreetingResponse get(final String id) {
        Greeting greeting = greetingRepository.findbyId(id).orElseThrow(GreetingNotFoundException::new);

        return GreetingResponse.builder()
            .withId(UUID.fromString(greeting.getId()))
            .withGreeting(greeting.getGreeting())
            .build();
    }

    @Override
    public GreetingResponse create(GreetingRequest greetingRequest) {
        Greeting greeting = Greeting.builder()
            .withId(UUID.randomUUID().toString())
            .withGreeting(greetingRequest.getGreeting())
            .build();

        greetingRepository.save(greeting);

        return GreetingResponse.builder()
            .withId(UUID.fromString(greeting.getId()))
            .withGreeting(greeting.getGreeting())
            .build();
    }

    @Override
    public void delete(final String id) {
        greetingRepository.delete(id);
    }
}
