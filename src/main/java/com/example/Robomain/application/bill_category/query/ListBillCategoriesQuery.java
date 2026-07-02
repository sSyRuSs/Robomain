package com.example.Robomain.application.bill_category.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ListBillCategoriesQuery {
    private final UUID billId;
    private final int page;
    private final int size;
}
