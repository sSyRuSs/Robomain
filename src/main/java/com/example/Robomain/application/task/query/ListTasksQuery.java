package com.example.Robomain.application.task.query;

import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class ListTasksQuery {
    private int page = 0;
    private int size = 10;
    private String search;
    private EnumStatus status;
    private UUID workOrderId;
    private UUID userId;
}
