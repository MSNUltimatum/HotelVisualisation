package com.example.hostelweb.service;

import com.example.hostelweb.Repos.PersonRepo;
import com.example.hostelweb.domain.Person;
import com.example.hostelweb.domain.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PersonService {

    private final PersonRepo personRepo;
    private final PasswordEncoder encoder;

    @Autowired
    public PersonService(PersonRepo personRepo, PasswordEncoder encoder) {
        this.personRepo = personRepo;
        this.encoder = encoder;
    }

    public Boolean createPerson(Person person){
        if(person.getConfirmed_password().equals(person.getPassword())) {
            person.setRoles(Set.of(Roles.USER));
            person.setPassword(encoder.encode(person.getPassword()));
            personRepo.save(person);
            return true;
        }
        return false;
    }
}
