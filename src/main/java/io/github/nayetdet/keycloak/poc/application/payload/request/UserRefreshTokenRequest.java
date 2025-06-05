package io.github.nayetdet.keycloak.poc.application.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRefreshTokenRequest {

    @NotBlank
    private String refreshToken;

}
