package com.example.Robomain.application.enterprise.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.enterprise.dto.EnterpriseDto;
import com.example.Robomain.application.enterprise.mapper.EnterpriseDtoMapper;
import com.example.Robomain.application.enterprise.query.ListEnterprisesQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.enterprise.repository.IEnterpriseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListEnterprisesQueryHandler {

    private final IEnterpriseRepository enterpriseRepository;
    private final EnterpriseDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<EnterpriseDto> handle(ListEnterprisesQuery query) {
        var list = enterpriseRepository.search(query.getSearch(), query.getPage(), query.getSize())
                .stream().map(dtoMapper::toDto).toList();
        long total = enterpriseRepository.count(query.getSearch());
        return PaginationResponse.of(list, total, query.getPage(), query.getSize());
    }
}

