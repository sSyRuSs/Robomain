package com.example.Robomain.application.stock_movement.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.stock_movement.dto.StockMovementDto;
import com.example.Robomain.application.stock_movement.mapper.StockMovementDtoMapper;
import com.example.Robomain.application.stock_movement.query.GetStockMovementByIdQuery;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;
import com.example.Robomain.domain.stock_movement.repository.IStockMovementRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetStockMovementByIdQueryHandler {

    private final IStockMovementRepository movementRepository;
    private final StockMovementDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public StockMovementDto handle(GetStockMovementByIdQuery query) {
        return movementRepository.findById(query.getMovementId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("StockMovement", query.getMovementId()));
    }
}

