package com.engineer.nutrition;


import com.engineer.data.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="api/nutrition")
public class NutritionController {

    private NutritionService nutritionService;

    public NutritionController(NutritionService nutritionService) {
        this.nutritionService = nutritionService;
    }

    @PostMapping
    List<NutritionDTO> proposeNutrition(@RequestBody Data data) {
        return nutritionService.proposeNutrition(data);
    }


}