package com.example.micronault.greetings.services;

import com.example.micronault.greetings.representations.GreetingResponse;
import com.example.micronault.greetings.representations.GreetingRequest;

import java.util.List;

public interface GreetingService {

    List<GreetingResponse> list();

    GreetingResponse get(String id);

    GreetingResponse create(GreetingRequest greetingRequest);

    void delete(String id);
}
