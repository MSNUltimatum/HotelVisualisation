package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Service_in_hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceInHotelRepo extends JpaRepository<Service_in_hotel, Integer> {
}
