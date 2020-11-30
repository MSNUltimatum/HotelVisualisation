package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Hotel_worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelWorkerRepo extends JpaRepository<Hotel_worker, String> {
}
