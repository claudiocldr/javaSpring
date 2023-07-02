package com.example.demo.controller;


import com.example.demo.Ingredient;
import com.example.demo.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {
    @Autowired
    IngredientService ingredientService;

    @GetMapping(value = "/all-ingredients")
    List<Ingredient> getIngredients() {

    return ingredientService.getIngredients();

    }

    @PostMapping(value = "/ingredient")
    void insertIngredientByPost(@RequestBody Ingredient ingredient) {
        ingredientService.insertIngredientByPost(ingredient);
    }

    @PutMapping(value = "/ingredient")
    void insertIngredientByPut(@RequestBody Ingredient ingredient, @RequestParam Long id) {
        ingredientService.updateingredient(ingredient, id);
    }

}
