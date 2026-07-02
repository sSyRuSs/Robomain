package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.response_issue.model.ResponseIssue;
import com.example.Robomain.domain.response_issue.repository.IResponseIssueRepository;
import com.example.Robomain.infrastructure.persistence.jpa.ResponseIssueJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.ResponseIssueMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ResponseIssueRepositoryImpl implements IResponseIssueRepository {

    private final ResponseIssueJpaRepository jpaRepository;
    private final ResponseIssueMapper mapper;

    @Override public Optional<ResponseIssue> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ResponseIssue> findByIssueId(UUID issueId, UUID parentId, int page, int size) {
        return jpaRepository.findByIssueId(issueId, parentId,
                        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt")))
                .stream().map(mapper::toDomain).toList();
    }

    @Override public long countByIssueId(UUID issueId, UUID parentId) {
        return jpaRepository.countByIssueId(issueId, parentId);
    }

    @Override public ResponseIssue save(ResponseIssue ri) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(ri)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
