package com.example.Robomain.application.enterprise.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.enterprise.command.CreateEnterpriseCommand;
import com.example.Robomain.application.enterprise.mapper.EnterpriseDtoMapper;
import com.example.Robomain.domain.enterprise.event.EnterpriseCreatedEvent;
import com.example.Robomain.domain.enterprise.model.Enterprise;
import com.example.Robomain.domain.enterprise.repository.IEnterpriseRepository;
import com.example.Robomain.domain.shared.exception.ConflictException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateEnterpriseCommandHandler {

    private final IEnterpriseRepository enterpriseRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final EnterpriseDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateEnterpriseCommand command) {
        if (command.getEnterpriseCode() != null
                && enterpriseRepository.existsByEnterpriseCode(command.getEnterpriseCode())) {
            throw new ConflictException("Enterprise", "code", command.getEnterpriseCode());
        }

        Enterprise enterprise = Enterprise.create(
                command.getEnterpriseName(),
                command.getEnterpriseCode(),
                command.getEnterpriseMail(),
                command.getEnterprisePhone(),
                command.getEnterpriseAddress()
        );

        Enterprise saved = enterpriseRepository.save(enterprise);
        eventPublisher.publishEvent(new EnterpriseCreatedEvent(saved.getId(), saved.getEnterpriseName()));
        return saved.getId();
    }
}
