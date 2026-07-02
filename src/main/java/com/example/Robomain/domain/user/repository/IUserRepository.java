package com.example.Robomain.domain.user.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.user.model.User;

/**
 * Domain interface for User persistence.
 * Implementation in infrastructure/persistence/repository.
 */
public interface IUserRepository {

    Optional<User> findById(UUID id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findAll(int page, int size);

    long count();

    User save(User user);

    void deleteById(UUID id);
}
