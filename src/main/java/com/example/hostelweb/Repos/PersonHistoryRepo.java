package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Person;
import com.example.hostelweb.domain.Person_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PersonHistoryRepo extends JpaRepository<Person_history, Integer> {
    Collection<Person_history> findAllByPerson(Person person);
}
