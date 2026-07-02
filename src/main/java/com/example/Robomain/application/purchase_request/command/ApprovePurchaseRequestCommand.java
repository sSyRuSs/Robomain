package com.example.Robomain.application.purchase_request.command;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ApprovePurchaseRequestCommand {
    private UUID requestId;
    private UUID approvedBy;
    private String approvalNotes;
}
