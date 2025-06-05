package io.github.nayetdet.keycloak.poc.infrastructure.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "keycloak_id", unique = true, nullable = false)
    private UUID keycloakId;

    @Column(length = 25, unique = true, nullable = false)
    private String username;

    @Column(name = "display_name", length = 50)
    private String displayName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 1000)
    private String description;

}
