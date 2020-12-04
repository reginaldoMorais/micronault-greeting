package com.example.micronault.greetings;

import com.example.micronault.greetings.exceptions.GreetingNotFoundException;
import com.example.micronault.greetings.models.Greeting;
import com.example.micronault.greetings.representations.GreetingRequest;
import com.example.micronault.greetings.services.GreetingService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller("/greetings")
public class GreetingController {

    @Inject
    private GreetingService greetingService;

    @Operation(summary = "List of greeting", description = "Returns a list of greeting")
    @ApiResponse(
        responseCode = "200",
        description = "Ok",
        content = @Content(mediaType = MediaType.APPLICATION_JSON,
            array = @ArraySchema(schema = @Schema(implementation = Greeting.class))))
    @Tag(name = "greetings")
    @Get
    public HttpResponse<List<Greeting>> list() {

        log.info("Getting a list of Greetings");
        return HttpResponse.ok(greetingService.list());
    }

    @Operation(summary = "Search by id of greeting", description = "Returns a greeting")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Ok",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                schema = @Schema(implementation = Greeting.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Greeting not found")
    })
    @Tag(name = "greetings")
    @Get("/{id}")
    public HttpResponse<Greeting> get(
        @Parameter(description = "Identification of greeting", required = true) final String id) {

        log.info("Getting a Greeting id: [{}]", id);
        try {
            Greeting greeting = greetingService.get(id);
            return HttpResponse.ok(greeting);
        } catch (GreetingNotFoundException e) {
            return HttpResponse.notFound();
        }
    }

    @Operation(summary = "Create a new greeting", description = "Create a new greeting")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(
            responseCode = "201",
            description = "Greeting created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                schema = @Schema(implementation = Greeting.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Greeting not found")
    })
    @Tag(name = "greetings")
    @Post
    public HttpResponse<Greeting> post(
        @RequestBody(description = "Payload of Greeting", required = true)
        @Body @Valid final GreetingRequest request) {

        log.info("Creating a new Greeting: [{}]", request);
        Greeting greeting = greetingService.create(request);
        return HttpResponse.created(greeting);
    }

    @Operation(summary = "Delete a greeting", description = "Delete a greeting")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(responseCode = "204", description = "No content")
    })
    @Tag(name = "greetings")
    @Delete("/{id}")
    public HttpResponse<Void> delete(
        @Parameter(description = "Identification of greeting", required = true) final String id) {

        log.info("Deleting a Greeting id: [{}]", id);
        greetingService.delete(id);
        return HttpResponse.noContent();
    }
}
