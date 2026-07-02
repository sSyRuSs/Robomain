package com.example.Robomain.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.domain.task_detail.model.TaskDetail;
import com.example.Robomain.infrastructure.persistence.entity.TaskDetailJpaEntity;

@Component
public class TaskDetailMapper {

    public TaskDetail toDomain(TaskDetailJpaEntity e) {
        if (e == null) return null;
        return TaskDetail.builder()
                .id(e.getId()).taskId(e.getTaskId())
                .taskDetailCode(e.getTaskDetailCode())
                .taskDetailProblem(e.getTaskDetailProblem())
                .taskDetailDescription(e.getTaskDetailDescription())
                .completed(e.isCompleted())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    public TaskDetailJpaEntity toJpa(TaskDetail d) {
        if (d == null) return null;
        TaskDetailJpaEntity e = TaskDetailJpaEntity.builder()
                .taskId(d.getTaskId()).taskDetailCode(d.getTaskDetailCode())
                .taskDetailProblem(d.getTaskDetailProblem())
                .taskDetailDescription(d.getTaskDetailDescription())
                .completed(d.isCompleted())
                .build();
        if (d.getId() != null) e.setId(d.getId());
        return e;
    }
}
