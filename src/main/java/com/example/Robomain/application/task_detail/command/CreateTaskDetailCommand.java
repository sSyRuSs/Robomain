package com.example.Robomain.application.task_detail.command;

import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateTaskDetailCommand {
    @NotNull(message = "Task ID is required")
    private UUID taskId;
    private String taskDetailCode;
    private String taskDetailProblem;
    private String taskDetailDescription;
}
