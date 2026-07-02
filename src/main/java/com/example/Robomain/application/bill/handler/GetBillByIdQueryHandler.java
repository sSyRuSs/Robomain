package com.example.Robomain.application.bill.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.bill.dto.BillDto;
import com.example.Robomain.application.bill.mapper.BillDtoMapper;
import com.example.Robomain.application.bill.query.GetBillByIdQuery;
import com.example.Robomain.domain.bill.repository.IBillRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetBillByIdQueryHandler {

    private final IBillRepository billRepository;
    private final BillDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public BillDto handle(GetBillByIdQuery query) {
        return billRepository.findById(query.getBillId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Bill", query.getBillId()));
    }
}

