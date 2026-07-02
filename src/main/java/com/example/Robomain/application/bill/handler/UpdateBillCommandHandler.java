package com.example.Robomain.application.bill.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.bill.command.UpdateBillCommand;
import com.example.Robomain.application.bill.mapper.BillDtoMapper;
import com.example.Robomain.domain.bill.repository.IBillRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateBillCommandHandler {

    private final IBillRepository billRepository;
    private final BillDtoMapper dtoMapper;

    @Transactional
    public UUID handle(UpdateBillCommand cmd) {
        var bill = billRepository.findById(cmd.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bill", cmd.getId()));
        bill.update(cmd.getBillId(), cmd.getBillTotal());
        return billRepository.save(bill).getId();
    }
}
