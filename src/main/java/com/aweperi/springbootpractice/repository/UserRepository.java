package com.aweperi.springbootpractice.repository;

import com.aweperi.springbootpractice.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Long, User> {

    @Query("SELECT u from User u where u.email = ?1")
    Optional<User> findByEmail(String email);
}
