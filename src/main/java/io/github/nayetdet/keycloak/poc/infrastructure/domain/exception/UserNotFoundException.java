package io.github.nayetdet.keycloak.poc.infrastructure.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private static final String DEFAULT_ERROR_MESSAGE = "User not found";

    public UserNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

}
