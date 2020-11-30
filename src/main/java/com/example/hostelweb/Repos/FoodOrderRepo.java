package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Food_order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodOrderRepo extends JpaRepository<Food_order, Integer> {
}
