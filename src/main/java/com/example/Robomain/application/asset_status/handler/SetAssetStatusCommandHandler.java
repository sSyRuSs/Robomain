package com.example.Robomain.application.asset_status.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.asset_status.command.SetAssetStatusCommand;
import com.example.Robomain.application.asset_status.mapper.AssetStatusDtoMapper;
import com.example.Robomain.domain.asset.repository.IAssetRepository;
import com.example.Robomain.domain.asset_status.model.AssetStatus;
import com.example.Robomain.domain.asset_status.repository.IAssetStatusRepository;
import com.example.Robomain.domain.asset_status_history.model.AssetStatusHistory;
import com.example.Robomain.domain.asset_status_history.repository.IAssetStatusHistoryRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SetAssetStatusCommandHandler {

    private final IAssetStatusRepository statusRepository;
    private final IAssetStatusHistoryRepository historyRepository;
    private final IAssetRepository assetRepository;
    private final AssetStatusDtoMapper dtoMapper;

    /**
     * Creates or updates the current status for an asset and appends a history record.
     * Why: status changes must always be audited; history is append-only.
     */
    @Transactional
    public UUID handle(SetAssetStatusCommand command) {
        // Guard: asset must exist
        assetRepository.findById(command.getAssetId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset", command.getAssetId()));

        AssetStatus status = statusRepository.findByAssetId(command.getAssetId())
                .map(existing -> {
                    existing.update(command.getStatus(), command.getReason(), command.getFromDate());
                    return existing;
                })
                .orElseGet(() -> AssetStatus.create(
                        command.getAssetId(), command.getStatus(),
                        command.getReason(), command.getFromDate()));

        AssetStatus saved = statusRepository.save(status);

        // Always record the change in history
        historyRepository.save(AssetStatusHistory.record(
                saved.getAssetId(), saved.getId(), saved.getStatus(), saved.getReason()));

        return saved.getId();
    }
}
