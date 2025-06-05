package io.github.nayetdet.keycloak.poc.infrastructure.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class KeycloakBadRequestException extends RuntimeException {

    private static final String DEFAULT_ERROR_MESSAGE = "The request was invalid or could not be processed by Keycloak";

    public KeycloakBadRequestException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

}
