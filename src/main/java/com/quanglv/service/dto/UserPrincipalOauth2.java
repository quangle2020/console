package com.quanglv.service.dto;

import com.quanglv.domain.first.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipalOauth2 implements UserDetails {

    private String username;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipalOauth2(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipalOauth2 createPrincipalOauth2(Users userEntity){
        List<GrantedAuthority> authorities = userEntity.getRoles().stream().map(roleEntity ->
                        new SimpleGrantedAuthority(roleEntity.getRoleCode())
                ).collect(Collectors.toList());
        return new UserPrincipalOauth2(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
