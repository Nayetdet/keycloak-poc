package io.github.nayetdet.keycloak.poc.application.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
