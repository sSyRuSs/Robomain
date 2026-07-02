package com.example.Robomain.application.inventory_category.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.inventory_category.dto.InventoryCategoryDto;
import com.example.Robomain.application.inventory_category.mapper.InventoryCategoryDtoMapper;
import com.example.Robomain.application.inventory_category.query.ListInventoryCategoriesQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.inventory_category.repository.IInventoryCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListInventoryCategoriesQueryHandler {

    private final IInventoryCategoryRepository categoryRepository;
    private final InventoryCategoryDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<InventoryCategoryDto> handle(ListInventoryCategoriesQuery query) {
        var list = categoryRepository.search(
                        query.getSearch(), query.getEnterpriseId(), query.getParentId(),
                        query.getPage(), query.getSize())
                .stream().map(dtoMapper::toDto).toList();
        long total = categoryRepository.count(query.getSearch(), query.getEnterpriseId());
        return PaginationResponse.of(list, total, query.getPage(), query.getSize());
    }
}

