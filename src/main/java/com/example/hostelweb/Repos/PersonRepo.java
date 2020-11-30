package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, String> {
    List<Person> findAllByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);
    Person findByEmail(@NotBlank @Email String email);
}
