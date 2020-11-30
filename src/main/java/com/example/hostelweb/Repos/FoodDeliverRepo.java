package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Food_deliver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodDeliverRepo extends JpaRepository<Food_deliver, Integer> {
}
