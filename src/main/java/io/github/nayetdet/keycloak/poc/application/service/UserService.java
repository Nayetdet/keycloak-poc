package io.github.nayetdet.keycloak.poc.application.service;

import io.github.nayetdet.keycloak.poc.application.mapper.UserMapper;
import io.github.nayetdet.keycloak.poc.application.payload.request.UserSignUpRequest;
import io.github.nayetdet.keycloak.poc.application.payload.request.UserUpdateRequest;
import io.github.nayetdet.keycloak.poc.application.payload.response.UserResponse;
import io.github.nayetdet.keycloak.poc.infrastructure.domain.exception.UserNotFoundException;
import io.github.nayetdet.keycloak.poc.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final KeycloakService keycloakService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<UserResponse> find(String username) {
        return userRepository.findByUsername(username).map(userMapper::toResponse);
    }

    @Transactional
    public UserResponse signUp(UserSignUpRequest request) {
        var keycloakId = UUID.fromString(keycloakService.signUp(request).getId());
        try {
            var user = userMapper.toEntity(keycloakId, request);
            return userMapper.toResponse(userRepository.save(user));
        } catch (Exception e) {
            keycloakService.delete(keycloakId);
            throw e;
        }
    }

    public void resendVerifyEmail(String username) {
        var user = userRepository
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        keycloakService.resendVerifyEmail(user.getKeycloakId());
    }

    public void resetEmail(String username) {
        var user = userRepository
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        keycloakService.resetEmail(user.getKeycloakId());
    }

    @Transactional
    public void update(String username, UserUpdateRequest request) {
        var user = userRepository
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        userMapper.updateModel(request, user);
        userRepository.save(user);
        keycloakService.update(user.getKeycloakId(), request);
    }

    @Transactional
    public void delete(String username) {
        var user = userRepository
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
        keycloakService.delete(user.getKeycloakId());
    }

}
