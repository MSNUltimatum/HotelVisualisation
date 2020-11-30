package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Food_in_storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodInStorageRepo extends JpaRepository<Food_in_storage, Integer> {
}
