package com.example.Robomain.application.user.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.user.dto.UserDto;
import com.example.Robomain.application.user.query.GetUserByIdQuery;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;
import com.example.Robomain.domain.user.repository.IUserRepository;
import com.example.Robomain.domain.shared.enums.EnumRole;

import lombok.RequiredArgsConstructor;
import java.util.List;

/**
 * Query handler — reads user by ID. NO state modification.
 */
@Service
@RequiredArgsConstructor
public class GetUserByIdQueryHandler {

    private final IUserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDto handle(GetUserByIdQuery query) {
        var user = userRepository.findById(query.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", query.getUserId()));

        List<EnumRole> roles = user.getRoles().stream()
                .map(r -> r.getName())
                .toList();

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .phone(user.getPhone())
                .address(user.getAddress())
                .departmentId(user.getDepartmentId())
                .enterpriseId(user.getEnterpriseId())
                .roles(roles)
                .build();
    }
}

