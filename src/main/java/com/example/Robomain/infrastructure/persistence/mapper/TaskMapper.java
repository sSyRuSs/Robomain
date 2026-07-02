package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.task.model.Task;
import com.example.Robomain.infrastructure.persistence.entity.TaskJpaEntity;

@Component
public class TaskMapper {

    public Task toDomain(TaskJpaEntity e) {
        if (e == null) return null;
        return Task.builder()
                .id(e.getId()).taskName(e.getTaskName()).taskLocation(e.getTaskLocation())
                .taskDescription(e.getTaskDescription())
                .startDate(e.getStartDate()).completeDate(e.getCompleteDate())
                .suggestStartDate(e.getSuggestStartDate()).suggestCompleteDate(e.getSuggestCompleteDate())
                .status(e.getStatus()).priority(e.getPriority())
                .star(e.isStar()).issueNumber(e.getIssueNumber())
                .assetId(e.getAssetId()).workOrderId(e.getWorkOrderId())
                .maintenanceId(e.getMaintenanceId()).userId(e.getUserId())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public TaskJpaEntity toJpa(Task d) {
        if (d == null) return null;
        TaskJpaEntity e = TaskJpaEntity.builder()
                .taskName(d.getTaskName()).taskLocation(d.getTaskLocation())
                .taskDescription(d.getTaskDescription())
                .startDate(d.getStartDate()).completeDate(d.getCompleteDate())
                .suggestStartDate(d.getSuggestStartDate()).suggestCompleteDate(d.getSuggestCompleteDate())
                .status(d.getStatus()).priority(d.getPriority())
                .star(d.isStar()).issueNumber(d.getIssueNumber())
                .assetId(d.getAssetId()).workOrderId(d.getWorkOrderId())
                .maintenanceId(d.getMaintenanceId()).userId(d.getUserId())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
