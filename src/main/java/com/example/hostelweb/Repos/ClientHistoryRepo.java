package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Client_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientHistoryRepo extends JpaRepository<Client_history, Integer> {
}
