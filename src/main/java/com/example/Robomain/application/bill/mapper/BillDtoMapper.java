package com.example.Robomain.application.bill.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.bill.dto.BillDto;
import com.example.Robomain.domain.bill.model.Bill;

@Component
public class BillDtoMapper {

    public BillDto toDto(Bill b) {
        return BillDto.builder()
                .id(b.getId()).billId(b.getBillId())
                .billTotal(b.getBillTotal()).taskId(b.getTaskId())
                .build();
    }
}
