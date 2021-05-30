package com.example.hybridbooksbackend.repository;

import com.example.hybridbooksbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
