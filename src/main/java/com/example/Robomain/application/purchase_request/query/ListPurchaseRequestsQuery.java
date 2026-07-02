package com.example.Robomain.application.purchase_request.query;

import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumPRStatus;
import com.example.Robomain.domain.shared.enums.EnumPRType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ListPurchaseRequestsQuery {
    private final int page;
    private final int size;
    private final String keyword;
    private final EnumPRStatus status;
    private final EnumPRType type;
    private final UUID enterpriseId;
    private final UUID inventoryId;
}
