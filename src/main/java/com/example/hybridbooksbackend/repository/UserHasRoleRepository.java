package com.example.hybridbooksbackend.repository;

import com.example.hybridbooksbackend.model.UserHasRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserHasRoleRepository extends JpaRepository<UserHasRole, Long> {

    Optional<UserHasRole> findByUser(Long id);
    Optional<UserHasRole> findByRole(Long id);
}
