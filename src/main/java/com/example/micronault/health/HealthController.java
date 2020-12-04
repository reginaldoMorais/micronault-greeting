package com.example.micronault.health;

import com.example.micronault.health.representations.HealthResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("/health")
public class HealthController {

    @Operation(summary = "Health check", description = "Health check")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Ok",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                schema = @Schema(implementation = HealthResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Greeting not found")
    })
    @Tag(name = "health check")
    @Get
    public HttpResponse<HealthResponse> get() {

        return HttpResponse.ok(new HealthResponse("OK"));
    }
}
