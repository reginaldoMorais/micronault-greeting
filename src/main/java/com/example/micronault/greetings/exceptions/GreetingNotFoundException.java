package com.example.micronault.greetings.exceptions;

public class GreetingNotFoundException extends RuntimeException {

    public GreetingNotFoundException() {
        super("Greeting not found");
    }

    public GreetingNotFoundException(final Throwable cause) {
        super(cause);
    }

    public GreetingNotFoundException(final String message) {
        super(message);
    }

    public GreetingNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
