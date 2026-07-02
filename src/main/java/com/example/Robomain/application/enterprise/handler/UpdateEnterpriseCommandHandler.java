package com.example.Robomain.application.enterprise.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.enterprise.command.UpdateEnterpriseCommand;
import com.example.Robomain.application.enterprise.mapper.EnterpriseDtoMapper;
import com.example.Robomain.domain.enterprise.model.Enterprise;
import com.example.Robomain.domain.enterprise.repository.IEnterpriseRepository;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateEnterpriseCommandHandler {

    private final IEnterpriseRepository enterpriseRepository;
    private final EnterpriseDtoMapper dtoMapper;

    @Transactional
    public UUID handle(UpdateEnterpriseCommand command) {
        Enterprise enterprise = enterpriseRepository.findById(command.getEnterpriseId())
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise", command.getEnterpriseId()));

        enterprise.update(command.getEnterpriseName(), command.getEnterpriseCode(),
                command.getEnterpriseMail(), command.getEnterprisePhone(), command.getEnterpriseAddress());

        return enterpriseRepository.save(enterprise).getId();
    }
}
