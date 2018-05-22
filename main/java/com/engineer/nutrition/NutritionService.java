package com.engineer.nutrition;

import com.engineer.data.Data;

import java.util.List;

public interface NutritionService {

    NutritionDTO proposeNutrition (Data data);

    NutritionDTO findOneNutrition(String name);
}
