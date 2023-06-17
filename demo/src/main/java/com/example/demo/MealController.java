package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class MealController {
    List<Meal> meals = new ArrayList<>(Arrays.asList(new Meal("spaghetti", 2.00, "buoni"), new Meal("panino con le panelle", 2.50, "buoni"), new Meal("cotoletta", 4.00, "ottima")));

    public static void main(String[] args) {
        SpringApplication.run(MealController.class, args); }

    @GetMapping("get/meals")
    public ResponseEntity<List<Meal>> retrieveMeals() {
        return ResponseEntity.status(200).body(meals);

    }

    // mapping che ritorna una lista di meals dove ogni meal ha come nome la stringa passata come pathvariable
    @GetMapping("get/meal/{name}")
    public ResponseEntity<List<Meal>> retrieveMealByName(@PathVariable String name) {
        List<Meal> mealsRetrieved = meals.stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList());
        return ResponseEntity.ok().body(mealsRetrieved);
    }

   // 1 - Annotate a new class with the @RestController annotation.
    // 2 - Create a new endpoint "/meal/description-match/{phrase}" using the @GetMapping annotation.
    // 3 - In the method, add a query parameter "description" using the @PathVariable annotation.
    // 4 - Return a Meal object with the corresponding description.
    // Richiesta che ritorna tutti i piatti che contengono la string description
    @GetMapping("get/description/{description}")
    public ResponseEntity<List<Meal>> retrieveMealByDescription (@PathVariable String description)
    {
        List<Meal> mealsFilteredByDescription = meals.stream().filter(x -> x.getDescription().contains(description)).collect(Collectors.toList());
        return ResponseEntity.ok(mealsFilteredByDescription);
    }

}

