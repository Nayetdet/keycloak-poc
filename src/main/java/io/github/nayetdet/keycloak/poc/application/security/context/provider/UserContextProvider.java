package io.github.nayetdet.keycloak.poc.application.security.context.provider;

import io.github.nayetdet.keycloak.poc.infrastructure.domain.exception.UserUnauthorizedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserContextProvider {

    private UserContextProvider() {
    }

    public static boolean isUserUnauthorized(UUID authorizedKeycloakId) {
        var jwt = getCurrentJwt();
        var keycloakId = UUID.fromString(jwt.getSubject());
        var isAdmin = isAdmin(jwt);
        return !authorizedKeycloakId.equals(keycloakId) && !isAdmin;
    }

    private static boolean isAdmin(Jwt jwt) {
        Map<String, List<String>> realmAccess = jwt.getClaim("realm_access");
        return realmAccess.get("roles").contains("admin");
    }

    private static Jwt getCurrentJwt() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserUnauthorizedException();
        }

        return (Jwt) authentication.getCredentials();
    }

}
