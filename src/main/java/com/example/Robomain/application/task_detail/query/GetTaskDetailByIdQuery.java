package com.example.Robomain.application.task_detail.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetTaskDetailByIdQuery {
    private final UUID taskDetailId;
}
