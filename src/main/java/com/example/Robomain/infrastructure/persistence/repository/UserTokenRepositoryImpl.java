package com.example.Robomain.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.domain.auth.model.UserToken;
import com.example.Robomain.domain.auth.repository.IUserTokenRepository;
import com.example.Robomain.infrastructure.persistence.jpa.UserTokenJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.UserTokenMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserTokenRepositoryImpl implements IUserTokenRepository {

    private final UserTokenJpaRepository jpaRepository;
    private final UserTokenMapper mapper;

    @Override
    public Optional<UserToken> findByAccessToken(String accessToken) {
        return jpaRepository.findByAccessToken(accessToken).map(mapper::toDomain);
    }

    @Override
    public Optional<UserToken> findByRefreshToken(String refreshToken) {
        return jpaRepository.findByRefreshToken(refreshToken).map(mapper::toDomain);
    }

    @Override
    public UserToken save(UserToken token) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(token)));
    }

    @Override
    public void delete(UserToken token) {
        jpaRepository.findByAccessToken(token.getAccessToken())
                .ifPresent(jpaRepository::delete);
    }

    @Override
    @Transactional
    public void deleteByUserId(UUID userId) {
        jpaRepository.deleteByUserId(userId);
    }
}
