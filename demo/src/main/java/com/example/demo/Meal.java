package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer meal_id;

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private String description;

    public Meal(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Meal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMeal_id() {
        return meal_id;
    }
}


