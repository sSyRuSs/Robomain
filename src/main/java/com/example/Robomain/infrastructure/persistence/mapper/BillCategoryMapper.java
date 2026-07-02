package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.bill_category.model.BillCategory;
import com.example.Robomain.infrastructure.persistence.entity.BillCategoryJpaEntity;

@Component
public class BillCategoryMapper {

    public BillCategory toDomain(BillCategoryJpaEntity e) {
        if (e == null) return null;
        return BillCategory.builder()
                .id(e.getId()).billCategoryName(e.getBillCategoryName())
                .billCategoryPrice(e.getBillCategoryPrice()).billId(e.getBillId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public BillCategoryJpaEntity toJpa(BillCategory d) {
        if (d == null) return null;
        BillCategoryJpaEntity e = BillCategoryJpaEntity.builder()
                .billCategoryName(d.getBillCategoryName())
                .billCategoryPrice(d.getBillCategoryPrice()).billId(d.getBillId())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
