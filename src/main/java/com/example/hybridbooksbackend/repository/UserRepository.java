package com.example.hybridbooksbackend.repository;

import com.example.hybridbooksbackend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByFirstName(String firstName);
    Optional<UserEntity> findByLastName(String lastName);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByRole(String role);
}
