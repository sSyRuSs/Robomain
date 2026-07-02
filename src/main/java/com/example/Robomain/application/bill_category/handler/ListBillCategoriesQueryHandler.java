package com.example.Robomain.application.bill_category.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.bill_category.dto.BillCategoryDto;
import com.example.Robomain.application.bill_category.mapper.BillCategoryDtoMapper;
import com.example.Robomain.application.bill_category.query.ListBillCategoriesQuery;
import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.domain.bill_category.repository.IBillCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListBillCategoriesQueryHandler {

    private final IBillCategoryRepository billCategoryRepository;
    private final BillCategoryDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<BillCategoryDto> handle(ListBillCategoriesQuery query) {
        var items = billCategoryRepository.findByBillId(query.getBillId(), query.getPage(), query.getSize());
        var total = billCategoryRepository.countByBillId(query.getBillId());
        var dtos = items.stream().map(dtoMapper::toDto).toList();
        return PaginationResponse.of(dtos, total, query.getPage(), query.getSize());
    }
}

