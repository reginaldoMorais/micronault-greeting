package com.example.micronault.greetings.services;

import com.example.micronault.greetings.models.Greeting;
import com.example.micronault.greetings.representations.GreetingRequest;

import java.util.List;

public interface GreetingService {

    List<Greeting> list();

    Greeting get(String id);

    Greeting create(GreetingRequest greetingRequest);

    void delete(String id);
}
