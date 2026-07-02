package com.example.Robomain.application.facility.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.facility.command.UpdateFacilityCommand;
import com.example.Robomain.application.facility.mapper.FacilityDtoMapper;
import com.example.Robomain.domain.facility.repository.IFacilityRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateFacilityCommandHandler {

    private final IFacilityRepository facilityRepository;
    private final FacilityDtoMapper dtoMapper;

    @Transactional
    public UUID handle(UpdateFacilityCommand command) {
        var facility = facilityRepository.findById(command.getFacilityId())
                .orElseThrow(() -> new ResourceNotFoundException("Facility", command.getFacilityId()));
        facility.update(command.getFacilityName(), command.getFacilityAddress(), command.getFacilityPhone());
        return facilityRepository.save(facility).getId();
    }
}
