package com.example.hostelweb.domain;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public enum Roles {
    USER,
    ADMIN;

    public List<SimpleGrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority(this.name()));
    }
}
