package com.example.Robomain.application.purchase_request.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.purchase_request.dto.PurchaseRequestDto;
import com.example.Robomain.domain.purchase_request.model.PurchaseRequest;

@Component
public class PurchaseRequestDtoMapper {

    public PurchaseRequestDto toDto(PurchaseRequest pr) {
        return PurchaseRequestDto.builder()
                .id(pr.getId()).requestCode(pr.getRequestCode()).inventoryId(pr.getInventoryId())
                .requestedQuantity(pr.getRequestedQuantity()).estimatedCost(pr.getEstimatedCost())
                .status(pr.getStatus()).type(pr.getType()).reason(pr.getReason())
                .requestedBy(pr.getRequestedBy()).requestedDate(pr.getRequestedDate())
                .requiredDate(pr.getRequiredDate()).approvedBy(pr.getApprovedBy())
                .approvedDate(pr.getApprovedDate()).approvalNotes(pr.getApprovalNotes())
                .vendorId(pr.getVendorId()).enterpriseId(pr.getEnterpriseId())
                .build();
    }
}
