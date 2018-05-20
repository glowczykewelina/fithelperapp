package com.engineer.proposition;

import com.engineer.ingredient.Ingredient;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Proposition {

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

    @OneToMany(mappedBy = "proposition")
    private List<Ingredient> ingredients;

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

    public String getTypeMeal() {
        return typeMeal;
    }

    public void setTypeMeal(String typeMeal) {
        this.typeMeal = typeMeal;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposition that = (Proposition) o;
        return weight == that.weight &&
                kcal == that.kcal &&
                protein == that.protein &&
                fat == that.fat &&
                carbohydrote == that.carbohydrote &&
                Objects.equals(nameMeal, that.nameMeal) &&
                Objects.equals(typeMeal, that.typeMeal) &&
                Objects.equals(ingredients, that.ingredients);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nameMeal, weight, typeMeal, kcal, protein, fat, carbohydrote, ingredients);
    }
}
