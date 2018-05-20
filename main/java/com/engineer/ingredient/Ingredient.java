package com.engineer.ingredient;

import com.engineer.proposition.Proposition;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String ingredient;

    @ManyToOne
    private Proposition proposition;

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Proposition getProposition() {
        return proposition;
    }

    public void setProposition(Proposition proposition) {
        this.proposition = proposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(ingredient, that.ingredient) &&
                Objects.equals(proposition, that.proposition);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ingredient, proposition);
    }
}


