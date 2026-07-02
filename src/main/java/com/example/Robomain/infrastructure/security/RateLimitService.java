package com.example.Robomain.infrastructure.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

/**
 * In-memory rate limiter for authentication endpoints.
 * Blocks accounts after too many failed login attempts.
 * 
 * Why: Prevents brute-force attacks on the login endpoint.
 * For production, replace with Redis-backed implementation.
 */
@Component
public class RateLimitService {

    private static final int MAX_ATTEMPTS = 5;
    private static final long BLOCK_DURATION_MS = 15 * 60 * 1000L; // 15 minutes

    private final Map<String, AtomicInteger> attemptCounts = new ConcurrentHashMap<>();
    private final Map<String, Long> blockExpiry = new ConcurrentHashMap<>();

    public boolean isAllowed(String identifier) {
        Long expiry = blockExpiry.get(identifier);
        if (expiry != null && System.currentTimeMillis() < expiry) {
            return false; // Still blocked
        }
        return true;
    }

    public void recordFailedAttempt(String identifier) {
        AtomicInteger count = attemptCounts.computeIfAbsent(identifier, k -> new AtomicInteger(0));
        int attempts = count.incrementAndGet();
        if (attempts >= MAX_ATTEMPTS) {
            blockExpiry.put(identifier, System.currentTimeMillis() + BLOCK_DURATION_MS);
        }
    }

    public void recordSuccessfulLogin(String identifier) {
        attemptCounts.remove(identifier);
        blockExpiry.remove(identifier);
    }

    public int getRemainingAttempts(String identifier) {
        AtomicInteger count = attemptCounts.get(identifier);
        if (count == null) return MAX_ATTEMPTS;
        return Math.max(0, MAX_ATTEMPTS - count.get());
    }
}
