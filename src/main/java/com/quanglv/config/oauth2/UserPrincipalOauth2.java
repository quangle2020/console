package com.quanglv.config.oauth2;

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

    private Long status;

    @JsonIgnore
    private String password;


    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipalOauth2(String username, Long status, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.status = status;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipalOauth2 create(Users users){
        List<GrantedAuthority> authorities = users.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getRoleCode())
        ).collect(Collectors.toList());


        return new UserPrincipalOauth2(
                users.getUsername(),
                users.getStatus(),
                users.getPassword(),
                authorities
        );

    }

    //================================================================//
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
