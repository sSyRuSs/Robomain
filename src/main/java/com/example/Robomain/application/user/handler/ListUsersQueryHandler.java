package com.example.Robomain.application.user.handler;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.user.dto.UserDto;
import com.example.Robomain.application.user.query.ListUsersQuery;
import com.example.Robomain.domain.shared.enums.EnumRole;
import com.example.Robomain.domain.user.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Query handler — lists users with pagination. NO state modification.
 */
@Service
@RequiredArgsConstructor
public class ListUsersQueryHandler {

    private final IUserRepository userRepository;

    @Transactional(readOnly = true)
    public PaginationResponse<UserDto> handle(ListUsersQuery query) {
        List<UserDto> users = userRepository.findAll(query.getPage(), query.getSize())
                .stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .phone(user.getPhone())
                        .address(user.getAddress())
                        .roles(user.getRoles().stream().map(r -> r.getName()).toList())
                        .build())
                .toList();

        long total = userRepository.count();
        return PaginationResponse.of(users, total, query.getPage(), query.getSize());
    }
}

