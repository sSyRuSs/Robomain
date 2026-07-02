package com.example.Robomain.infrastructure.security.auth;

import java.util.List;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.Robomain.application.auth.port.IAuthenticationPort;
import com.example.Robomain.infrastructure.security.jwt.JwtService;
import com.example.Robomain.infrastructure.security.userdetails.UserDetailsImpl;
import com.example.Robomain.infrastructure.security.userdetails.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

/**
 * Infrastructure implementation of IAuthenticationPort.
 * Bridges application auth handlers to Spring Security.
 * Owns all Spring Security coupling — nothing above this layer sees Spring Security types.
 */
@Component
@RequiredArgsConstructor
public class AuthenticationPortImpl implements IAuthenticationPort {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public LoginResult login(String email, String password) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
        String token = jwtService.generateJwtToken(auth);
        return new LoginResult(token, principal.getId());
    }

    @Override
    public String generateTokenForUser(UUID userId, String email) {
        var userDetails = userDetailsService.loadUserByUsername(email);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        return jwtService.generateJwtToken(auth);
    }
}
