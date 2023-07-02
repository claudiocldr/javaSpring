package com.example.demo.service;

import com.example.demo.Meal;
import com.example.demo.dao.MealRepository;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.controller.MealController.WINTER_TEMP;

@Service
public class MealService {
    @Autowired
    private final MealRepository mealRepository;

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }


    public List<Meal> getMealByName(String name) {
        return mealRepository.findAll().stream().filter(x -> x.getName().equals(name)).toList();
    }

    public List<Meal> getMeals() {
        return mealRepository.findAll();
    }

    public List<Meal> getMealsInPriceRange(Double num1, Double num2) {
        return mealRepository.findByPriceBetween(num1, num2);
    }


    public void updateMealById(String name, Double price, String description, Integer meal_id) {
        mealRepository.updateMeal(name, price, description, meal_id);
    }

    public List<Meal> findByDescription(String description) {
        return mealRepository.findByDescription(description);
    }

    public Integer deleteMealByName(String name) {
        return mealRepository.deleteByName(name);
    }

    public Integer deleteMealByPrice(Double price) {
        return mealRepository.deleteByPrice(price);
    }

    public Integer updateByPrice(Double price, Meal meal) {
        return mealRepository.updateMealByPrice(meal.getName(), meal.getPrice(), meal.getDescription(), price);
    }

    private Double getCurrentTemperatureInCelsius() {
        try {
            JSONObject response = Unirest.get("https://api.open-meteo.com/v1/forecast?latitude=63.8521&longitude=15.5558&hourly=temperature_2m&current_weather=true").asJson().getBody().getObject();
            return response.getJSONObject("current_weather").getDouble("temperature");
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Meal> getWinterMeals() {
        List<Meal> meals = getMeals();
        Double currentTemperature = getCurrentTemperatureInCelsius();
        List<Meal> winterMeals = new ArrayList<>();
        if (currentTemperature < WINTER_TEMP) { meals.stream().filter(Meal::getIsWinterMeal).forEach(winterMeals::add);
        }
        return winterMeals;

    }
}
