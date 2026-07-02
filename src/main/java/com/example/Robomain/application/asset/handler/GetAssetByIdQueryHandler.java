package com.example.Robomain.application.asset.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.asset.dto.AssetDto;
import com.example.Robomain.application.asset.mapper.AssetDtoMapper;
import com.example.Robomain.application.asset.query.GetAssetByIdQuery;
import com.example.Robomain.domain.asset.repository.IAssetRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetAssetByIdQueryHandler {

    private final IAssetRepository assetRepository;
    private final AssetDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public AssetDto handle(GetAssetByIdQuery query) {
        return assetRepository.findById(query.getAssetId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Asset", query.getAssetId()));
    }
}

