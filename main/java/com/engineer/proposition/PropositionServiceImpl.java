package com.engineer.proposition;

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

    public PropositionServiceImpl(PropositionRepository propositionRepository, PropositionMapper propositionMapper, MealRepository mealRepository) {
        this.propositionRepository = propositionRepository;
        this.propositionMapper = propositionMapper;
        this.mealRepository = mealRepository;
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
}
