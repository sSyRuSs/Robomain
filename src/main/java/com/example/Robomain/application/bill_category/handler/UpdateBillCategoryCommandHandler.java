package com.example.Robomain.application.bill_category.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.bill_category.command.UpdateBillCategoryCommand;
import com.example.Robomain.application.bill_category.mapper.BillCategoryDtoMapper;
import com.example.Robomain.domain.bill_category.repository.IBillCategoryRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateBillCategoryCommandHandler {

    private final IBillCategoryRepository billCategoryRepository;
    private final BillCategoryDtoMapper dtoMapper;

    @Transactional
    public UUID handle(UpdateBillCategoryCommand cmd) {
        var bc = billCategoryRepository.findById(cmd.getId())
                .orElseThrow(() -> new ResourceNotFoundException("BillCategory", cmd.getId()));
        bc.update(cmd.getBillCategoryName(), cmd.getBillCategoryPrice());
        return billCategoryRepository.save(bc).getId();
    }
}
