package com.example.Robomain.infrastructure.persistence.entity;

import java.util.UUID;

import com.example.Robomain.infrastructure.persistence.base.BaseJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task_detail")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class TaskDetailJpaEntity extends BaseJpaEntity {

    @Column(name = "task_detail_code")
    private String taskDetailCode;

    @Column(name = "task_detail_problem")
    private String taskDetailProblem;

    @Column(name = "task_detail_description")
    private String taskDetailDescription;

    @Column(name = "is_completed")
    private boolean completed;

    @Column(name = "task_id")
    private UUID taskId;
}
