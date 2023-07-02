package com.example.demo.controller;
import com.example.demo.Meal;
import com.example.demo.service.IngredientService;
import com.example.demo.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class MealController {
    @Autowired
    private MealService mealService;

    @GetMapping("/meals")
    public ResponseEntity<List<Meal>> retrieveMeals() {

        return ResponseEntity.status(200).body(mealService.getMeals());

    }

    //     mapping che ritorna una lista di meals dove ogni meal ha come nome la stringa passata come pathvariable
    @GetMapping("/meal/{name}")
    public ResponseEntity<List<Meal>> retrieveMealByName(@PathVariable String name) {

        return ResponseEntity.ok().body(mealService.getMealByName(name));
    }



    // Richiesta che ritorna tutti i piatti che contengono la string description
    @GetMapping("/meal/find-by-description/{description}")
    public ResponseEntity<List<Meal>> retrieveMealByDescription(@PathVariable String description) {

        return ResponseEntity.ok(mealService.findByDescription(description));
    }

    @GetMapping("/meal/price/{min}-{max}")
    public ResponseEntity<List<Meal>> retrieveMealByPriceRange(@PathVariable Double min, @PathVariable Double max) {
        List<Meal> mealsFilteredByPrice = mealService.getMealsInPriceRange(min, max);
        return ResponseEntity.ok(mealsFilteredByPrice);
    }

    @PutMapping("/meal")
    public ResponseEntity<String> updateMealById(@RequestBody Meal meal, @RequestParam Integer id) throws Exception {
        if (meal.getName() != null) {
                mealService.updateMealById(meal.getName(), meal.getPrice(), meal.getDescription(), id);
            return ResponseEntity.ok().body("ok");

        } else {
            return ResponseEntity.badRequest().body("Can't update meal, check fields");
        }
    }

    @DeleteMapping("/meal/{name}")
    public ResponseEntity<String> deleteMealByName (@PathVariable String name)
    {
        if (name != null) {
        Integer updatedRows = mealService.deleteMealByName(name);
        return ResponseEntity.ok("Le righe aggiornate sono: " + updatedRows);}
        else {
            return ResponseEntity.badRequest().body("Inserire un nome valido e riprovare");
        }
    }

    @DeleteMapping("/meal/price/{price}")
    public ResponseEntity<String> deleteMealByPrice (@PathVariable Double price)
    {
        if (price != null) {
        Integer updatedRows = mealService.deleteMealByPrice(price);
        return ResponseEntity.ok().body("Le righe aggiornate sono: " + updatedRows);
        } else {
            return ResponseEntity.badRequest().body("Inserire un prezzo valido");
        }
    }


    @PutMapping("/meal/price")
    public ResponseEntity<String>  updatePriceByBodyPrice (@RequestParam Double price, @RequestBody Meal meal)
    {
        Integer updatedRows = mealService.updateByPrice(price, meal);
        return ResponseEntity.ok("Le righe aggiornate sono: " + updatedRows);
    }

    public final static Double WINTER_TEMP = 20.00;

    // get request che ritorna i piatti invernali solo se la temperatura corrente è inferiore alla costante WINTER_TEMP
    @GetMapping("/meal/winter")
    public ResponseEntity<List<Meal>> getWinterMeals(){
       List<Meal> winterMeals =  mealService.getWinterMeals();
       return ResponseEntity.ok().body(winterMeals);
    }

}

