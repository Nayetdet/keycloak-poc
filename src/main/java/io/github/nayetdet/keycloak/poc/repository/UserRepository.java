package io.github.nayetdet.keycloak.poc.repository;

import io.github.nayetdet.keycloak.poc.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
