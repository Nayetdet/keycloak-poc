package io.github.nayetdet.keycloak.poc.application.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private UUID keycloakId;
    private String username;
    private String displayName;
    private String description;

}
