package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.DoubleBuffer;
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


    // Richiesta che ritorna tutti i piatti che contengono la string description
    @GetMapping("get/description/{description}")
    public ResponseEntity<List<Meal>> retrieveMealByDescription (@PathVariable String description)
    {
        List<Meal> mealsFilteredByDescription = meals.stream().filter(x -> x.getDescription().contains(description)).collect(Collectors.toList());
        return ResponseEntity.ok(mealsFilteredByDescription);
    }

    @GetMapping("get/byprice/{min}-{max}")
    public ResponseEntity<List<Meal>> retrieveMealByPriceRange (@PathVariable String min, @PathVariable String max)
    {
        List<Meal> mealsFilteredByPrice = meals.stream().filter(x -> x.getPrice() > Double.parseDouble(min) && x.getPrice() < Double.parseDouble(max)).toList();
        return ResponseEntity.ok(mealsFilteredByPrice);
    }

    @PutMapping("/put/meal")
    public ResponseEntity<String> addMeal(@RequestBody Meal meal) {
        meals.add(meal);
        return ResponseEntity.ok("Meal added");
    }

    @PostMapping("/post/meal")
    public ResponseEntity<String> updateMealByName (@RequestBody Meal meal) {
//        for (Meal x : meals) {
//            if(x.getName().equals(meal.getName())) {
//                meals.remove(x);
//                meals.add(meal);
//            }
//        }
        // Entrambi i modi funzionano correttamente
        meals.stream().filter(x -> x.getName().equals(meal.getName())).forEach(x-> meals.remove(x));
        meals.add(meal);

        return ResponseEntity.ok("Meal updated");
    }

    @DeleteMapping("/delete/meal/{name}")
    public ResponseEntity<String> deleteMealByName (@PathVariable String name)
    {
//    for (Meal x : meals) {
//        if (x.getName().equals(name)) {
//            meals.remove(x);
//        }
//    }
        // Entrambi i modi funzionano correttamente
    meals.stream().filter(x -> x.getName().equals(name)).forEach(x -> meals.remove(x));

    return ResponseEntity.ok("piatto cancellato con successo");
    }

}

