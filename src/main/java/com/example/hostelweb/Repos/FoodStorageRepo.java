package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Food_storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodStorageRepo extends JpaRepository<Food_storage, Integer> {
}
