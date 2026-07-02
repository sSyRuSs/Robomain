package com.example.Robomain.application.inventory_category.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.inventory_category.command.CreateInventoryCategoryCommand;
import com.example.Robomain.application.inventory_category.mapper.InventoryCategoryDtoMapper;
import com.example.Robomain.domain.inventory_category.model.InventoryCategory;
import com.example.Robomain.domain.inventory_category.repository.IInventoryCategoryRepository;
import com.example.Robomain.domain.shared.exception.ConflictException;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateInventoryCategoryCommandHandler {

    private final IInventoryCategoryRepository categoryRepository;
    private final InventoryCategoryDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateInventoryCategoryCommand command) {
        if (categoryRepository.existsByCategoryCode(command.getCategoryCode())) {
            throw new ConflictException("InventoryCategory", "code", command.getCategoryCode());
        }

        InventoryCategory category = InventoryCategory.create(
                command.getCategoryCode(), command.getCategoryName(),
                command.getDescription(), command.getEnterpriseId());

        if (command.getParentCategoryId() != null) {
            categoryRepository.findById(command.getParentCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("InventoryCategory", command.getParentCategoryId()));
            category.setParent(command.getParentCategoryId());
        }

        return categoryRepository.save(category).getId();
    }
}
