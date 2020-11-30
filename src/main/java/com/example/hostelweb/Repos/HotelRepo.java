package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Integer> {
    @Query(value = "SELECT * FROM HOTEL ORDER BY star_count DESC", nativeQuery = true)
    Page<Hotel> findAllByStarCount(Pageable pageable);

    @Query(value = "SELECT * FROM Hotel WHERE hotel_name LIKE '%' + ?1 + '%'",
            countQuery = "SELECT COUNT(*) FROM Hotel WHERE hotel_name LIKE '%' + ?1 + '%'",nativeQuery = true)
    Page<Hotel> findAllByHotelNameLike(@NotBlank String hotelName, Pageable pageable);

    @Query(value = "SELECT * FROM Hotel WHERE hotel_adres LIKE '%' + ?1 + '%'",
            countQuery = "SELECT COUNT(*) FROM Hotel WHERE hotel_adres LIKE '%' + ?1 + '%'",nativeQuery = true)
    Page<Hotel> findAllByHotelAdresLike(@NotBlank String hotelAddress, Pageable pageable);

    @Query(value = """
            SELECT DISTINCT H.hotel_id, hotel_name, hotel_adres, star_count, hotel_email_adress, hotel_banc_acc FROM
                Rooms_in_hotel Rih
                    INNER JOIN Room_type Rt on Rt.room_type_id = Rih.room_type_id
                    INNER JOIN Rooms_numbers Rn on Rih.rooms_in_hotel_id = Rn.room_types_in_hotel_id
                    INNER JOIN Hotel H on H.hotel_id = Rih.hotel_id
            WHERE hotel_name LIKE '%' + ?1 + '%' AND hotel_adres LIKE '%' + ?2 + '%'
              AND
                    Rn.room_id NOT IN (SELECT client_in_hotel.room_id
                                       FROM client_in_hotel
                                       WHERE check_in_date BETWEEN ?3 AND ?4)""", nativeQuery = true)
    Page<Hotel> findAllByHotelNameAndAddressAndDates(String hotelName,String hotelAddress, String checkInDate, String checkOutDate, Pageable pageable);

    @Query(value = """
            SELECT DISTINCT H.hotel_id, hotel_name, hotel_adres, star_count, hotel_email_adress, hotel_banc_acc FROM
                Rooms_in_hotel Rih
                    INNER JOIN Room_type Rt on Rt.room_type_id = Rih.room_type_id
                    INNER JOIN Rooms_numbers Rn on Rih.rooms_in_hotel_id = Rn.room_types_in_hotel_id
                    INNER JOIN Hotel H on H.hotel_id = Rih.hotel_id
            WHERE hotel_name LIKE '%' + ?1 + '%'
              AND
                    Rn.room_id NOT IN (SELECT client_in_hotel.room_id
                                       FROM client_in_hotel
                                       WHERE check_in_date BETWEEN ?2 AND ?3)""",
            countQuery = """
            SELECT DISTINCT COUNT(*) FROM
                            Rooms_in_hotel Rih
                                INNER JOIN Room_type Rt on Rt.room_type_id = Rih.room_type_id
                                INNER JOIN Rooms_numbers Rn on Rih.rooms_in_hotel_id = Rn.room_types_in_hotel_id
                                INNER JOIN Hotel H on H.hotel_id = Rih.hotel_id
                        WHERE hotel_name LIKE '%' + ?1 + '%'
                          AND
                                Rn.room_id NOT IN (SELECT client_in_hotel.room_id
                                                   FROM client_in_hotel
                                                   WHERE check_in_date BETWEEN ?2 AND ?3)""",
            nativeQuery = true)
    Page<Hotel> findAllByHotelNameAndDates(String hotelName, String checkInDate, String checkOutDate, Pageable pageable);

    @Query(value = """
            SELECT DISTINCT H.hotel_id, hotel_name, hotel_adres, star_count, hotel_email_adress, hotel_banc_acc FROM
                Rooms_in_hotel Rih
                    INNER JOIN Room_type Rt on Rt.room_type_id = Rih.room_type_id
                    INNER JOIN Rooms_numbers Rn on Rih.rooms_in_hotel_id = Rn.room_types_in_hotel_id
                    INNER JOIN Hotel H on H.hotel_id = Rih.hotel_id
            WHERE hotel_adres LIKE '%' + ?1 + '%'
              AND
                    Rn.room_id NOT IN (SELECT client_in_hotel.room_id
                                       FROM client_in_hotel
                                       WHERE check_in_date BETWEEN ?2 AND ?3)""",
            countQuery = """
            SELECT DISTINCT COUNT(*) FROM
                Rooms_in_hotel Rih
                    INNER JOIN Room_type Rt on Rt.room_type_id = Rih.room_type_id
                    INNER JOIN Rooms_numbers Rn on Rih.rooms_in_hotel_id = Rn.room_types_in_hotel_id
                    INNER JOIN Hotel H on H.hotel_id = Rih.hotel_id
            WHERE hotel_adres LIKE '%' + ?1 + '%'
              AND
                    Rn.room_id NOT IN (SELECT client_in_hotel.room_id
                                       FROM client_in_hotel
                                       WHERE check_in_date BETWEEN ?2 AND ?3)""",
            nativeQuery = true)
    Page<Hotel> findAllByHotelAddressAndDates(String hotelAddress, String checkInDate, String checkOutDate, Pageable pageable);

    @Query(value = """
            SELECT DISTINCT H.hotel_id, hotel_name, hotel_adres, star_count, hotel_email_adress, hotel_banc_acc FROM
                               Rooms_in_hotel Rih
                    INNER JOIN Room_type Rt on Rt.room_type_id = Rih.room_type_id
                    INNER JOIN Rooms_numbers Rn on Rih.rooms_in_hotel_id = Rn.room_types_in_hotel_id
                    INNER JOIN Hotel H on H.hotel_id = Rih.hotel_id
                WHERE Rn.room_id NOT IN (SELECT client_in_hotel.room_id
                                          FROM client_in_hotel
                                          WHERE check_in_date BETWEEN ?1 AND ?2)""",
            countQuery = """
                SELECT COUNT(*) FROM
                               Rooms_in_hotel Rih
                    INNER JOIN Room_type Rt on Rt.room_type_id = Rih.room_type_id
                    INNER JOIN Rooms_numbers Rn on Rih.rooms_in_hotel_id = Rn.room_types_in_hotel_id
                    INNER JOIN Hotel H on H.hotel_id = Rih.hotel_id
                WHERE Rn.room_id NOT IN (SELECT client_in_hotel.room_id
                                          FROM client_in_hotel
                                          WHERE check_in_date BETWEEN ?1 AND ?2)""",
            nativeQuery = true)
    Page<Hotel> findAllByDates(String checkInDate, String checkOutDate, Pageable pageable);

    @Query(value = "SELECT * FROM Hotel WHERE hotel_name LIKE '%' + ?1 + '%' AND hotel_adres LIKE '%' + ?2 + '%'", nativeQuery = true)
    Page<Hotel> findAllByHotelNameLikeAndHotelAdresLike(String hotelName, String hotelAddress, Pageable pageable);


    Hotel findByHotelEmailAdress(@NotBlank @Email String hotelEmailAdress);

    Hotel findByHotelBancAcc(@NotBlank String hotelBancAcc);


}
