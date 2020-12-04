package com.example.micronault.greetings.representations;

import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Schema(name = "HelloRequest", description = "Greeting payload")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Introspected
public class GreetingRequest {

    @Schema(description = "Value of greeting", minLength = 2, required = true)
    @NotNull
    private String greeting;
}
