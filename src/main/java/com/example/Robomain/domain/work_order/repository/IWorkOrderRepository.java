package com.example.Robomain.domain.work_order.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumPriority;
import com.example.Robomain.domain.shared.enums.EnumStatus;
import com.example.Robomain.domain.work_order.model.WorkOrder;

public interface IWorkOrderRepository {
    Optional<WorkOrder> findById(UUID id);
    List<WorkOrder> search(String keyword, EnumStatus status, EnumPriority priority,
                           UUID maintenanceId, UUID assetId, int page, int size);
    long count(String keyword, EnumStatus status, EnumPriority priority,
               UUID maintenanceId, UUID assetId);
    WorkOrder save(WorkOrder workOrder);
    void deleteById(UUID id);
}
