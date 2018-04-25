package com.engineer.nutrition;

import com.engineer.meal.Meal;

import javax.persistence.*;
import java.util.List;

@Entity
public class Nutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private int calory;

    @Column
    private int protein;

    @Column
    private int fat;

    @Column
    private int carbohydrote;


    public Nutrition() {

    }

    public int getCalory() {
        return calory;
    }

    public void setCalory(int calory) {
        this.calory = calory;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbohydrote() {
        return carbohydrote;
    }

    public void setCarbohydrote(int carbohydrote) {
        this.carbohydrote = carbohydrote;
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
}
