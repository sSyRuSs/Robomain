package com.example.Robomain.application.asset_category.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.asset_category.dto.AssetCategoryDto;
import com.example.Robomain.application.asset_category.mapper.AssetCategoryDtoMapper;
import com.example.Robomain.application.asset_category.query.GetAssetCategoryByIdQuery;
import com.example.Robomain.domain.asset_category.repository.IAssetCategoryRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetAssetCategoryByIdQueryHandler {

    private final IAssetCategoryRepository categoryRepository;
    private final AssetCategoryDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public AssetCategoryDto handle(GetAssetCategoryByIdQuery query) {
        return categoryRepository.findById(query.getCategoryId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("AssetCategory", query.getCategoryId()));
    }
}

