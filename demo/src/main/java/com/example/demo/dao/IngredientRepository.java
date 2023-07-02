package com.example.demo.dao;

import com.example.demo.Ingredient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Modifying
    @Query(value = "UPDATE ingredient i SET i.name = ?, i.is_gluten_free = ?, i.is_lactose_free = ?, i.is_vegan = ?, i.is_vegetarian = ? WHERE i.id = ?", nativeQuery = true)
    Integer updateIngredient(String name, Boolean isGlutenFree, Boolean isLactoseFree, Boolean IsVegan, Boolean isVegetarian, Long id);
}
