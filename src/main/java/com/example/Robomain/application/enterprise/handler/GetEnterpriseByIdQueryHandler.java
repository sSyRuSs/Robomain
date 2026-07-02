package com.example.Robomain.application.enterprise.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.enterprise.dto.EnterpriseDto;
import com.example.Robomain.application.enterprise.mapper.EnterpriseDtoMapper;
import com.example.Robomain.application.enterprise.query.GetEnterpriseByIdQuery;
import com.example.Robomain.domain.enterprise.repository.IEnterpriseRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetEnterpriseByIdQueryHandler {

    private final IEnterpriseRepository enterpriseRepository;
    private final EnterpriseDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public EnterpriseDto handle(GetEnterpriseByIdQuery query) {
        return enterpriseRepository.findById(query.getEnterpriseId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise", query.getEnterpriseId()));
    }
}

