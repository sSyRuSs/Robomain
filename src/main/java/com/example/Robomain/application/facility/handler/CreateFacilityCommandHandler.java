package com.example.Robomain.application.facility.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.facility.command.CreateFacilityCommand;
import com.example.Robomain.application.facility.mapper.FacilityDtoMapper;
import com.example.Robomain.domain.enterprise.repository.IEnterpriseRepository;
import com.example.Robomain.domain.facility.event.FacilityCreatedEvent;
import com.example.Robomain.domain.facility.model.Facility;
import com.example.Robomain.domain.facility.repository.IFacilityRepository;
import com.example.Robomain.domain.shared.exception.ConflictException;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateFacilityCommandHandler {

    private final IFacilityRepository facilityRepository;
    private final IEnterpriseRepository enterpriseRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final FacilityDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateFacilityCommand command) {
        // Validate enterprise exists
        var enterprise = enterpriseRepository.findById(command.getEnterpriseId())
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise", command.getEnterpriseId()));

        if (command.getFacilityCode() != null
                && facilityRepository.existsByFacilityCode(command.getFacilityCode())) {
            throw new ConflictException("Facility", "code", command.getFacilityCode());
        }

        Facility facility = Facility.create(
                command.getFacilityName(), command.getFacilityCode(), command.getEnterpriseId());

        if (command.getParentFacilityId() != null) facility.setParent(command.getParentFacilityId());
        if (command.getFacilityAddress() != null || command.getFacilityPhone() != null) {
            facility.update(null, command.getFacilityAddress(), command.getFacilityPhone());
        }

        Facility saved = facilityRepository.save(facility);

        // Update enterprise facility counter
        enterprise.incrementFacilityTotal();
        enterpriseRepository.save(enterprise);

        eventPublisher.publishEvent(
                new FacilityCreatedEvent(saved.getId(), saved.getEnterpriseId(), saved.getFacilityName()));
        return saved.getId();
    }
}
