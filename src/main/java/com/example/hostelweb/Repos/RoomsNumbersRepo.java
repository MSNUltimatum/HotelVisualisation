package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.RoomsNumbers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsNumbersRepo extends JpaRepository<RoomsNumbers, Integer> {

    @Query(value = """
        SELECT Rn FROM RoomsNumbers Rn
        WHERE Rn.roomsInHotel.hotel.hotelId = ?1
        """)
    Page<RoomsNumbers> findAllByHotel(Integer hotel, Pageable pageable);
}
