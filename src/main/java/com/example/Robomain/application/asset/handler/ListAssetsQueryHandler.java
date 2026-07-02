package com.example.Robomain.application.asset.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.asset.dto.AssetDto;
import com.example.Robomain.application.asset.mapper.AssetDtoMapper;
import com.example.Robomain.application.asset.query.ListAssetsQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.asset.repository.IAssetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListAssetsQueryHandler {

    private final IAssetRepository assetRepository;
    private final AssetDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<AssetDto> handle(ListAssetsQuery query) {
        var list = assetRepository
                .search(query.getSearch(), query.getFacilityId(), query.getCategoryId(),
                        query.getPage(), query.getSize())
                .stream().map(dtoMapper::toDto).toList();
        long total = assetRepository.count(query.getSearch(), query.getFacilityId(), query.getCategoryId());
        return PaginationResponse.of(list, total, query.getPage(), query.getSize());
    }
}

