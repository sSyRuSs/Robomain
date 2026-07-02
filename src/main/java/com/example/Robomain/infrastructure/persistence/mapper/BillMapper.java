package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.bill.model.Bill;
import com.example.Robomain.infrastructure.persistence.entity.BillJpaEntity;

@Component
public class BillMapper {

    public Bill toDomain(BillJpaEntity e) {
        if (e == null) return null;
        return Bill.builder()
                .id(e.getId()).billId(e.getBillId()).billTotal(e.getBillTotal()).taskId(e.getTaskId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public BillJpaEntity toJpa(Bill d) {
        if (d == null) return null;
        BillJpaEntity e = BillJpaEntity.builder()
                .billId(d.getBillId()).billTotal(d.getBillTotal()).taskId(d.getTaskId())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
