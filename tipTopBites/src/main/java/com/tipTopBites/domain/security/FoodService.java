package com.tipTopBites.domain.security;

import java.util.List;
import java.util.Optional;

public interface FoodService {

    List<Food> findAll();

    Food findOne(Long long1);

    List<Food> findByCategory(String category);

    List<Food> blurrySearch(String foodName);

    List<Food> findByNumberOfCalories(String calories);

	Optional<Food> findOne(Food food);
}
