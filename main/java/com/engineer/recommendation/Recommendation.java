package com.engineer.recommendation;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column
    private String nameMeal;

    @Column
    private int weight;

    @Column
    private String typeMeal;

    @Column
    private int kcal;

    @Column
    private int protein;

    @Column
    private int fat;

    @Column
    private int carbohydrote;

    @Column
    private int matchPercentage;

    public String getNameMeal() {
        return nameMeal;
    }

    public void setNameMeal(String nameMeal) {
        this.nameMeal = nameMeal;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getTypeMeal() {
        return typeMeal;
    }

    public void setTypeMeal(String typeMeal) {
        this.typeMeal = typeMeal;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
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

    public int getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(int matchPercentage) {
        this.matchPercentage = matchPercentage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recommendation that = (Recommendation) o;
        return weight == that.weight &&
                kcal == that.kcal &&
                protein == that.protein &&
                fat == that.fat &&
                carbohydrote == that.carbohydrote &&
                matchPercentage == that.matchPercentage &&
                Objects.equals(nameMeal, that.nameMeal) &&
                Objects.equals(typeMeal, that.typeMeal);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nameMeal, weight, typeMeal, kcal, protein, fat, carbohydrote, matchPercentage);
    }

}
