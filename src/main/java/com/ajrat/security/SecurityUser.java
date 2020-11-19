package com.ajrat.security;

import com.ajrat.domain.Reader;
import com.ajrat.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

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
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromReader(Reader reader) {
        return new org.springframework.security.core.userdetails.User(
                reader.getName(), reader.getPassword(),
                reader.getStatus().equals(Status.ACTIVE),
                reader.getStatus().equals(Status.ACTIVE),
                reader.getStatus().equals(Status.ACTIVE),
                reader.getStatus().equals(Status.ACTIVE),
                reader.getRole().getAuthorities()
        );
    }
}