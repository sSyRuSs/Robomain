package com.example.Robomain.application.task.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.task.dto.TaskDto;
import com.example.Robomain.domain.task.model.Task;

@Component
public class TaskDtoMapper {

    public TaskDto toDto(Task t) {
        return TaskDto.builder()
                .id(t.getId()).taskName(t.getTaskName()).taskLocation(t.getTaskLocation())
                .taskDescription(t.getTaskDescription())
                .startDate(t.getStartDate()).completeDate(t.getCompleteDate())
                .suggestStartDate(t.getSuggestStartDate()).suggestCompleteDate(t.getSuggestCompleteDate())
                .status(t.getStatus()).priority(t.getPriority())
                .star(t.isStar()).issueNumber(t.getIssueNumber())
                .assetId(t.getAssetId()).workOrderId(t.getWorkOrderId())
                .maintenanceId(t.getMaintenanceId()).userId(t.getUserId())
                .build();
    }
}
