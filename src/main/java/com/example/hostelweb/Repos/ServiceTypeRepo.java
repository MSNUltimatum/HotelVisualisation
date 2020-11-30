package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Service_type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepo extends JpaRepository<Service_type, Integer> {
}
