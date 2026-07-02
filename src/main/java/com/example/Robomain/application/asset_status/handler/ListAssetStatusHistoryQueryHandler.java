package com.example.Robomain.application.asset_status.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.asset_status.dto.AssetStatusHistoryDto;
import com.example.Robomain.application.asset_status.query.ListAssetStatusHistoryQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.asset_status_history.model.AssetStatusHistory;
import com.example.Robomain.domain.asset_status_history.repository.IAssetStatusHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListAssetStatusHistoryQueryHandler {

    private final IAssetStatusHistoryRepository historyRepository;

    @Transactional(readOnly = true)
    public PaginationResponse<AssetStatusHistoryDto> handle(ListAssetStatusHistoryQuery query) {
        var list = historyRepository.findByAssetId(query.getAssetId(), query.getPage(), query.getSize())
                .stream().map(this::toDto).toList();
        long total = historyRepository.countByAssetId(query.getAssetId());
        return PaginationResponse.of(list, total, query.getPage(), query.getSize());
    }

    private AssetStatusHistoryDto toDto(AssetStatusHistory h) {
        return AssetStatusHistoryDto.builder()
                .id(h.getId()).assetId(h.getAssetId()).assetStatusId(h.getAssetStatusId())
                .status(h.getStatus()).reason(h.getReason()).changedAt(h.getChangedAt())
                .build();
    }
}

