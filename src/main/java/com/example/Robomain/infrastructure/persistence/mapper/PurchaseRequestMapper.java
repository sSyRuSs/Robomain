package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.purchase_request.model.PurchaseRequest;
import com.example.Robomain.infrastructure.persistence.entity.PurchaseRequestJpaEntity;

@Component
public class PurchaseRequestMapper {

    public PurchaseRequest toDomain(PurchaseRequestJpaEntity e) {
        if (e == null) return null;
        return PurchaseRequest.builder()
                .id(e.getId()).requestCode(e.getRequestCode()).inventoryId(e.getInventoryId())
                .requestedQuantity(e.getRequestedQuantity()).estimatedCost(e.getEstimatedCost())
                .status(e.getStatus()).type(e.getType()).reason(e.getReason())
                .requestedBy(e.getRequestedBy()).requestedDate(e.getRequestedDate())
                .requiredDate(e.getRequiredDate()).approvedBy(e.getApprovedBy())
                .approvedDate(e.getApprovedDate()).approvalNotes(e.getApprovalNotes())
                .vendorId(e.getVendorId()).enterpriseId(e.getEnterpriseId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public PurchaseRequestJpaEntity toJpa(PurchaseRequest d) {
        if (d == null) return null;
        PurchaseRequestJpaEntity e = PurchaseRequestJpaEntity.builder()
                .requestCode(d.getRequestCode()).inventoryId(d.getInventoryId())
                .requestedQuantity(d.getRequestedQuantity()).estimatedCost(d.getEstimatedCost())
                .status(d.getStatus()).type(d.getType()).reason(d.getReason())
                .requestedBy(d.getRequestedBy()).requestedDate(d.getRequestedDate())
                .requiredDate(d.getRequiredDate()).approvedBy(d.getApprovedBy())
                .approvedDate(d.getApprovedDate()).approvalNotes(d.getApprovalNotes())
                .vendorId(d.getVendorId()).enterpriseId(d.getEnterpriseId())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
