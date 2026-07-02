package com.example.Robomain.application.vendor.query;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class GetVendorByIdQuery {
    private final UUID vendorId;
}
