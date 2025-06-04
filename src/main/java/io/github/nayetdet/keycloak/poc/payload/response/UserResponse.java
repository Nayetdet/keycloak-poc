package io.github.nayetdet.keycloak.poc.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String keycloakId;
    private String username;
    private String displayName;
    private String email;
    private String description;

}
