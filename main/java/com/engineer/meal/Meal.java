package com.engineer.meal;

import com.engineer.nutrition.Nutrition;

import javax.persistence.*;

@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String typeMeal;

    @Column
    private int quantityMeal;

    @Column
    private int meal_cal;

    @Column
    private int meal_protein;

    @Column
    private int meal_fat;

    @Column
    private int meal_carbo;


    public Meal() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeMeal() {
        return typeMeal;
    }

    public void setTypeMeal(String typeMeal) {
        this.typeMeal = typeMeal;
    }

    public int getQuantityMeal() {
        return quantityMeal;
    }

    public void setQuantityMeal(int quantityMeal) {
        this.quantityMeal = quantityMeal;
    }

    public int getMeal_cal() {
        return meal_cal;
    }

    public void setMeal_cal(int meal_cal) {
        this.meal_cal = meal_cal;
    }

    public int getMeal_protein() {
        return meal_protein;
    }

    public void setMeal_protein(int meal_protein) {
        this.meal_protein = meal_protein;
    }

    public int getMeal_fat() {
        return meal_fat;
    }

    public void setMeal_fat(int meal_fat) {
        this.meal_fat = meal_fat;
    }

    public int getMeal_carbo() {
        return meal_carbo;
    }

    public void setMeal_carbo(int meal_carbo) {
        this.meal_carbo = meal_carbo;
    }

}
