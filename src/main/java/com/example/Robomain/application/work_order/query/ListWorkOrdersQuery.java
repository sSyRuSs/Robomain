package com.example.Robomain.application.work_order.query;

import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumPriority;
import com.example.Robomain.domain.shared.enums.EnumStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class ListWorkOrdersQuery {
    private int page = 0;
    private int size = 10;
    private String search;
    private EnumStatus status;
    private EnumPriority priority;
    private UUID maintenanceId;
    private UUID assetId;
}
