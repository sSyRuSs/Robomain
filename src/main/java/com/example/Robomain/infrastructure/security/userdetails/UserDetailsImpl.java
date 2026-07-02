package com.example.Robomain.infrastructure.security.userdetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Robomain.domain.user.model.User;

import lombok.Builder;
import lombok.Getter;

/**
 * Spring Security UserDetails adapter for our domain User.
 * Bridges domain model to Spring Security contract.
 */
@Builder
@Getter
public class UserDetailsImpl implements UserDetails {

    private UUID id;
    private String email;
    private String username;
    private String phoneNumber;
    private String password;
    private List<GrantedAuthority> authorities;

    public static UserDetailsImpl from(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> (GrantedAuthority) new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                .toList();

        return UserDetailsImpl.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .phoneNumber(user.getPhone())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email; // Spring Security uses username as the login identifier
    }

    public String getDisplayName() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
