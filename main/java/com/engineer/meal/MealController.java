package com.engineer.meal;


import com.engineer.data.DataMeal;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/meals")
public class MealController {

    private MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping("/{name}")
    List<MealDTO> calculateMeal(@RequestBody DataMeal dataMeal, @PathVariable String name) {
        return  mealService.calculateMeal(dataMeal,name);
    }

    @GetMapping("/{name}")
    public MealDTO findOneMealByName (@PathVariable String name) {
        return mealService.findOneMealByName(name);
    }

}
