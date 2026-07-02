package com.example.Robomain.application.asset.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.asset.command.UpdateAssetCommand;
import com.example.Robomain.application.asset.mapper.AssetDtoMapper;
import com.example.Robomain.domain.asset.repository.IAssetRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateAssetCommandHandler {

    private final IAssetRepository assetRepository;
    private final AssetDtoMapper dtoMapper;

    @Transactional
    public UUID handle(UpdateAssetCommand command) {
        var asset = assetRepository.findById(command.getAssetId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset", command.getAssetId()));
        asset.update(command.getAssetName(), command.getAssetDescription(),
                command.getAssetCode(), command.getAssetQuantity());
        return assetRepository.save(asset).getId();
    }
}
