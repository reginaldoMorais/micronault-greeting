package com.example.micronault.greetings.services;

import com.example.micronault.greetings.representations.GreetingRequest;
import com.example.micronault.greetings.representations.GreetingResponse;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.List;

public interface GreetingService {

    Page<GreetingResponse> list(Pageable pageable);

    GreetingResponse get(String id);

    GreetingResponse create(GreetingRequest greetingRequest);

    void delete(String id);
}
