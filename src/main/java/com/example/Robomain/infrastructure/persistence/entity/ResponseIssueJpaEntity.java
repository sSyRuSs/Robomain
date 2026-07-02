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
@Table(name = "response_issue")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class ResponseIssueJpaEntity extends BaseJpaEntity {

    @Column(name = "response_title")
    private String responseTitle;

    @Column(name = "response_description", columnDefinition = "TEXT")
    private String responseDescription;

    @Column(name = "important")
    @Builder.Default
    private Boolean important = false;

    @Column(name = "response_id")
    private UUID parentResponseId;

    @Column(name = "issue_id")
    private UUID issueId;
}
