package com.example.Robomain.application.asset.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.asset.command.CreateAssetCommand;
import com.example.Robomain.application.asset.mapper.AssetDtoMapper;
import com.example.Robomain.domain.asset.event.AssetCreatedEvent;
import com.example.Robomain.domain.asset.model.Asset;
import com.example.Robomain.domain.asset.repository.IAssetRepository;
import com.example.Robomain.domain.shared.exception.ConflictException;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateAssetCommandHandler {

    private final IAssetRepository assetRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final AssetDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateAssetCommand command) {
        if (command.getAssetCode() != null
                && assetRepository.existsByAssetCode(command.getAssetCode())) {
            throw new ConflictException("Asset", "code", command.getAssetCode());
        }

        Asset asset = Asset.create(
                command.getAssetName(), command.getAssetCode(),
                command.getFacilityId(), command.getAssetCategoryId());

        if (command.getAssetDescription() != null) {
            asset.update(null, command.getAssetDescription(), null, command.getAssetQuantity());
        } else if (command.getAssetQuantity() != null) {
            asset.update(null, null, null, command.getAssetQuantity());
        }

        if (command.getParentAssetId() != null) {
            asset.setParent(command.getParentAssetId());
        }

        Asset saved = assetRepository.save(asset);
        eventPublisher.publishEvent(new AssetCreatedEvent(saved.getId(), saved.getAssetName(), saved.getFacilityId()));
        return saved.getId();
    }
}
