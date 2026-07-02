package com.example.Robomain.application.purchase_request.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.purchase_request.dto.PurchaseRequestDto;
import com.example.Robomain.application.purchase_request.mapper.PurchaseRequestDtoMapper;
import com.example.Robomain.application.purchase_request.query.GetPurchaseRequestByIdQuery;
import com.example.Robomain.domain.purchase_request.repository.IPurchaseRequestRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetPurchaseRequestByIdQueryHandler {

    private final IPurchaseRequestRepository prRepository;
    private final PurchaseRequestDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PurchaseRequestDto handle(GetPurchaseRequestByIdQuery query) {
        return prRepository.findById(query.getRequestId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseRequest", query.getRequestId()));
    }
}

