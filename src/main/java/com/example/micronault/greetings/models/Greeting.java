package com.example.micronault.greetings.models;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.hateoas.Resource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Introspected
public class Greeting implements Resource {

    private String id;

    private String greeting;
}
