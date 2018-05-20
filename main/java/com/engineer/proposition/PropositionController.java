package com.engineer.proposition;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/api/admin")
public class PropositionController {

    private PropositionService propositionService;

    public PropositionController(PropositionService propositionService) {
        this.propositionService = propositionService;
    }

    @PostMapping
    public ResponseEntity<PropositionDTO> addProposition(@RequestBody PropositionDTO propositionDTO) {

        PropositionDTO newProposition = propositionService.addProposition(propositionDTO);
        return new ResponseEntity<>(newProposition, HttpStatus.CREATED);
    }

    @PostMapping("/{name}")
    public List<PropositionDTO> findAllPropositionByTypeMeal(@RequestParam String name){
        return propositionService.findAllPropositionByTypeMeal(name);
    }

    @GetMapping
    public List<PropositionDTO> findAllPropositionByIngredient(@RequestParam String ingredient, @RequestParam String typeMeal) {
        return propositionService.findAllPropositionByIngredient(ingredient,typeMeal);
    }
}
