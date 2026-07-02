package com.example.Robomain.application.stock_movement.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.stock_movement.dto.StockMovementDto;
import com.example.Robomain.application.stock_movement.mapper.StockMovementDtoMapper;
import com.example.Robomain.application.stock_movement.query.ListStockMovementsQuery;
import com.example.Robomain.domain.stock_movement.repository.IStockMovementRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListStockMovementsQueryHandler {

    private final IStockMovementRepository movementRepository;
    private final StockMovementDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<StockMovementDto> handle(ListStockMovementsQuery query) {
        var list = movementRepository.findByInventoryId(
                        query.getInventoryId(), query.getType(), query.getPage(), query.getSize())
                .stream().map(dtoMapper::toDto).toList();
        long total = movementRepository.countByInventoryId(query.getInventoryId(), query.getType());
        return PaginationResponse.of(list, total, query.getPage(), query.getSize());
    }
}

