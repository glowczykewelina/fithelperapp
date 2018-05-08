package com.engineer.ingredient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/ingredient")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<IngredientDTO> addIngredient (@RequestParam String ingredient, @RequestParam Long propositionId) {
        IngredientDTO result = ingredientService.addIngredient(ingredient,propositionId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public List<IngredientDTO> findAllPropositionByIngredient(@RequestParam String ingredient) {
        return ingredientService.findAllPropositionByIngredient(ingredient);
    }
}
