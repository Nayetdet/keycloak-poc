package io.github.nayetdet.keycloak.poc.service;

import io.github.nayetdet.keycloak.poc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

}
