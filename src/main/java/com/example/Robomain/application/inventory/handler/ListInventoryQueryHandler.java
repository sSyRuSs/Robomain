package com.example.Robomain.application.inventory.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.inventory.dto.InventoryDto;
import com.example.Robomain.application.inventory.mapper.InventoryDtoMapper;
import com.example.Robomain.application.inventory.query.ListInventoryQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.inventory.repository.IInventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListInventoryQueryHandler {

    private final IInventoryRepository inventoryRepository;
    private final InventoryDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<InventoryDto> handle(ListInventoryQuery query) {
        var list = inventoryRepository.search(
                        query.getSearch(), query.getCategoryId(), query.getWarehouseId(),
                        query.getStatus(), query.getEnterpriseId(),
                        query.getPage(), query.getSize())
                .stream().map(dtoMapper::toDto).toList();
        long total = inventoryRepository.count(
                query.getSearch(), query.getCategoryId(), query.getWarehouseId(),
                query.getStatus(), query.getEnterpriseId());
        return PaginationResponse.of(list, total, query.getPage(), query.getSize());
    }
}

