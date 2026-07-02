package com.example.Robomain.application.task_detail.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class ListTaskDetailsQuery {
    private UUID taskId;
    private int page = 0;
    private int size = 10;
}
