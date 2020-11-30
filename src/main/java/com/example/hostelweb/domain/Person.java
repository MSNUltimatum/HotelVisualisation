package com.example.hostelweb.domain;

import lombok.Data;
import org.hibernate.annotations.Check;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Person")
@Data
public class Person implements UserDetails {
    @Id
    @JoinColumn(name = "person_passport_data", nullable = false)
    private String personPassportData;

    @NotBlank
    @JoinColumn(name = "password")
    private String password;

    @Transient
    private String confirmed_password;

    @NotBlank
    @JoinColumn(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @JoinColumn(name = "second_name", nullable = false)
    private String secondName;

    @NotBlank
    @JoinColumn(name = "city")
    private String city;

    @NotBlank
    @Check(constraints = "gender IN ('MALE', 'FEMALE')")
    private String gender;

    @JoinColumn(name = "date_of_birth", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @NotBlank
    @Email
    private String email;

    @JoinColumn(name = "bank_acc")
    @NotBlank
    private String bankAcc;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> permissions = new HashSet<>();
        roles.forEach(e -> permissions.addAll(e.getAuthorities()));
        return permissions;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
