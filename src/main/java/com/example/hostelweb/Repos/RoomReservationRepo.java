package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Person;
import com.example.hostelweb.domain.Room_reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface RoomReservationRepo extends JpaRepository<Room_reservation, Integer> {
    Collection<Room_reservation> findByPerson(Person person);

    @Procedure
    void BOOK_ROOM(String check_in_day, String check_out_day, String person_passport_data,Integer hotel_id, Integer room_type);
}
