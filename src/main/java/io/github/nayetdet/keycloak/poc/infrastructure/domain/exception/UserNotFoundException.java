package io.github.nayetdet.keycloak.poc.infrastructure.domain.exception;

public class UserNotFoundException extends RuntimeException {

    private static final String DEFAULT_ERROR_MESSAGE = "User not found";

    public UserNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
