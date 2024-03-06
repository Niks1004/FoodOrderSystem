package com.tipTopBites.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tipTopBites.domain.security.Food;
@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByCategory(String category);

    List<Food> findByFoodNameContaining(String foodName);

    List<Food> findByNumberOfCalories(String calories);

	Food findOne(Long id);
}
