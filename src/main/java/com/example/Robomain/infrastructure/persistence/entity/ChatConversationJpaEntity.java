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
@Table(name = "chat_conversation")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class ChatConversationJpaEntity extends BaseJpaEntity {

    @Column(nullable = false, length = 20)
    @Builder.Default
    private String type = "DIRECT";

    @Column(length = 120)
    private String name;

    @Column(name = "department_id")
    private UUID departmentId;

    @Column(name = "enterprise_id")
    private UUID enterpriseId;

    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean deleted = false;

    @Column(name = "can_send_messages", nullable = false)
    @Builder.Default
    private Boolean canSendMessages = true;
}
