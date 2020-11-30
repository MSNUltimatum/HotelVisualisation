package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Room_type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;

@Repository
public interface RoomTypeRepo extends JpaRepository<Room_type, Integer> {
    Room_type findByRoomTypeName(@NotBlank String roomTypeName);
}
