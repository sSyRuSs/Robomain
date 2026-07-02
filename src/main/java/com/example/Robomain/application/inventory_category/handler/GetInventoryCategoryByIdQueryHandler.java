package com.example.Robomain.application.inventory_category.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.inventory_category.dto.InventoryCategoryDto;
import com.example.Robomain.application.inventory_category.mapper.InventoryCategoryDtoMapper;
import com.example.Robomain.application.inventory_category.query.GetInventoryCategoryByIdQuery;
import com.example.Robomain.domain.inventory_category.repository.IInventoryCategoryRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetInventoryCategoryByIdQueryHandler {

    private final IInventoryCategoryRepository categoryRepository;
    private final InventoryCategoryDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public InventoryCategoryDto handle(GetInventoryCategoryByIdQuery query) {
        return categoryRepository.findById(query.getCategoryId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("InventoryCategory", query.getCategoryId()));
    }
}

