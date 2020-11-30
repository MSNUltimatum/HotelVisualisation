package com.example.hostelweb.service;

import com.example.hostelweb.Repos.PersonRepo;
import com.example.hostelweb.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements org.springframework.security.core.userdetails.UserDetailsService{
    private final PersonRepo personRepo;

    @Autowired
    public UserDetailService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person byEmail = personRepo.findByEmail(s);
        return byEmail;
    }
}
