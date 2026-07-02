package com.example.Robomain.application.asset_status.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.asset_status.dto.AssetStatusDto;
import com.example.Robomain.application.asset_status.mapper.AssetStatusDtoMapper;
import com.example.Robomain.application.asset_status.query.GetAssetStatusQuery;
import com.example.Robomain.domain.asset_status.repository.IAssetStatusRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetAssetStatusQueryHandler {

    private final IAssetStatusRepository statusRepository;
    private final AssetStatusDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public AssetStatusDto handle(GetAssetStatusQuery query) {
        return statusRepository.findByAssetId(query.getAssetId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("AssetStatus for asset", query.getAssetId()));
    }
}

