package com.example.Robomain.application.inventory.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.inventory.dto.InventoryDto;
import com.example.Robomain.application.inventory.mapper.InventoryDtoMapper;
import com.example.Robomain.application.inventory.query.GetInventoryByIdQuery;
import com.example.Robomain.domain.inventory.repository.IInventoryRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetInventoryByIdQueryHandler {

    private final IInventoryRepository inventoryRepository;
    private final InventoryDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public InventoryDto handle(GetInventoryByIdQuery query) {
        return inventoryRepository.findById(query.getInventoryId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory", query.getInventoryId()));
    }
}

