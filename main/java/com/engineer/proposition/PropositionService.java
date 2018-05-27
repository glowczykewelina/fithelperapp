package com.engineer.proposition;

import org.springframework.stereotype.Service;

import java.util.List;


public interface PropositionService {

    PropositionDTO addProposition(PropositionDTO propositionDTO);

    List<PropositionDTO> findAllPropositionByTypeMeal(String name);
    List<PropositionDTO> findAllPropositionByIngredient(String ingredient,String typeMeal);
    void deleteProposition(Long propositionId);

}
