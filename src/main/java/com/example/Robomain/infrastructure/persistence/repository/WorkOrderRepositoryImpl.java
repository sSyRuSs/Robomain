package com.example.Robomain.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.Robomain.domain.shared.enums.EnumPriority;
import com.example.Robomain.domain.shared.enums.EnumStatus;
import com.example.Robomain.domain.work_order.model.WorkOrder;
import com.example.Robomain.domain.work_order.repository.IWorkOrderRepository;
import com.example.Robomain.infrastructure.persistence.jpa.WorkOrderJpaRepository;
import com.example.Robomain.infrastructure.persistence.mapper.WorkOrderMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WorkOrderRepositoryImpl implements IWorkOrderRepository {

    private final WorkOrderJpaRepository jpaRepository;
    private final WorkOrderMapper mapper;

    @Override public Optional<WorkOrder> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<WorkOrder> search(String keyword, EnumStatus status, EnumPriority priority,
                                   UUID maintenanceId, UUID assetId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return jpaRepository.search(keyword, status, priority, maintenanceId, assetId, pageable)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public long count(String keyword, EnumStatus status, EnumPriority priority,
                      UUID maintenanceId, UUID assetId) {
        return jpaRepository.countSearch(keyword, status, priority, maintenanceId, assetId);
    }

    @Override public WorkOrder save(WorkOrder w) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(w)));
    }

    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
}
