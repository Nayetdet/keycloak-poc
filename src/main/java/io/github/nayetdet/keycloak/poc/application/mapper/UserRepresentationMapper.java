package io.github.nayetdet.keycloak.poc.application.mapper;

import io.github.nayetdet.keycloak.poc.application.payload.request.UserSignUpRequest;
import io.github.nayetdet.keycloak.poc.application.payload.request.UserUpdateRequest;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRepresentationMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "emailVerified", constant = "false")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    UserRepresentation toRepresentation(UserSignUpRequest request);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "username", source = "username")
    UserRepresentation toRepresentation(UserUpdateRequest request);

    @AfterMapping
    default void afterMapping(UserSignUpRequest request, @MappingTarget UserRepresentation userRepresentation) {
        userRepresentation.setCredentials(getCredentials(request.getPassword()));
    }

    private List<CredentialRepresentation> getCredentials(String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);
        return Collections.singletonList(credential);
    }

}
