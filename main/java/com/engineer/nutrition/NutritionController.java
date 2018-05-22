package com.engineer.nutrition;


import com.engineer.data.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class NutritionController {

    private NutritionService nutritionService;

    public NutritionController(NutritionService nutritionService) {
        this.nutritionService = nutritionService;
    }

    @RequestMapping(value = "/nutrition", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    NutritionDTO proposeNutrition(@RequestBody Data data) {
        return nutritionService.proposeNutrition(data);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/nutrition/{name}")
    public NutritionDTO findOneNutrition (@PathVariable String name) {
        return nutritionService.findOneNutrition(name);
    }



}