package io.github.nayetdet.keycloak.poc.infrastructure.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyVerifiedException extends RuntimeException {

    private static final String DEFAULT_ERROR_MESSAGE = "User is already verified";

    public UserAlreadyVerifiedException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

}
