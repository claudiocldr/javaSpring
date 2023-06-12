package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class MealController {
    List<Meal> meals = Arrays.asList(new Meal("spaghetti", 2.00, "buoni"), new Meal("panino con le panelle", 2.50, "buoni"), new Meal("cotoletta", 4.00, "ottima"));

    public static void main(String[] args) {
        SpringApplication.run(MealController.class, args); }

    @GetMapping("/meals")
    public ResponseEntity<List<Meal>> retrieveMeals() {
        return ResponseEntity.status(200).body(meals);

    }
}

