package com.example.Robomain.application.bill.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ListBillsByTaskQuery {
    private final UUID taskId;
    private final int page;
    private final int size;
}
