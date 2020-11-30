package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Hotel;
import com.example.hostelweb.domain.Rooms_in_hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RoomsInHotelRepo extends JpaRepository<Rooms_in_hotel, Integer> {
    @Query(value = """
            SELECT room_type_name, cost_per_night, room_place_count FROM
                               Rooms_in_hotel Rih
                    INNER JOIN Room_type Rt on Rt.room_type_id = Rih.room_type_id
                    INNER JOIN Rooms_numbers Rn on Rih.rooms_in_hotel_id = Rn.room_types_in_hotel_id
                WHERE hotel_id = ?1
                  AND
                      Rn.room_id NOT IN (SELECT client_in_hotel.room_id
                                          FROM client_in_hotel
                                          WHERE check_in_date BETWEEN ?2 AND ?3)""",
            countQuery = """
                    SELECT COUNT(*) FROM
                                       Rooms_in_hotel Rih
                            INNER JOIN Room_type Rt on Rt.room_type_id = Rih.room_type_id
                            INNER JOIN Rooms_numbers Rn on Rih.rooms_in_hotel_id = Rn.room_types_in_hotel_id
                        WHERE hotel_id = ?1
                          AND
                              Rn.room_id NOT IN (SELECT client_in_hotel.room_id
                                                  FROM client_in_hotel
                                                  WHERE check_in_date BETWEEN ?2 AND ?3)""",
            nativeQuery = true)
    Page<Object[]> findFreeRooms(Integer hotelId,
                                 String chekInDate,
                                 String checkOutDate,
                                 Pageable pageable);
}
