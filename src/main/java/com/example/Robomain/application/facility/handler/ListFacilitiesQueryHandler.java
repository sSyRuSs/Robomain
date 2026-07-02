package com.example.Robomain.application.facility.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.facility.dto.FacilityDto;
import com.example.Robomain.application.facility.mapper.FacilityDtoMapper;
import com.example.Robomain.application.facility.query.ListFacilitiesQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.facility.repository.IFacilityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListFacilitiesQueryHandler {

    private final IFacilityRepository facilityRepository;
    private final FacilityDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<FacilityDto> handle(ListFacilitiesQuery query) {
        var list = facilityRepository
                .search(query.getSearch(), query.getEnterpriseId(), query.getParentFacilityId(),
                        query.getPage(), query.getSize())
                .stream().map(dtoMapper::toDto).toList();
        long total = facilityRepository.count(query.getSearch(), query.getEnterpriseId());
        return PaginationResponse.of(list, total, query.getPage(), query.getSize());
    }
}

