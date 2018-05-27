package com.engineer.nutrition;


import com.engineer.data.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/nutrition")
public class NutritionController {

    private NutritionService nutritionService;

    public NutritionController(NutritionService nutritionService) {
        this.nutritionService = nutritionService;
    }

    @RequestMapping( method = RequestMethod.POST)
    @ResponseBody
    NutritionDTO proposeNutrition(@RequestBody Data data) {
        return nutritionService.proposeNutrition(data);
    }

    @PostMapping("/{name}")
    public NutritionDTO findOneNutrition (@PathVariable String name) {
        return nutritionService.findOneNutrition(name);
    }


}