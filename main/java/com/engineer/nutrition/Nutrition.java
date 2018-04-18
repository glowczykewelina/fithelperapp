package com.engineer.nutrition;

import javax.persistence.*;

@Entity
public class Nutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
}
