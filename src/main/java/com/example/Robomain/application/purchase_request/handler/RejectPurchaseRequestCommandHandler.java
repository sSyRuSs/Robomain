package com.example.Robomain.application.purchase_request.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.purchase_request.command.RejectPurchaseRequestCommand;
import com.example.Robomain.application.purchase_request.mapper.PurchaseRequestDtoMapper;
import com.example.Robomain.domain.purchase_request.repository.IPurchaseRequestRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RejectPurchaseRequestCommandHandler {

    private final IPurchaseRequestRepository prRepository;
    private final PurchaseRequestDtoMapper dtoMapper;

    @Transactional
    public UUID handle(RejectPurchaseRequestCommand cmd) {
        var pr = prRepository.findById(cmd.getRequestId())
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseRequest", cmd.getRequestId()));
        // Domain method enforces PENDING guard
        pr.reject(cmd.getReason());
        return prRepository.save(pr).getId();
    }
}
