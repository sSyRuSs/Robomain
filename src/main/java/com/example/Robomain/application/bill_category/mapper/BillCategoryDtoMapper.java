package com.example.Robomain.application.bill_category.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.bill_category.dto.BillCategoryDto;
import com.example.Robomain.domain.bill_category.model.BillCategory;

@Component
public class BillCategoryDtoMapper {

    public BillCategoryDto toDto(BillCategory bc) {
        return BillCategoryDto.builder()
                .id(bc.getId()).billCategoryName(bc.getBillCategoryName())
                .billCategoryPrice(bc.getBillCategoryPrice()).billId(bc.getBillId())
                .build();
    }
}
