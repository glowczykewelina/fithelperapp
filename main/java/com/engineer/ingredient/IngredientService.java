package com.engineer.ingredient;

import java.util.List;

public interface IngredientService {

    IngredientDTO addIngredient(String ingredient, Long propositionId);
    List<IngredientDTO> findAllPropositionByIngredient(String ingredient);

}
