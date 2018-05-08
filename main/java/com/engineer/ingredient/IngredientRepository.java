package com.engineer.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient,Long>{

    List<Ingredient> findAllByIngredient(String ingredient);

}
