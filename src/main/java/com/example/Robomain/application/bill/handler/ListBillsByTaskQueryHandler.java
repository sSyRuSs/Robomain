package com.example.Robomain.application.bill.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.bill.dto.BillDto;
import com.example.Robomain.application.bill.mapper.BillDtoMapper;
import com.example.Robomain.application.bill.query.ListBillsByTaskQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.bill.repository.IBillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListBillsByTaskQueryHandler {

    private final IBillRepository billRepository;
    private final BillDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<BillDto> handle(ListBillsByTaskQuery query) {
        var bills = billRepository.findByTaskId(query.getTaskId(), query.getPage(), query.getSize());
        var total = billRepository.countByTaskId(query.getTaskId());
        var dtos = bills.stream().map(dtoMapper::toDto).toList();
        return PaginationResponse.of(dtos, total, query.getPage(), query.getSize());
    }
}

