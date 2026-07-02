package com.example.Robomain.application.task_detail.command;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UpdateTaskDetailCommand {
    private UUID taskDetailId;
    private String taskDetailProblem;
    private String taskDetailDescription;
}
