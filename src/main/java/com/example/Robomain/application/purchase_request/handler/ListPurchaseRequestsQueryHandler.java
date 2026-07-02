package com.example.Robomain.application.purchase_request.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.purchase_request.dto.PurchaseRequestDto;
import com.example.Robomain.application.purchase_request.mapper.PurchaseRequestDtoMapper;
import com.example.Robomain.application.purchase_request.query.ListPurchaseRequestsQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.purchase_request.repository.IPurchaseRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListPurchaseRequestsQueryHandler {

    private final IPurchaseRequestRepository prRepository;
    private final PurchaseRequestDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<PurchaseRequestDto> handle(ListPurchaseRequestsQuery query) {
        var items = prRepository.search(query.getKeyword(), query.getStatus(), query.getType(),
                query.getEnterpriseId(), query.getInventoryId(), query.getPage(), query.getSize());
        var total = prRepository.count(query.getKeyword(), query.getStatus(), query.getType(),
                query.getEnterpriseId(), query.getInventoryId());
        var dtos = items.stream().map(dtoMapper::toDto).toList();
        return PaginationResponse.of(dtos, total, query.getPage(), query.getSize());
    }
}

