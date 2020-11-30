package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Food_order_to_room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodOrderToRoomRepo extends JpaRepository<Food_order_to_room, Integer> {
}
