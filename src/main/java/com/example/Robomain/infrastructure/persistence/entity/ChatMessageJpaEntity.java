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
@Table(name = "chat_message")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class ChatMessageJpaEntity extends BaseJpaEntity {

    @Column(name = "conversation_id", nullable = false)
    private UUID conversationId;

    @Column(name = "sender_id", nullable = false)
    private UUID senderId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "message_type", nullable = false, length = 20)
    @Builder.Default
    private String messageType = "TEXT";

    @Column(name = "client_message_id", length = 80)
    private String clientMessageId;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean deleted = false;

    @Column(name = "is_pinned", nullable = false)
    @Builder.Default
    private Boolean pinned = false;
}
