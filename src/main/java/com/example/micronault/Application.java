package com.example.micronault;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "micronault-greeting",
        version = "0.0.1",
        description = "",
        contact = @Contact(name = "Reginaldo Morais", email = "reginaldo.cmorais@gmail.com")
    )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
