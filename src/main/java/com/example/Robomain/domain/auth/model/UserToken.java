package com.example.Robomain.domain.auth.model;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UserToken domain entity — represents an active authentication session.
 * Pure Java, no JPA annotations.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {

    private UUID id;
    private UUID userId;
    private String accessToken;
    private String refreshToken;
    private Date refreshTokenExpire;
    private Boolean isReseted;
    private Date createdAt;
    private Date updatedAt;

    public boolean isRefreshTokenExpired() {
        return refreshTokenExpire == null || refreshTokenExpire.before(new Date());
    }

    public boolean isRevoked() {
        return Boolean.TRUE.equals(isReseted);
    }

    public void revoke() {
        this.isReseted = true;
    }
}
