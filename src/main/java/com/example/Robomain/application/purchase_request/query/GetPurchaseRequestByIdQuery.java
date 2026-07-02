package com.example.Robomain.application.purchase_request.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetPurchaseRequestByIdQuery {
    private final UUID requestId;
}
