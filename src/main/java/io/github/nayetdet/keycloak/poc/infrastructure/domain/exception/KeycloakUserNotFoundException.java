package io.github.nayetdet.keycloak.poc.infrastructure.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class KeycloakUserNotFoundException extends RuntimeException {

    private static final String DEFAULT_ERROR_MESSAGE = "Keycloak User not found";

    public KeycloakUserNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

    public KeycloakUserNotFoundException(String message) {
        super(message);
    }

}
