package com.example.demo.dao;
import com.example.demo.Meal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface MealRepository extends JpaRepository<Meal, Integer> {

    List<Meal> findByName(String name);
    List<Meal> findByDescription(String description);

    List<Meal> findByPriceBetween(Double num, Double num2);

    Integer deleteByName(String name);

    Integer deleteByPrice(Double price);

    @Modifying
    @Query(value = "UPDATE meal m SET m.name = ?, m.price = ?, m.description = ? WHERE m.price = ?", nativeQuery = true)
    Integer updateMealByPrice(String name, Double price, String description, Double originalPrice);

    @Modifying
    @Query(value = "UPDATE meal m SET m.name = ?, m.price = ?, m.description = ? WHERE m.meal_id = ?", nativeQuery = true)
    Integer updateMeal(String name, Double price, String description, Integer meal_id);



}
