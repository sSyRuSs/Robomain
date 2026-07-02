package com.example.Robomain.application.vendor.query;

import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumVendorStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ListVendorsQuery {
    private final int page;
    private final int size;
    private final String keyword;
    private final UUID enterpriseId;
    private final EnumVendorStatus status;
}
