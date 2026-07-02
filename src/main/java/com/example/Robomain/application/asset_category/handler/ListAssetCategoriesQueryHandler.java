package com.example.Robomain.application.asset_category.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.asset_category.dto.AssetCategoryDto;
import com.example.Robomain.application.asset_category.mapper.AssetCategoryDtoMapper;
import com.example.Robomain.application.asset_category.query.ListAssetCategoriesQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.asset_category.repository.IAssetCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListAssetCategoriesQueryHandler {

    private final IAssetCategoryRepository categoryRepository;
    private final AssetCategoryDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<AssetCategoryDto> handle(ListAssetCategoriesQuery query) {
        var list = categoryRepository.search(query.getSearch(), query.getParentId(), query.getPage(), query.getSize())
                .stream().map(dtoMapper::toDto).toList();
        long total = categoryRepository.count(query.getSearch());
        return PaginationResponse.of(list, total, query.getPage(), query.getSize());
    }
}

