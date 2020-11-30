package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Food_in_order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodInOrderRepo extends JpaRepository<Food_in_order, Integer> {
}
