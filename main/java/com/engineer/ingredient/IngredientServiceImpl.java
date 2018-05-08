package com.engineer.ingredient;

import com.engineer.proposition.Proposition;
import com.engineer.proposition.PropositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{

    private IngredientRepository ingredientRepository;
    private IngredientMapper ingredientMapper;
    private PropositionRepository propositionRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper, PropositionRepository propositionRepository) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
        this.propositionRepository = propositionRepository;
    }

    @Override
    public IngredientDTO addIngredient(String ingredient, Long propositionId) {
        Ingredient newIngredient= new Ingredient();
        newIngredient.setIngredient(ingredient);

        Proposition proposition = propositionRepository.findOne(propositionId);
        newIngredient.setProposition(proposition);

        return ingredientMapper.toIngredientDTO(ingredientRepository.save(newIngredient));

    }

    @Override
    public List<IngredientDTO> findAllPropositionByIngredient(String ingredient) {
        return ingredientMapper.toIngredientDTO(ingredientRepository.findAllByIngredient(ingredient));
    }
}
