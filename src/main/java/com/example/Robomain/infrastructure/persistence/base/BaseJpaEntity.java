package com.example.Robomain.infrastructure.persistence.base;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Base JPA entity for infrastructure persistence layer ONLY.
 *
 * Why this exists separately from domain models:
 * - Domain models must be pure Java (no JPA/Spring annotations)
 * - Clean Architecture: infrastructure depends on domain, not vice versa
 * - JPA entities are an infrastructure detail, not a business concept
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class BaseJpaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    protected Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    protected Date updatedAt;
}

