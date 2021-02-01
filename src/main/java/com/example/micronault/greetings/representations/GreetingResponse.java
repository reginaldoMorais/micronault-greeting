package com.example.micronault.greetings.representations;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

import javax.validation.constraints.NotNull;

@Schema(name = "GreetingResponse", description = "Greeting payload")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Introspected
public class GreetingResponse {

    @Schema(description = "Indentification of greeting")
    private UUID id;

    @Schema(description = "Value of greeting")
    private String greeting;

    @Schema(description = "Created date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Instant createdAt;
}
