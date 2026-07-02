package com.example.Robomain.application.bill.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.bill.command.CreateBillCommand;
import com.example.Robomain.application.bill.mapper.BillDtoMapper;
import com.example.Robomain.domain.bill.model.Bill;
import com.example.Robomain.domain.bill.repository.IBillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateBillCommandHandler {

    private final IBillRepository billRepository;
    private final BillDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateBillCommand cmd) {
        Bill bill = Bill.create(cmd.getBillId(), cmd.getBillTotal(), cmd.getTaskId());
        return billRepository.save(bill).getId();
    }
}
