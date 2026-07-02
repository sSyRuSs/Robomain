package com.example.Robomain.application.task_detail.mapper;

import org.springframework.stereotype.Component;

import com.example.Robomain.application.task_detail.dto.TaskDetailDto;
import com.example.Robomain.domain.task_detail.model.TaskDetail;

@Component
public class TaskDetailDtoMapper {

    public TaskDetailDto toDto(TaskDetail d) {
        return TaskDetailDto.builder()
                .id(d.getId()).taskId(d.getTaskId()).taskDetailCode(d.getTaskDetailCode())
                .taskDetailProblem(d.getTaskDetailProblem())
                .taskDetailDescription(d.getTaskDetailDescription())
                .completed(d.isCompleted())
                .build();
    }
}
