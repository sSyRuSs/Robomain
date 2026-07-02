package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.preventive_maintenance.model.PreventiveMaintenanceSchedule;
import com.example.Robomain.domain.preventive_maintenance.repository.IPreventiveMaintenanceScheduleRepository;
import com.example.Robomain.domain.shared.enums.EnumPreventiveScheduleStatus;
import com.example.Robomain.infrastructure.persistence.jpa.PreventiveMaintenanceScheduleJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.PreventiveMaintenanceScheduleMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PreventiveMaintenanceScheduleRepositoryImpl implements IPreventiveMaintenanceScheduleRepository {

    private final PreventiveMaintenanceScheduleJpaRepository jpaRepository;
    private final PreventiveMaintenanceScheduleMapper mapper;

    @Override public Optional<PreventiveMaintenanceSchedule> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<PreventiveMaintenanceSchedule> search(String keyword, UUID assetId, UUID maintenanceId,
                                                       EnumPreventiveScheduleStatus status, UUID enterpriseId,
                                                       int page, int size) {
        return jpaRepository.search(keyword, assetId, maintenanceId, status, enterpriseId,
                        PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")))
                .stream().map(mapper::toDomain).toList();
    }

    @Override public long count(String keyword, UUID enterpriseId) {
        return jpaRepository.countSearch(keyword, enterpriseId);
    }

    @Override public PreventiveMaintenanceSchedule save(PreventiveMaintenanceSchedule s) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(s)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
