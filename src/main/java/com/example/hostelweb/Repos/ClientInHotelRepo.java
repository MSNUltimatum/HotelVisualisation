package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.ClientInHotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ClientInHotelRepo extends JpaRepository<ClientInHotel, String> {

    @Query(value = """
    SELECT c FROM ClientInHotel c
    WHERE c.room.roomsInHotel.hotel.hotelId = ?1
""")
    Page<ClientInHotel> findAllByHotel(Integer hotel_id, Pageable pageable);

    @Procedure(name = "ClientInHotel.deleteClient")
    BigDecimal deleteClient(@Param("person_passport_data") String person_passport_data);
}
