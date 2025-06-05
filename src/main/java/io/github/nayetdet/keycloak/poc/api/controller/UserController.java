package io.github.nayetdet.keycloak.poc.api.controller;

import io.github.nayetdet.keycloak.poc.api.controller.docs.UserControllerDocs;
import io.github.nayetdet.keycloak.poc.application.payload.request.UserSignUpRequest;
import io.github.nayetdet.keycloak.poc.application.payload.request.UserUpdateRequest;
import io.github.nayetdet.keycloak.poc.application.payload.response.UserResponse;
import io.github.nayetdet.keycloak.poc.application.security.annotation.UserRoleRequired;
import io.github.nayetdet.keycloak.poc.application.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "Endpoints for managing users")
public class UserController implements UserControllerDocs {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> find(@PathVariable String username) {
        return userService
                .find(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody @Valid UserSignUpRequest request) {
        var response = userService.signUp(request);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(response.getUsername())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/{username}/resend-verify-email")
    public ResponseEntity<Void> resendVerifyEmail(@PathVariable String username) {
        userService.resendVerifyEmail(username);
        return ResponseEntity.noContent().build();
    }

    @UserRoleRequired
    @PostMapping("/{username}/reset-email")
    public ResponseEntity<Void> resetEmail(@PathVariable String username) {
        userService.resetEmail(username);
        return ResponseEntity.noContent().build();
    }

    @UserRoleRequired
    @PostMapping("/{username}/reset-password")
    public ResponseEntity<Void> resetPassword(@PathVariable String username) {
        userService.resetPassword(username);
        return ResponseEntity.noContent().build();
    }

    @UserRoleRequired
    @PutMapping("/{username}")
    public ResponseEntity<Void> update(@PathVariable String username, @RequestBody @Valid UserUpdateRequest request) {
        userService.update(username, request);
        return ResponseEntity.noContent().build();
    }

//    @UserRoleRequired
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        userService.delete(username);
        return ResponseEntity.noContent().build();
    }

}
