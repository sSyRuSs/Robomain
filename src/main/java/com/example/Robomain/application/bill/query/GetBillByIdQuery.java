package com.example.Robomain.application.bill.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetBillByIdQuery {
    private final UUID billId;
}
