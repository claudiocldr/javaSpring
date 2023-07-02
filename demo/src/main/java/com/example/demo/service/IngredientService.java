package com.example.demo.service;

import com.example.demo.Ingredient;
import com.example.demo.dao.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;

    public IngredientRepository getIngredientRepository() {
        return ingredientRepository;
    }

    public void setIngredientRepository(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public List<Ingredient> getIngredientById(Iterable<Long> id) {
        return ingredientRepository.findAllById(id);
    }

    public void insertIngredientByPost(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public void updateingredient(Ingredient ingredient, Long id) {
        ingredientRepository.updateIngredient(
                ingredient.getName(),
                ingredient.getIsGlutenFree(),
                ingredient.getIsLactoseFree(),
                ingredient.getIsVegan(),
                ingredient.getIsVegetarian(),
                id);
    }
}
