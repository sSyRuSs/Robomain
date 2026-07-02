package com.example.Robomain.application.asset_category.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.asset_category.command.UpdateAssetCategoryCommand;
import com.example.Robomain.application.asset_category.mapper.AssetCategoryDtoMapper;
import com.example.Robomain.domain.asset_category.repository.IAssetCategoryRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateAssetCategoryCommandHandler {

    private final IAssetCategoryRepository categoryRepository;
    private final AssetCategoryDtoMapper dtoMapper;

    @Transactional
    public UUID handle(UpdateAssetCategoryCommand command) {
        var category = categoryRepository.findById(command.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("AssetCategory", command.getCategoryId()));
        category.update(command.getAssetCatName(), command.getAssetCatDescription());
        return categoryRepository.save(category).getId();
    }
}
