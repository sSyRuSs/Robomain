package com.example.Robomain.application.bill_category.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.bill_category.dto.BillCategoryDto;
import com.example.Robomain.application.bill_category.mapper.BillCategoryDtoMapper;
import com.example.Robomain.application.bill_category.query.GetBillCategoryByIdQuery;
import com.example.Robomain.domain.bill_category.repository.IBillCategoryRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetBillCategoryByIdQueryHandler {

    private final IBillCategoryRepository billCategoryRepository;
    private final BillCategoryDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public BillCategoryDto handle(GetBillCategoryByIdQuery query) {
        return billCategoryRepository.findById(query.getBillCategoryId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("BillCategory", query.getBillCategoryId()));
    }
}

