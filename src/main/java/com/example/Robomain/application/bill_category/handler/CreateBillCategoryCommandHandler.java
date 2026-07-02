package com.example.Robomain.application.bill_category.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.bill_category.command.CreateBillCategoryCommand;
import com.example.Robomain.application.bill_category.mapper.BillCategoryDtoMapper;
import com.example.Robomain.domain.bill_category.model.BillCategory;
import com.example.Robomain.domain.bill_category.repository.IBillCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateBillCategoryCommandHandler {

    private final IBillCategoryRepository billCategoryRepository;
    private final BillCategoryDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateBillCategoryCommand cmd) {
        BillCategory bc = BillCategory.create(cmd.getBillCategoryName(), cmd.getBillCategoryPrice(), cmd.getBillId());
        return billCategoryRepository.save(bc).getId();
    }
}
