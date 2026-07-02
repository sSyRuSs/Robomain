package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.user.model.User;
import com.example.Robomain.domain.user.repository.IUserRepository;
import com.example.Robomain.infrastructure.persistence.jpa.UserJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

/**
 * Infrastructure implementation of IUserRepository.
 * Translates between domain model and JPA entity.
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements IUserRepository {

    private final UserJpaRepository jpaRepository;
    private final UserMapper mapper;

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public List<User> findAll(int page, int size) {
        return jpaRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public User save(User user) {
        var entity = mapper.toJpa(user);
        if (user.getId() != null) entity.setId(user.getId());
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
