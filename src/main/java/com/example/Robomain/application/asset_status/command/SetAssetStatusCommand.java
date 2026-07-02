package com.example.Robomain.application.asset_status.command;

import java.util.Date;
import java.util.UUID;

import com.example.Robomain.domain.shared.enums.EnumAssetStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SetAssetStatusCommand {
    @NotNull(message = "Asset ID is required")
    private UUID assetId;
    @NotNull(message = "Status is required")
    private EnumAssetStatus status;
    private String reason;
    private Date fromDate;
}
