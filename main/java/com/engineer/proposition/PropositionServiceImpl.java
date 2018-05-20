package com.engineer.proposition;

import com.engineer.ingredient.Ingredient;
import com.engineer.ingredient.IngredientRepository;
import com.engineer.meal.Meal;
import com.engineer.meal.MealDTO;
import com.engineer.meal.MealRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PropositionServiceImpl implements PropositionService {

    private PropositionRepository propositionRepository;
    private PropositionMapper propositionMapper;
    private MealRepository mealRepository;
    private IngredientRepository ingredientRepository;

    public PropositionServiceImpl(PropositionRepository propositionRepository, PropositionMapper propositionMapper, MealRepository mealRepository, IngredientRepository ingredientRepository) {
        this.propositionRepository = propositionRepository;
        this.propositionMapper = propositionMapper;
        this.mealRepository = mealRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public PropositionDTO addProposition(PropositionDTO propositionDTO) {
        Proposition proposition = new Proposition();
        proposition.setNameMeal(propositionDTO.getNameMeal());
        proposition.setWeight(propositionDTO.getWeight());
        proposition.setTypeMeal(propositionDTO.getTypeMeal());
        proposition.setKcal(propositionDTO.getKcal());
        proposition.setProtein(propositionDTO.getProtein());
        proposition.setFat(propositionDTO.getFat());
        proposition.setCarbohydrote(propositionDTO.getCarbohydrote());
        return propositionMapper.toPropositionDTO(propositionRepository.save(proposition));
    }

    @Override
    public List<PropositionDTO> findAllPropositionByTypeMeal(String name){

        Meal meal = mealRepository.findOneByName(name);
        String userType = meal.getTypeMeal();
        return propositionMapper.toPropositionDTO(propositionRepository.findAllByTypeMeal(userType));
    }

    @Override
    public List<PropositionDTO> findAllPropositionByIngredient(String ingredient, String typeMeal) {

        List<Ingredient> ingredient1 = ingredientRepository.findAllByIngredient(ingredient);

        for (int i=0; i<ingredient1.size(); i++) {
            Ingredient IngredWithIngredient = ingredient1.get(i);
            Proposition proposition =IngredWithIngredient.getProposition();
            if (!proposition.getTypeMeal().equals(typeMeal)) {
                ingredient1.remove(i);
            }
        }

        List<Proposition> newProposition = new ArrayList<>();
        System.out.println(ingredient1.size());
        for (int i=0; i<ingredient1.size(); i++){
            Ingredient IngredWithIngredient = ingredient1.get(i);
            Proposition propositionWithIngredient = IngredWithIngredient.getProposition();
            String nameMealIngred = propositionWithIngredient.getNameMeal();
            Proposition newPropo = new Proposition();
            newPropo = propositionRepository.findOneByNameMealAndTypeMeal(nameMealIngred,typeMeal);
            List<Proposition> allPropo = new ArrayList<>();
            allPropo.add(newPropo);
            newProposition.addAll(allPropo);
        }

        return propositionMapper.toPropositionDTO(newProposition);


    }
}
