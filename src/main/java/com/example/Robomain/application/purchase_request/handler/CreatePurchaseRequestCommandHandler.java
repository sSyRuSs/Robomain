package com.example.Robomain.application.purchase_request.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.purchase_request.command.CreatePurchaseRequestCommand;
import com.example.Robomain.application.purchase_request.mapper.PurchaseRequestDtoMapper;
import com.example.Robomain.domain.purchase_request.event.PurchaseRequestCreatedEvent;
import com.example.Robomain.domain.purchase_request.model.PurchaseRequest;
import com.example.Robomain.domain.purchase_request.repository.IPurchaseRequestRepository;
import com.example.Robomain.domain.shared.exception.ConflictException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreatePurchaseRequestCommandHandler {

    private final IPurchaseRequestRepository prRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final PurchaseRequestDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreatePurchaseRequestCommand cmd) {
        if (prRepository.existsByRequestCode(cmd.getRequestCode())) {
            throw new ConflictException("Request code already exists: " + cmd.getRequestCode());
        }
        PurchaseRequest pr = PurchaseRequest.create(cmd.getRequestCode(), cmd.getInventoryId(),
                cmd.getRequestedQuantity(), cmd.getEstimatedCost(), cmd.getRequestedBy(), cmd.getEnterpriseId());
        if (cmd.getReason() != null) pr = PurchaseRequest.builder()
                .id(pr.getId()).requestCode(pr.getRequestCode()).inventoryId(pr.getInventoryId())
                .requestedQuantity(pr.getRequestedQuantity()).estimatedCost(pr.getEstimatedCost())
                .status(pr.getStatus()).type(pr.getType()).reason(cmd.getReason())
                .requestedBy(pr.getRequestedBy()).requestedDate(pr.getRequestedDate())
                .requiredDate(cmd.getRequiredDate()).vendorId(cmd.getVendorId())
                .enterpriseId(pr.getEnterpriseId()).build();
        PurchaseRequest saved = prRepository.save(pr);
        eventPublisher.publishEvent(new PurchaseRequestCreatedEvent(
                saved.getId(), saved.getRequestCode(), saved.getInventoryId(),
                saved.getRequestedQuantity(), saved.getEnterpriseId()));
        return saved.getId();
    }
}
