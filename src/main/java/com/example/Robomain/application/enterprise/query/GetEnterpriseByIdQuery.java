package com.example.Robomain.application.enterprise.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetEnterpriseByIdQuery {
    private final UUID enterpriseId;
}
