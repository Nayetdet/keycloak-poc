package io.github.nayetdet.keycloak.poc.application.service;

import io.github.nayetdet.keycloak.poc.application.mapper.UserRepresentationMapper;
import io.github.nayetdet.keycloak.poc.application.payload.request.UserSignInRequest;
import io.github.nayetdet.keycloak.poc.application.payload.request.UserSignUpRequest;
import io.github.nayetdet.keycloak.poc.application.payload.request.UserUpdateRequest;
import io.github.nayetdet.keycloak.poc.application.security.context.provider.KeycloakProvider;
import io.github.nayetdet.keycloak.poc.infrastructure.domain.exception.KeycloakUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.AbstractUserRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@RequiredArgsConstructor
@Service
public class KeycloakService {

    private final KeycloakProvider keycloakProvider;
    private final UserRepresentationMapper userRepresentationMapper;

    public AccessTokenResponse signIn(UserSignInRequest request) {
        return keycloakProvider
                .getKeycloak(request.getUsername(), request.getPassword())
                .tokenManager()
                .getAccessToken();
    }

    public UserRepresentation signUp(UserSignUpRequest request) {
        var usersResource = keycloakProvider.getUsersResource();
        usersResource.create(userRepresentationMapper.toRepresentation(request));

        var userRepresentation = usersResource
                .searchByUsername(request.getUsername(), true)
                .stream()
                .filter(Predicate.not(AbstractUserRepresentation::isEmailVerified))
                .findFirst()
                .orElseThrow(KeycloakUserNotFoundException::new);

        usersResource.get(userRepresentation.getId()).sendVerifyEmail();
        return userRepresentation;
    }

    public void resetEmail(UUID keycloakId) {
        keycloakProvider
                .getUsersResource()
                .get(keycloakId.toString())
                .executeActionsEmail(List.of("UPDATE_EMAIL"));
    }

    public void resetPassword(UUID keycloakId) {
        keycloakProvider
                .getUsersResource()
                .get(keycloakId.toString())
                .executeActionsEmail(List.of("UPDATE_PASSWORD"));
    }

    public void update(UUID keycloakId, UserUpdateRequest request) {
        keycloakProvider
                .getUsersResource()
                .get(keycloakId.toString())
                .update(userRepresentationMapper.toRepresentation(request));
    }

    public void delete(UUID keycloakId) {
        keycloakProvider
                .getUsersResource()
                .get(keycloakId.toString())
                .remove();
    }

}
