package com.example.Robomain.domain.task_detail.model;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.exception.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * TaskDetail domain entity — a specific work item / checklist entry within a Task.
 * Records the problem found, action taken, and completion state.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetail {

    private UUID id;
    private String taskDetailCode;
    private String taskDetailProblem;
    private String taskDetailDescription;
    @Builder.Default
    private boolean completed = false;
    private UUID taskId;
    private Date createdAt;
    private Date updatedAt;

    public static TaskDetail create(UUID taskId, String code, String problem, String description) {
        if (taskId == null) throw new ValidationException("TaskDetail must belong to a task");
        return TaskDetail.builder()
                .id(UUID.randomUUID())
                .taskId(taskId)
                .taskDetailCode(code)
                .taskDetailProblem(problem)
                .taskDetailDescription(description)
                .completed(false)
                .build();
    }

    public void update(String problem, String description) {
        if (problem != null) this.taskDetailProblem = problem;
        if (description != null) this.taskDetailDescription = description;
    }

    public void complete() {
        this.completed = true;
    }
}
