package io.github.nayetdet.keycloak.poc.infrastructure.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class KeycloakException extends RuntimeException {

    public KeycloakException(String message) {
        super(message);
    }

}
