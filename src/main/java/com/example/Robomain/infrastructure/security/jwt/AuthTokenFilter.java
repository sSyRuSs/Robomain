package com.example.Robomain.infrastructure.security.jwt;

import java.io.IOException;
import java.util.Date;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.example.Robomain.domain.auth.exception.AuthenticationFailedException;
import com.example.Robomain.infrastructure.persistence.entity.UserTokenJpaEntity;
import com.example.Robomain.infrastructure.persistence.jpa.UserTokenJpaRepository;
import com.example.Robomain.infrastructure.security.userdetails.UserDetailsServiceImpl;

public class AuthTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Autowired private JwtService jwtService;
    @Autowired private UserDetailsServiceImpl userDetailsService;
    @Autowired private UserTokenJpaRepository userTokenJpaRepository;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            // Public endpoints — skip token validation
            String path = request.getRequestURI();
            if (isPublicPath(path) || isPublicCreateUser(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            String jwt = parseJwt(request);
            if (jwt != null && jwtService.validateJwtToken(jwt)) {
                var userTokenOpt = userTokenJpaRepository.findByAccessToken(jwt);
                if (userTokenOpt.isEmpty() || Boolean.TRUE.equals(userTokenOpt.get().getIsReseted())) {
                    throw new AuthenticationFailedException("Invalid or revoked token", "INVALID_TOKEN");
                }
                UserTokenJpaEntity userToken = userTokenOpt.get();

                // Touch last activity timestamp
                userToken.setUpdatedAt(new Date());
                userTokenJpaRepository.save(userToken);

                String email = jwtService.getEmailFromToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e.getMessage());
            resolver.resolveException(request, response, null, e);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean isPublicPath(String path) {
        return path.startsWith("/api/v1/auth/login")
                || path.startsWith("/api/v1/auth/refresh")
                || path.startsWith("/ws")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/api-docs");
    }

    private boolean isPublicCreateUser(HttpServletRequest request) {
        return "POST".equalsIgnoreCase(request.getMethod())
                && request.getRequestURI().equals("/api/v1/user");
    }

    private String parseJwt(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
