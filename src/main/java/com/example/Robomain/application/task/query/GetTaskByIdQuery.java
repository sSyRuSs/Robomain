package com.example.Robomain.application.task.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetTaskByIdQuery {
    private final UUID taskId;
}
