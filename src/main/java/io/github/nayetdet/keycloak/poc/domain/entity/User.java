package io.github.nayetdet.keycloak.poc.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String keycloakId;

    @Column(length = 50, unique = true, nullable = false)
    private String username;

    @Column(length = 50)
    private String displayName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 1000)
    private String description;

}
