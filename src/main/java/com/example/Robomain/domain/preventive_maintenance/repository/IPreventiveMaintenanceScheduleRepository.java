package com.example.Robomain.domain.preventive_maintenance.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Robomain.domain.preventive_maintenance.model.PreventiveMaintenanceSchedule;
import com.example.Robomain.domain.shared.enums.EnumPreventiveScheduleStatus;

public interface IPreventiveMaintenanceScheduleRepository {
    Optional<PreventiveMaintenanceSchedule> findById(UUID id);
    List<PreventiveMaintenanceSchedule> search(String keyword, UUID assetId, UUID maintenanceId,
                                                EnumPreventiveScheduleStatus status, UUID enterpriseId,
                                                int page, int size);
    long count(String keyword, UUID enterpriseId);
    PreventiveMaintenanceSchedule save(PreventiveMaintenanceSchedule schedule);
    void deleteById(UUID id);
}
