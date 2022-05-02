package com.aweperi.springbootpractice.repository;

import com.aweperi.springbootpractice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(String role);
}
