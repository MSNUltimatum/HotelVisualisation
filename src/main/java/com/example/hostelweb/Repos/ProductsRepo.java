package com.example.hostelweb.Repos;

import com.example.hostelweb.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Integer> {
}
