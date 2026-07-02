package com.example.Robomain.application.task_detail.command;

import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class CompleteTaskDetailCommand {
    @NotNull
    private UUID taskDetailId;
}
