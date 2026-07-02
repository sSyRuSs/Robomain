package com.example.Robomain.application.facility.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.facility.dto.FacilityDto;
import com.example.Robomain.application.facility.mapper.FacilityDtoMapper;
import com.example.Robomain.application.facility.query.GetFacilityByIdQuery;
import com.example.Robomain.domain.facility.repository.IFacilityRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetFacilityByIdQueryHandler {

    private final IFacilityRepository facilityRepository;
    private final FacilityDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public FacilityDto handle(GetFacilityByIdQuery query) {
        return facilityRepository.findById(query.getFacilityId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Facility", query.getFacilityId()));
    }
}

