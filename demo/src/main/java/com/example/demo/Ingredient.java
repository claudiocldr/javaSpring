package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean isVegetarian;
    private Boolean isVegan;
    private Boolean isGlutenFree;
    private Boolean isLactoseFree;
    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;


    public Ingredient(Long id, String name, Boolean isVegetarian, Boolean isVegan, Boolean isGlutenFree, Boolean isLactoseFree) {
        this.id = id;
        this.name = name;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
        this.isGlutenFree = isGlutenFree;
        this.isLactoseFree = isLactoseFree;
    }

    public Ingredient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsVegetarian() {
        return isVegetarian;
    }

    public void setIsVegetarian(Boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public Boolean getIsVegan() {
        return isVegan;
    }

    public void setIsVegan(Boolean vegan) {
        isVegan = vegan;
    }

    public Boolean getIsGlutenFree() {
        return isGlutenFree;
    }

    public void setIsGlutenFree(Boolean GlutenFree) {
        isGlutenFree = GlutenFree;
    }

    public Boolean getIsLactoseFree() {
        return isLactoseFree;
    }

    public void setIsLactoseFree(Boolean lactoseFree) {
        isLactoseFree = lactoseFree;
    }

    public Boolean getVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public Boolean getVegan() {
        return isVegan;
    }

    public void setVegan(Boolean vegan) {
        isVegan = vegan;
    }

    public Boolean getGlutenFree() {
        return isGlutenFree;
    }

    public void setGlutenFree(Boolean glutenFree) {
        isGlutenFree = glutenFree;
    }

    public Boolean getLactoseFree() {
        return isLactoseFree;
    }

    public void setLactoseFree(Boolean lactoseFree) {
        isLactoseFree = lactoseFree;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
