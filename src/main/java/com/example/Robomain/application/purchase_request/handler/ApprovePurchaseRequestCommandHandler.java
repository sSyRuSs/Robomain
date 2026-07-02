package com.example.Robomain.application.purchase_request.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.purchase_request.command.ApprovePurchaseRequestCommand;
import com.example.Robomain.application.purchase_request.mapper.PurchaseRequestDtoMapper;
import com.example.Robomain.domain.purchase_request.event.PurchaseRequestApprovedEvent;
import com.example.Robomain.domain.purchase_request.repository.IPurchaseRequestRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApprovePurchaseRequestCommandHandler {

    private final IPurchaseRequestRepository prRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final PurchaseRequestDtoMapper dtoMapper;

    @Transactional
    public UUID handle(ApprovePurchaseRequestCommand cmd) {
        var pr = prRepository.findById(cmd.getRequestId())
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseRequest", cmd.getRequestId()));
        // Domain method enforces PENDING guard
        pr.approve(cmd.getApprovedBy(), cmd.getApprovalNotes());
        UUID savedId = prRepository.save(pr).getId();
        eventPublisher.publishEvent(new PurchaseRequestApprovedEvent(
                pr.getId(), pr.getRequestCode(), pr.getInventoryId(),
                pr.getRequestedQuantity(), pr.getVendorId()));
        return savedId;
    }
}
