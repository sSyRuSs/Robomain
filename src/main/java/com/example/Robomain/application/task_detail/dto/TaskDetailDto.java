package com.example.Robomain.application.task_detail.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class TaskDetailDto {
    private UUID id;
    private String taskDetailCode;
    private String taskDetailProblem;
    private String taskDetailDescription;
    private boolean completed;
    private UUID taskId;
}
