package com.example.Robomain.application.inventory_category.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.inventory_category.command.UpdateInventoryCategoryCommand;
import com.example.Robomain.application.inventory_category.mapper.InventoryCategoryDtoMapper;
import com.example.Robomain.domain.inventory_category.repository.IInventoryCategoryRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateInventoryCategoryCommandHandler {

    private final IInventoryCategoryRepository categoryRepository;
    private final InventoryCategoryDtoMapper dtoMapper;

    @Transactional
    public UUID handle(UpdateInventoryCategoryCommand command) {
        var category = categoryRepository.findById(command.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("InventoryCategory", command.getCategoryId()));
        category.update(command.getCategoryName(), command.getDescription());
        return categoryRepository.save(category).getId();
    }
}
