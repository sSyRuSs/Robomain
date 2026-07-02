package com.example.Robomain.application.work_order.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetWorkOrderByIdQuery {
    private final UUID workOrderId;
}
