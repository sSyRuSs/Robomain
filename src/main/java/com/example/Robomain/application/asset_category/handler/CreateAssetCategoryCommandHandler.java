package com.example.Robomain.application.asset_category.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.asset_category.command.CreateAssetCategoryCommand;
import com.example.Robomain.application.asset_category.mapper.AssetCategoryDtoMapper;
import com.example.Robomain.domain.asset_category.model.AssetCategory;
import com.example.Robomain.domain.asset_category.repository.IAssetCategoryRepository;
import com.example.Robomain.domain.shared.exception.ConflictException;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateAssetCategoryCommandHandler {

    private final IAssetCategoryRepository categoryRepository;
    private final AssetCategoryDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateAssetCategoryCommand command) {
        if (categoryRepository.existsByAssetCatName(command.getAssetCatName())) {
            throw new ConflictException("AssetCategory", "name", command.getAssetCatName());
        }

        AssetCategory category = AssetCategory.create(command.getAssetCatName(), command.getAssetCatDescription());

        // Validate and attach parent if provided
        if (command.getParentCategoryId() != null) {
            categoryRepository.findById(command.getParentCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("AssetCategory", command.getParentCategoryId()));
            category.setParent(command.getParentCategoryId());
        }

        return categoryRepository.save(category).getId();
    }
}
